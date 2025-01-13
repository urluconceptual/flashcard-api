package org.example.flashcardbe.mapper;

import lombok.experimental.UtilityClass;
import org.example.flashcardbe.dto.AppUserRequest;
import org.example.flashcardbe.dto.AppUserResponse;
import org.example.flashcardbe.model.AppUser;
import org.example.flashcardbe.model.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

@UtilityClass
public class AppUserMapper {
    public AppUser toEntity(AppUserRequest dto, PasswordEncoder passwordEncoder) {
        final String encodedPassword = passwordEncoder.encode(dto.getPassword());
        return AppUser.builder().username(dto.getUsername()).password(encodedPassword).role(Role.ROLE_USER).build();
    }

    public AppUserResponse toDTO(AppUser user) {
        AppUserResponse dto = AppUserResponse.builder().userId(user.getUserId()).username(user.getUsername()).build();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        return dto;
    }
}