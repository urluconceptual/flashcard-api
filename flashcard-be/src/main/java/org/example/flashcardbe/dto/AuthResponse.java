package org.example.flashcardbe.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    AppUserResponse user;
    String token;
}
