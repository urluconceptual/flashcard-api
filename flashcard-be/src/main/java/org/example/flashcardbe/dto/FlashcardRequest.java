package org.example.flashcardbe.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class FlashcardRequest {
    @NotBlank(message = "Term is required")
    @Size(max = 255, message = "Term cannot exceed 255 characters")
    private String term;

    @NotBlank(message = "Definition is required")
    private String definition;

    @NotNull(message = "Difficulty is required")
    @Min(value = 1, message = "Difficulty must be at least 1")
    @Max(value = 5, message = "Difficulty cannot exceed 5")
    private Integer difficulty;

    private Long collectionId;
}
