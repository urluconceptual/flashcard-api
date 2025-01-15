package org.example.flashcardbe.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AchievementResponse {
    private Long achievementId;
    private String name;
    private String description;
    private int minQuizzes;
    private int minMediumScore;
}
