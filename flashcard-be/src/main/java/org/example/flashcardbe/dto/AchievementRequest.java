package org.example.flashcardbe.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AchievementRequest {
    private String name;
    private String description;
    private int minQuizzes;
    private int minMediumScore;
}
