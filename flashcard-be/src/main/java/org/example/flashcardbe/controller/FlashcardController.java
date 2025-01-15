package org.example.flashcardbe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.example.flashcardbe.dto.FlashcardRequest;
import org.example.flashcardbe.dto.FlashcardResponse;
import org.example.flashcardbe.service.FlashcardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/flashcard")
@Tag(name = "Flashcard", description = "Flashcard management APIs")
public class FlashcardController {
    private final FlashcardService flashcardService;

    @Operation(summary = "Create multiple flashcards")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created flashcards",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlashcardResponse.class))),
            @ApiResponse(responseCode = "404", description = "Collection not found")
    })
    @PostMapping("/bulk")
    public ResponseEntity<List<FlashcardResponse>> createFlashcards(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "List of flashcard requests")
            @RequestBody List<FlashcardRequest> flashcardRequestDto) {
        return ResponseEntity.ok(this.flashcardService.createFlashcards(flashcardRequestDto));
    }

    @Operation(summary = "Create a single flashcard")
    @PostMapping
    public ResponseEntity<FlashcardResponse> createFlashcard(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Flashcard request")
            @RequestBody FlashcardRequest flashcardRequestDto) {
        return ResponseEntity.ok(this.flashcardService.createFlashcard(flashcardRequestDto));
    }

    @Operation(summary = "Get flashcards by collection ID")
    @Parameter(name = "collectionId", description = "ID of the collection", required = true)
    @GetMapping("/{collectionId}")
    public ResponseEntity<List<FlashcardResponse>> getFlashcards(@PathVariable Long collectionId) {
        return ResponseEntity.ok(this.flashcardService.getFlashcardsByCollectionId(collectionId));
    }

    @Operation(summary = "Update a flashcard by ID")
    @PutMapping("/{flashcardId}")
    public ResponseEntity<FlashcardResponse> updateFlashcard(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated flashcard request")
            @RequestBody FlashcardRequest flashcardRequest,
            @Parameter(description = "ID of the flashcard to update") @PathVariable Long flashcardId) {
        val wasFound = this.flashcardService.updateFlashcardById(flashcardRequest, flashcardId);
        if (!wasFound) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete a flashcard by ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted flashcard")
    @DeleteMapping("/{flashcardId}")
    public ResponseEntity<Void> deleteFlashcard(@PathVariable Long flashcardId) {
        boolean wasFound = this.flashcardService.deleteFlashcard(flashcardId);
        if (!wasFound) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Import flashcards from a CSV file to a collection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully imported flashcards",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FlashcardResponse.class))),
            @ApiResponse(responseCode = "404", description = "Collection not found")
    })
    @PostMapping("/import")
    public ResponseEntity<List<FlashcardResponse>> importFlashcards(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "CSV file with flashcard data")
            @RequestParam("file") MultipartFile csvFile) throws Exception {
        return ResponseEntity.ok(this.flashcardService.importFlashcards(csvFile));
    }
}