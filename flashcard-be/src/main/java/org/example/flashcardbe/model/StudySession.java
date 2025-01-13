package org.example.flashcardbe.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "study_sessions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudySession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long sessionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Column(nullable = false, updatable = false)
    private LocalDateTime startTime = LocalDateTime.now();

    @Column
    private LocalDateTime endTime;
}