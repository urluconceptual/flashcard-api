package org.example.flashcardbe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.flashcardbe.service.StudySessionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Study Session", description = "Study session management APIs")
@RequestMapping("/api/v1/study-session")
public class StudySessionController {
    private final StudySessionService studySessionService;

    @PostMapping("/start/{userId}")
    @Operation(
            summary = "Start a Study Session",
            description = "This endpoint starts a new study session for the user specified by `userId`."
    )
    @ApiResponse(responseCode = "200", description = "Study session started successfully")
    public void startStudySession(
            @Parameter(description = "ID of the user for whom the study session is to be started")
            @PathVariable Long userId) {
        studySessionService.startStudySession(userId);
    }

    @PostMapping("/end/{sessionId}")
    @Operation(
            summary = "End a Study Session",
            description = "This endpoint ends an active study session specified by `sessionId`."
    )
    @ApiResponse(responseCode = "200", description = "Study session ended successfully")
    public void endStudySession(
            @Parameter(description = "ID of the session to be ended")
            @PathVariable Long sessionId) {
        studySessionService.endStudySession(sessionId);
    }

    @GetMapping("/{userId}")
    @Operation(
            summary = "Get All Study Sessions for a User",
            description = "This endpoint retrieves all study sessions associated with the user specified by `userId`."
    )
    @ApiResponse(responseCode = "200", description = "List of study sessions retrieved successfully")
    public void getStudySessionsByUserId(
            @Parameter(description = "ID of the user to retrieve study sessions for")
            @PathVariable Long userId) {
        studySessionService.getStudySessionsByUserId(userId);
    }

    @PostMapping("/active/{userId}")
    @Operation(
            summary = "Get Active Study Sessions for a User",
            description = "This endpoint retrieves all active study sessions for the user specified by `userId`."
    )
    @ApiResponse(responseCode = "200", description = "Active study sessions retrieved successfully")
    public void getActiveStudySessions(
            @Parameter(description = "ID of the user to retrieve active study sessions for")
            @PathVariable Long userId) {
        studySessionService.getActiveStudySessions(userId);
    }
}