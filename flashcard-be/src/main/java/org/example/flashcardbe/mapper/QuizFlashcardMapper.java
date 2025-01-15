package org.example.flashcardbe.mapper;

import lombok.experimental.UtilityClass;
import org.example.flashcardbe.dto.QuizFlashcardResponse;
import org.example.flashcardbe.model.Flashcard;
import org.example.flashcardbe.model.Quiz;
import org.example.flashcardbe.model.QuizFlashcard;
import org.example.flashcardbe.repository.FlashcardRepository;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class QuizFlashcardMapper {
    public QuizFlashcardResponse toDto(Flashcard flashcard) {
        return QuizFlashcardResponse.builder()
                .flashcardId(flashcard.getFlashcardId())
                .term(flashcard.getTerm())
                .collectionId(flashcard.getCollection().getCollectionId())
                .answerVariantList(new ArrayList<>(List.of(flashcard.getDefinition())))
                .build();
    }

    public QuizFlashcard toEntity(QuizFlashcardResponse quizFlashcardResponse, Quiz quiz,
                                  FlashcardRepository flashcardRepository) {
        return QuizFlashcard.builder()
                .quiz(quiz)
                .flashcard(flashcardRepository.findById(quizFlashcardResponse.getFlashcardId()).get())
                .build();
    }
}
