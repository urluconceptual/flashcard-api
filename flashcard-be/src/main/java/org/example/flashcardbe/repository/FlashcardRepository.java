package org.example.flashcardbe.repository;

import org.example.flashcardbe.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findByCollection_CollectionId(Long collectionId);
}
