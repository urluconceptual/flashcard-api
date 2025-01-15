package org.example.flashcardbe.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuizAnswerRequest {
    private Long quizId;
    private List<QuizFlashcardRequest> quizFlashcardRequestList;
}
