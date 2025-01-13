package org.example.flashcardbe.mapper;

import lombok.experimental.UtilityClass;
import org.example.flashcardbe.dto.FlashcardRequest;
import org.example.flashcardbe.dto.FlashcardResponse;
import org.example.flashcardbe.model.Collection;
import org.example.flashcardbe.model.Flashcard;

@UtilityClass
public class FlashcardMapper {
    public Flashcard toEntity(FlashcardRequest dto, Collection collection) {
        Flashcard flashcard = new Flashcard();
        flashcard.setTerm(dto.getTerm());
        flashcard.setDefinition(dto.getDefinition());
        flashcard.setDifficulty(dto.getDifficulty());
        flashcard.setCollection(collection);
        return flashcard;
    }

    public FlashcardResponse toResponseDTO(Flashcard flashcard) {
        FlashcardResponse dto = new FlashcardResponse();
        dto.setFlashcardId(flashcard.getFlashcardId());
        dto.setTerm(flashcard.getTerm());
        dto.setDefinition(flashcard.getDefinition());
        dto.setDifficulty(flashcard.getDifficulty());
        dto.setCollectionId(flashcard.getCollection().getCollectionId());
        return dto;
    }
}
