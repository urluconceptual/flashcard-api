package org.example.flashcardbe.mapper;

import lombok.experimental.UtilityClass;
import org.example.flashcardbe.dto.CollectionRequest;
import org.example.flashcardbe.dto.CollectionResponse;
import org.example.flashcardbe.model.AppUser;
import org.example.flashcardbe.model.Collection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CollectionMapper {
    public Collection toEntity(CollectionRequest dto, AppUser user) {
        return Collection.builder().name(dto.getName()).category(dto.getCategory()).user(user).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
    }

    public CollectionResponse toResponseDTO(Collection collection) {
        return CollectionResponse.builder().collectionId(collection.getCollectionId()).name(
                        collection.getName()).category(collection.getCategory()).userId(collection.getUser()
                        .getUserId())
                        .createdAt(collection.getCreatedAt()).updatedAt(collection.getUpdatedAt())
                .flashcardCount((long) collection.getFlashcards().size()).build();
    }

    public List<CollectionResponse> toResponseDTO(List<Collection> collections) {
        return collections.stream().map(CollectionMapper::toResponseDTO).collect(Collectors.toList());
    }
}
