package org.example.flashcardbe.repository;

import org.example.flashcardbe.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    List<Collection> findByUser_UserId(Long userId);


}
