package org.example.flashcardbe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CollectionRequest {
    @NotBlank(message = "Collection name is required")
    @Size(max = 100, message = "Collection name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "User ID is required")
    private Long userId;
}
