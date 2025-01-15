package org.example.flashcardbe.mapper;

import lombok.experimental.UtilityClass;
import org.example.flashcardbe.dto.AchievementRequest;
import org.example.flashcardbe.dto.AchievementResponse;
import org.example.flashcardbe.model.Achievement;

@UtilityClass
public class AchievementMapper {
    public Achievement toEntity(AchievementRequest achievementDto) {
        return Achievement.builder()
                .name(achievementDto.getName())
                .description(achievementDto.getDescription())
                .minQuizzes(achievementDto.getMinQuizzes())
                .minMediumScore(achievementDto.getMinMediumScore())
                .build();
    }

    public AchievementResponse toDto(Achievement achievement) {
        return AchievementResponse.builder()
                .achievementId(achievement.getAchievementId())
                .name(achievement.getName())
                .description(achievement.getDescription())
                .minQuizzes(achievement.getMinQuizzes())
                .minMediumScore(achievement.getMinMediumScore())
                .build();
    }
}
