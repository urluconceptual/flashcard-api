package org.example.flashcardbe.repository;

import org.example.flashcardbe.model.QuizFlashcard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizFlashcardRepository extends JpaRepository<QuizFlashcard, Long> {

    List<QuizFlashcard> findByQuiz_QuizId(Long quizId);

    long countByQuiz_QuizIdAndIsCorrectTrue(Long quizId);
}
