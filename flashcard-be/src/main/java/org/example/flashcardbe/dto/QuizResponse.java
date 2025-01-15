package org.example.flashcardbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class QuizResponse {
    private Long quizId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private Integer score;
    private Integer flashcardNumber;
}
