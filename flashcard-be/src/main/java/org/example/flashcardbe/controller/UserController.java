package org.example.flashcardbe.controller;

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
public class UserController {
    private final UserService userService;

    @GetMapping(path="/check-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> checkToken(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.checkToken(token));
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AppUserRequest request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @PostMapping
    public ResponseEntity<AuthResponse> createUser(@RequestBody AppUserRequest request) {
        return ResponseEntity.ok(userService.saveUser(request));
    }

    @GetMapping
    public ResponseEntity<AppUserResponse> getUserById(@RequestBody Long userId) {
        AppUser user = userService.getUserById(userId);
        return ResponseEntity.ok(AppUserMapper.toDTO(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
