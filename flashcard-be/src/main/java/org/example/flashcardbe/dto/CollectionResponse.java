package org.example.flashcardbe.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CollectionResponse {
    private Long collectionId;
    private String name;
    private String category;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long flashcardCount;
}
