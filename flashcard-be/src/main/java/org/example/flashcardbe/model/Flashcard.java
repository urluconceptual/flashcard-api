package org.example.flashcardbe.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "flashcards")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long flashcardId;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String definition;

    @Column(nullable = false)
    private int difficulty;
}