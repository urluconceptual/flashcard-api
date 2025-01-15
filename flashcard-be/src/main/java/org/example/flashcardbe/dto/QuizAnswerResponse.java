package org.example.flashcardbe.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuizAnswerResponse {
    private Long quizId;
    private List<QuizFlashcardResponse> list;
}
