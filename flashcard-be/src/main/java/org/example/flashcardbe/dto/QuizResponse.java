package org.example.flashcardbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class QuizResponse {
    private Long quizId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private Integer score;
}
