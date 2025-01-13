package org.example.flashcardbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StudySessionResponse {
    private Long sessionId;
    private Long userId;
    private Long collectionId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
