package org.example.flashcardbe.repository;

import org.example.flashcardbe.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByUsers_UserId(Long userId);
}