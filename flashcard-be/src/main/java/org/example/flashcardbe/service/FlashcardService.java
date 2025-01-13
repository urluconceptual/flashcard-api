package org.example.flashcardbe.service;

import lombok.RequiredArgsConstructor;
import org.example.flashcardbe.dto.FlashcardRequest;
import org.example.flashcardbe.dto.FlashcardResponse;
import org.example.flashcardbe.mapper.FlashcardMapper;
import org.example.flashcardbe.model.Collection;
import org.example.flashcardbe.model.Flashcard;
import org.example.flashcardbe.repository.CollectionRepository;
import org.example.flashcardbe.repository.FlashcardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlashcardService {
    private final FlashcardRepository flashcardRepository;
    private final CollectionRepository collectionRepository;

    public FlashcardResponse createFlashcard(FlashcardRequest requestDTO) {
        Collection collection = collectionRepository.findById(requestDTO.getCollectionId())
                .orElseThrow(() -> new RuntimeException("Collection not found"));

        Flashcard flashcard = FlashcardMapper.toEntity(requestDTO, collection);
        Flashcard savedFlashcard = flashcardRepository.save(flashcard);
        return FlashcardMapper.toResponseDTO(savedFlashcard);
    }

    public List<FlashcardResponse> getFlashcardsByCollectionId(Long collectionId) {
        return flashcardRepository.findByCollection_CollectionId(collectionId)
                .stream().map(FlashcardMapper::toResponseDTO).toList();
    }

    public void deleteFlashcard(Long flashcardId) {
        flashcardRepository.deleteById(flashcardId);
    }
}