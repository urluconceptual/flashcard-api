package org.example.flashcardbe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Collection", description = "Collection management APIs")
public class CollectionController {
    private final CollectionService collectionService;

    @Operation(summary = "Save a new collection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created collection",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CollectionResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping
    public ResponseEntity<CollectionResponse> saveCollection(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Collection request")
            @RequestBody CollectionRequest request) {
        return ResponseEntity.ok(collectionService.saveCollection(request));
    }

    @Operation(summary = "Retrieve all collections")
    @ApiResponse(responseCode = "200", description = "List of all collections",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CollectionResponse.class)))
    @GetMapping
    public ResponseEntity<List<CollectionResponse>> getAllCollections() {
        return ResponseEntity.ok(collectionService.getAllCollections());
    }

    @Operation(summary = "Retrieve collections by user ID")
    @Parameter(name = "userId", description = "ID of the user", required = true)
    @ApiResponse(responseCode = "200", description = "List of collections for the user",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CollectionResponse.class)))
    @GetMapping("/{userId}")
    public ResponseEntity<List<CollectionResponse>> getCollectionsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(collectionService.getCollectionsByUserId(userId));
    }

    @Operation(summary = "Update an existing collection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated collection",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CollectionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Collection not found", content = @Content)
    })
    @PutMapping("/{collectionId}")
    public ResponseEntity<CollectionResponse> updateCollection(
            @Parameter(description = "ID of the collection to update") @PathVariable Long collectionId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated collection request")
            @RequestBody CollectionRequest request) {
        return ResponseEntity.ok(collectionService.updateCollection(collectionId, request));
    }

    @Operation(summary = "Delete a collection by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted collection"),
            @ApiResponse(responseCode = "404", description = "Collection not found", content = @Content)
    })
    @DeleteMapping("/{collectionId}")
    public ResponseEntity<Void> deleteCollection(@PathVariable Long collectionId) {
        collectionService.deleteCollection(collectionId);
        return ResponseEntity.ok().build();
    }
}