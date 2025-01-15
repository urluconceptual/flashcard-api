package org.example.flashcardbe.repository;

import org.example.flashcardbe.model.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {
    List<StudySession> findByUser_UserId(Long userId);

    List<StudySession> findByUser_UserIdAndEndTimeIsNull(Long userId);
}