package org.example.flashcardbe.mapper;

import lombok.experimental.UtilityClass;
import org.example.flashcardbe.dto.QuizResponse;
import org.example.flashcardbe.model.Quiz;

@UtilityClass
public class QuizMapper {

    public QuizResponse toDto(Quiz quiz) {
        return new QuizResponse(quiz.getQuizId(), quiz.getUser().getUserId(), quiz.getCreatedAt(),
                quiz.getCompletedAt(), quiz.getScore(), quiz.getTotalQuestions());
    }
}
