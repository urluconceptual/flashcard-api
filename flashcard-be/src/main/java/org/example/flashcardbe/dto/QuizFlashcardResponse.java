package org.example.flashcardbe.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuizFlashcardResponse {
    private Long flashcardId;
    private Long collectionId;
    private String term;
    private List<String> answerVariantList;
}
