package org.example.flashcardbe.service;

import lombok.AllArgsConstructor;
import org.example.flashcardbe.dto.CollectionRequest;
import org.example.flashcardbe.dto.CollectionResponse;
import org.example.flashcardbe.mapper.CollectionMapper;
import org.example.flashcardbe.model.AppUser;
import org.example.flashcardbe.model.Collection;
import org.example.flashcardbe.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CollectionService {
    private UserService userService;
    private CollectionRepository collectionRepository;

    public CollectionResponse saveCollection(CollectionRequest collectionRequest) {
        AppUser user = userService.getUserById(collectionRequest.getUserId());
        Collection collection = CollectionMapper.toEntity(collectionRequest, user);
        collection = collectionRepository.save(collection);
        return CollectionMapper.toResponseDTO(collection);
    }

    public List<CollectionResponse> getAllCollections() {
        List<Collection> collections = collectionRepository.findAll();
        return CollectionMapper.toResponseDTO(collections);
    }

    public List<CollectionResponse> getCollectionsByUserId(Long userId) {
        List<Collection> collections = collectionRepository.findByUser_UserId(userId);
        return CollectionMapper.toResponseDTO(collections);
    }

    public Optional<Collection> getCollectionById(Long collectionId) {
        return collectionRepository.findById(collectionId);
    }

    public void deleteCollection(Long collectionId) {
        collectionRepository.deleteById(collectionId);
    }
}
