package org.example.flashcardbe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.flashcardbe.dto.*;
import org.example.flashcardbe.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz")
@Tag(name = "Quiz", description = "Quiz management APIs")
public class QuizController {
    private final QuizService quizService;

    @Operation(summary = "Start a new quiz session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully started quiz session",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuizAnswerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid quiz request", content = @Content)
    })
    @PostMapping("/new")
    public ResponseEntity<QuizAnswerResponse> startQuiz(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Quiz request with initial parameters")
            @RequestBody QuizRequest quizRequestDto) {
        return ResponseEntity.ok(this.quizService.createQuiz(quizRequestDto));
    }

    @Operation(summary = "Submit answers for an existing quiz session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully submitted quiz answers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuizResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid quiz answers", content = @Content)
    })
    @PostMapping
    public ResponseEntity<QuizResponse> submitQuiz(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Quiz answer submission request")
            @RequestBody QuizAnswerRequest quizAnswerRequestDto) {
        return ResponseEntity.ok(this.quizService.submitQuiz(quizAnswerRequestDto));
    }

    @Operation(summary = "Get a random flashcard by user ID and difficulty")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully generated random flashcard",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuizAnswerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid quiz request", content = @Content)
    })
    @GetMapping("/random")
    public ResponseEntity<QuizAnswerResponse> getRandomQuestion(
            @RequestParam QuizRequest quizRequestDto) {
        return ResponseEntity.ok(
                this.quizService.getRandomFlashcard(quizRequestDto.getDifficulty(), quizRequestDto.getUserId()));
    }

    @Operation(summary = "Check answer for a flashcard")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully checked answer",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "Invalid quiz flashcard", content = @Content)
    })
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkAnswer(
            @RequestParam QuizFlashcardRequest answer) {
        return ResponseEntity.ok(this.quizService.checkAnswer(answer));
    }
}