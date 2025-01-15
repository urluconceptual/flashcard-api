package org.example.flashcardbe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.flashcardbe.dto.AppUserRequest;
import org.example.flashcardbe.dto.AppUserResponse;
import org.example.flashcardbe.dto.AuthResponse;
import org.example.flashcardbe.mapper.AppUserMapper;
import org.example.flashcardbe.model.AppUser;
import org.example.flashcardbe.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "User management APIs")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Check the validity of a user token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token is valid",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid token", content = @Content)
    })
    @GetMapping(path = "/check-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> checkToken(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.checkToken(token));
    }

    @Operation(summary = "Authenticate and log in the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully logged in",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid login credentials", content = @Content)
    })
    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User login request with username and password")
            @RequestBody @Valid AppUserRequest request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user request", content = @Content)
    })
    @PostMapping
    public ResponseEntity<AuthResponse> createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New user registration request")
            @RequestBody AppUserRequest request) {
        return ResponseEntity.ok(userService.saveUser(request));
    }

    @Operation(summary = "Get user details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched user details",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppUserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<AppUserResponse> getUserById(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User ID for fetching user details")
            @RequestBody Long userId) {
        AppUser user = userService.getUserById(userId);
        return ResponseEntity.ok(AppUserMapper.toDTO(user));
    }

    @Operation(summary = "Delete a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}