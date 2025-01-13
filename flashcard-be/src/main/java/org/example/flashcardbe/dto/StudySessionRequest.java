package org.example.flashcardbe.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StudySessionRequest {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Collection ID is required")
    private Long collectionId;
}
