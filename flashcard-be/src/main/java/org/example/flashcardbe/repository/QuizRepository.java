package org.example.flashcardbe.repository;

import org.example.flashcardbe.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByUser_UserId(Long userId);

    Optional<Quiz> findQuizByQuizId(Long quizId);
}