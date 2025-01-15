package org.example.flashcardbe.repository;

import org.example.flashcardbe.model.Collection;
import org.example.flashcardbe.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findByCollection_CollectionId(Long collectionId);

    @Modifying
    @Query("update Flashcard f set f.term = ?1, f.definition = ?2, f.difficulty = ?3 where f.flashcardId = ?4")
    void updateFlashcardById(String term, String definition, int difficulty, Long flashcardId);

    List<Flashcard> getFlashcardByDifficultyAndCollection(int difficulty, Collection collection);

    List<Flashcard> getFlashcardByDifficultyAndCollection_User_UserId(Integer difficulty, Long userId);

    Optional<Flashcard> getFlashcardByFlashcardId(int flashcardId);
}
