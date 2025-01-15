package org.example.flashcardbe.service;

import lombok.AllArgsConstructor;
import org.example.flashcardbe.dto.AchievementRequest;
import org.example.flashcardbe.dto.AchievementResponse;
import org.example.flashcardbe.mapper.AchievementMapper;
import org.example.flashcardbe.model.Achievement;
import org.example.flashcardbe.repository.AchievementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AchievementService {
    public final AchievementRepository achievementRepository;

    public AchievementResponse saveAchievement(AchievementRequest achievement) {
        Achievement achievementEntity = achievementRepository.save(AchievementMapper.toEntity(achievement));
        return AchievementMapper.toDto(achievementEntity);
    }

    public AchievementResponse updateAchievement(Long achievementId, AchievementRequest achievement) {
        Achievement achievementEntity = achievementRepository.findById(achievementId).orElseThrow();
        achievementEntity.setName(achievement.getName());
        achievementEntity.setDescription(achievement.getDescription());
        achievementEntity.setMinQuizzes(achievement.getMinQuizzes());
        achievementEntity.setMinMediumScore(achievement.getMinMediumScore());
        achievementRepository.save(achievementEntity);
        return AchievementMapper.toDto(achievementEntity);
    }

    public void deleteAchievement(Long achievementId) {
        achievementRepository.deleteById(achievementId);
    }

    public List<AchievementResponse> getForUserId(Long userId) {
        return achievementRepository.findByUsers_UserId(userId).stream().map(AchievementMapper::toDto).toList();
    }
}
