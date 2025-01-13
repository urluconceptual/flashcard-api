package org.example.flashcardbe.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserResponse {
    private Long userId;
    private String username;
}
