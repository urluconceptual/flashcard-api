package org.example.flashcardbe.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.flashcardbe.exception.security.UnauthorizedUserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Order(1)
public class AuthorizationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Value("${xsrf.header.value:ValidToken}")
    private String validXsrfToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if ("websocket".equalsIgnoreCase(request.getHeader("Upgrade"))) {
                filterChain.doFilter(request, response);
                return;
            }

            String authorizationHeader = request.getHeader("Authorization");
            String token = jwtProvider.resolveToken(authorizationHeader);

            if (authorizationHeader != null && jwtProvider.validateToken(token)) {
                Authentication authentication = jwtProvider.doAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        } catch (UnauthorizedUserException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token in filter!");
        }
    }
}
