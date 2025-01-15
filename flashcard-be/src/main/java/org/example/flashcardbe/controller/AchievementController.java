package org.example.flashcardbe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.flashcardbe.dto.AchievementRequest;
import org.example.flashcardbe.dto.AchievementResponse;
import org.example.flashcardbe.service.AchievementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Achievement", description = "Achievement management APIs")
@RequestMapping("/api/v1/achievement")
public class AchievementController {
    private final AchievementService achievementService;

    @PostMapping
    @Operation(
            summary = "Save a new Achievement",
            description = "This endpoint allows the creation of a new achievement for a user."
    )
    @ApiResponse(responseCode = "200", description = "Achievement saved successfully")
    public void saveAchievement(
            @Parameter(description = "Achievement details to be saved")
            @RequestBody AchievementRequest achievementDto) {
        achievementService.saveAchievement(achievementDto);
    }

    @DeleteMapping("/{achievementId}")
    @Operation(
            summary = "Delete an Achievement",
            description = "This endpoint allows the deletion of an achievement by its ID."
    )
    @ApiResponse(responseCode = "200", description = "Achievement deleted successfully")
    public void deleteAchievement(
            @Parameter(description = "ID of the achievement to be deleted")
            @PathVariable Long achievementId) {
        achievementService.deleteAchievement(achievementId);
    }

    @PutMapping
    @Operation(
            summary = "Update an Achievement",
            description = "This endpoint allows updating an existing achievement using its ID."
    )
    @ApiResponse(responseCode = "200", description = "Achievement updated successfully")
    public void updateAchievement(
            @Parameter(description = "ID of the achievement to be updated")
            @RequestParam Long achievementId,
            @Parameter(description = "Updated achievement details")
            @RequestBody AchievementRequest achievementDto) {
        achievementService.updateAchievement(achievementId, achievementDto);
    }

    @GetMapping("/{userId}")
    @Operation(
            summary = "Get Achievements for a User",
            description = "This endpoint retrieves all achievements associated with the user specified by `userId`."
    )
    @ApiResponse(responseCode = "200", description = "List of achievements retrieved successfully")
    public List<AchievementResponse> getForUserId(
            @Parameter(description = "ID of the user to retrieve achievements for")
            @PathVariable Long userId) {
        return achievementService.getForUserId(userId);
    }
}