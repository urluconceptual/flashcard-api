package org.example.flashcardbe.dto;

import lombok.Data;

@Data
public class QuizFlashcardRequest {
    private int flashcardId;
    private String answer;
}
