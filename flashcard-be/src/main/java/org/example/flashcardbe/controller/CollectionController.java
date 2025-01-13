package org.example.flashcardbe.controller;

import lombok.RequiredArgsConstructor;
import org.example.flashcardbe.dto.CollectionRequest;
import org.example.flashcardbe.dto.CollectionResponse;
import org.example.flashcardbe.service.CollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collection")
public class CollectionController {
    private final CollectionService collectionService;

    @PostMapping
    public ResponseEntity<CollectionResponse> saveCollection(@RequestBody CollectionRequest request) {
        return ResponseEntity.ok(collectionService.saveCollection(request));
    }

    @GetMapping
    public ResponseEntity<List<CollectionResponse>> getAllCollections() {
        return ResponseEntity.ok(collectionService.getAllCollections());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CollectionResponse>> getCollectionsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(collectionService.getCollectionsByUserId(userId));
    }
}
