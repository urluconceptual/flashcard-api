package org.example.flashcardbe.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.flashcardbe.exception.security.UnauthorizedUserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    private static final String AUTHORITY = "authority";

    @Value("${jwt.secret}")
    private String secret;

    public String getUserIdFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getJwtKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String generateToken(String id, Long ttl, String authority) {
        Date expriationDate = Date.from(ZonedDateTime.now().plusHours(ttl).toInstant());

        return Jwts.builder()
                .setSubject(id)
                .claim(AUTHORITY, Set.of(authority))
                .setExpiration(expriationDate)
                .signWith(getJwtKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public SecretKey getJwtKey() {
        byte[] keyByteArray = Decoders.BASE64.decode(secret);

        return Keys.hmacShaKeyFor(keyByteArray);
    }

    public boolean validateToken(String token) throws UnauthorizedUserException {
        if (token.isBlank()) {
            throw new UnauthorizedUserException();
        }
        try {
            Jwts.parser()
                    .verifyWith(getJwtKey())
                    .build()
                    .parseSignedClaims(token);
        } catch (JwtException e) {
            throw new UnauthorizedUserException();
        }

        return true;
    }

    public Authentication doAuthentication(String token) throws UnauthorizedUserException {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getJwtKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            Set<SimpleGrantedAuthority> grantedAuthorities = ((ArrayList<String>) claims.get(AUTHORITY))
                    .stream()
                    .filter(claim -> claim != null && !claim.isBlank())
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());

            User user = new User(claims.getSubject(), "", grantedAuthorities);

            return new UsernamePasswordAuthenticationToken(user, "", grantedAuthorities);
        } catch (JwtException e) {
            throw new UnauthorizedUserException();
        }
    }

    public String resolveToken(String authorizationHeader) {
        if (authorizationHeader == null ||
                authorizationHeader.isBlank() ||
                !authorizationHeader.contains("Bearer")) {
            return "";
        }
        return authorizationHeader.substring(7);
    }

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }
}
