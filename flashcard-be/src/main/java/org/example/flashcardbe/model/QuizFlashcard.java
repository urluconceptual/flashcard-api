package org.example.flashcardbe.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quiz_flashcards")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizFlashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long quizFlashcardId;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "flashcard_id", nullable = false)
    private Flashcard flashcard;

    @Column
    private boolean isCorrect;

    @Column
    private String userAnswer;
}