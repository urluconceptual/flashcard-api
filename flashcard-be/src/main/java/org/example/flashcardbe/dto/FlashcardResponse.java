package org.example.flashcardbe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlashcardResponse {
    private Long flashcardId;
    private String term;
    private String definition;
    private Integer difficulty;
    private Long collectionId;
}
