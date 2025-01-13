package org.example.flashcardbe.service;

import lombok.AllArgsConstructor;
import org.example.flashcardbe.model.StudySession;
import org.example.flashcardbe.repository.StudySessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudySessionService {

    private StudySessionRepository studySessionRepository;

    public StudySession startStudySession(StudySession studySession) {
        return studySessionRepository.save(studySession);
    }

    public void endStudySession(Long sessionId) {
        Optional<StudySession> session = studySessionRepository.findById(sessionId);
        session.ifPresent(s -> {
            s.setEndTime(java.time.LocalDateTime.now());
            studySessionRepository.save(s);
        });
    }

    public List<StudySession> getStudySessionsByUserId(Long userId) {
        return studySessionRepository.findByUser_UserId(userId);
    }

    public List<StudySession> getActiveStudySessions(Long userId) {
        return studySessionRepository.findByUser_UserIdAndEndTimeIsNull(userId);
    }
}