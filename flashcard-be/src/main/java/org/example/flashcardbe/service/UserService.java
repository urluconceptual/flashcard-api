package org.example.flashcardbe.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.flashcardbe.config.JwtProvider;
import org.example.flashcardbe.dto.AppUserRequest;
import org.example.flashcardbe.dto.AppUserResponse;
import org.example.flashcardbe.dto.AuthResponse;
import org.example.flashcardbe.exception.conflict.UsernameConflictException;
import org.example.flashcardbe.exception.security.AuthenticationFailedException;
import org.example.flashcardbe.exception.security.UnauthorizedUserException;
import org.example.flashcardbe.mapper.AppUserMapper;
import org.example.flashcardbe.model.AppUser;
import org.example.flashcardbe.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Value("${jwt.ttl}")
    private Long timeToLive;

    public AuthResponse checkToken(@NotNull String token) throws ObjectNotFoundException {
        String tokenValue = token.substring(7);
        String userId = jwtProvider.getUserIdFromToken(tokenValue);
        AppUser user =
                userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new ObjectNotFoundException((Object) userId, "AppUser"));
        AppUserResponse userResponse = AppUserMapper.toDTO(user);
        return AuthResponse.builder().user(userResponse).token(tokenValue).build();
    }

    public AuthResponse authenticate(@NotNull AppUserRequest request) throws ObjectNotFoundException,
            UnauthorizedUserException {
        String username = request.getUsername();
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException((Object) username, "AppUser"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthenticationFailedException();
        }

        AppUserResponse userResponse = AppUserMapper.toDTO(user);
        String token = jwtProvider.generateToken(userResponse.getUserId().toString(), timeToLive,
                user.getRole().toString());
        return AuthResponse.builder().user(userResponse).token(token).build();
    }

    public AuthResponse saveUser(@NotNull AppUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameConflictException();
        }

        AppUser user = AppUserMapper.toEntity(request, passwordEncoder);
        user = userRepository.save(user);

        AppUserResponse userResponse = AppUserMapper.toDTO(user);
        String token = jwtProvider.generateToken(userResponse.getUserId().toString(), timeToLive,
                user.getRole().toString());
        return AuthResponse.builder().user(userResponse).token(token).build();
    }

    public AppUser getUserById(@NotNull Long userId) throws ObjectNotFoundException {
        Optional<AppUser> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new ObjectNotFoundException(userId, "AppUser");
        return user.get();
    }

    public void deleteUser(@NotNull Long userId) {
        userRepository.deleteById(userId);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}