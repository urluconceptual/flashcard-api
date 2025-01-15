package org.example.flashcardbe.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class QuizRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    private Integer difficulty;
}
