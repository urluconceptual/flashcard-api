package org.example.flashcardbe.service;

import lombok.AllArgsConstructor;
import org.example.flashcardbe.model.Flashcard;
import org.example.flashcardbe.model.Quiz;
import org.example.flashcardbe.model.QuizFlashcard;
import org.example.flashcardbe.repository.QuizFlashcardRepository;
import org.example.flashcardbe.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizService {

    private QuizRepository quizRepository;

    private QuizFlashcardRepository quizFlashcardRepository;

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Optional<Quiz> getQuizById(Long quizId) {
        return quizRepository.findById(quizId);
    }

    public List<Quiz> getQuizzesByUserId(Long userId) {
        return quizRepository.findByUser_UserId(userId);
    }

    public void deleteQuiz(Long quizId) {
        quizRepository.deleteById(quizId);
    }

    public void addFlashcardsToQuiz(Quiz quiz, List<Flashcard> flashcards) {
        for (Flashcard flashcard : flashcards) {
            QuizFlashcard quizFlashcard = new QuizFlashcard();
            quizFlashcard.setQuiz(quiz);
            quizFlashcard.setFlashcard(flashcard);
            quizFlashcardRepository.save(quizFlashcard);
        }
    }

    public List<QuizFlashcard> getQuizFlashcards(Long quizId) {
        return quizFlashcardRepository.findByQuiz_QuizId(quizId);
    }
}