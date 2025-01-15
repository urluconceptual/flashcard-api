package org.example.flashcardbe.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.val;
import org.example.flashcardbe.dto.*;
import org.example.flashcardbe.mapper.QuizFlashcardMapper;
import org.example.flashcardbe.model.AppUser;
import org.example.flashcardbe.model.Flashcard;
import org.example.flashcardbe.model.Quiz;
import org.example.flashcardbe.repository.FlashcardRepository;
import org.example.flashcardbe.repository.QuizFlashcardRepository;
import org.example.flashcardbe.repository.QuizRepository;
import org.example.flashcardbe.repository.UserRepository;
import org.example.flashcardbe.service.random.Randomizer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuizService {

    private QuizRepository quizRepository;
    private QuizFlashcardRepository quizFlashcardRepository;
    private UserRepository userRepository;
    private FlashcardRepository flashcardRepository;

    private static void validateUserList(List<AppUser> appUserList) {
        if (appUserList.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        if (appUserList.size() > 1) {
            throw new IllegalArgumentException("Multiple users found");
        }
    }

    @Transactional
    public QuizAnswerResponse createQuiz(QuizRequest quizRequestDto) {
        List<AppUser> appUserList = this.userRepository.findByUserId(quizRequestDto.getUserId());

        validateUserList(appUserList);

        Quiz quiz = Quiz.builder()
                .user(appUserList.getFirst())
                .createdAt(LocalDateTime.now())
                .build();

        val quizFlashcardResponse = this.getRandomFlashcard(quizRequestDto.getDifficulty(),
                appUserList.getFirst().getUserId());

        val modelQuizFlashcardList = quizFlashcardResponse.getList().stream()
                .map(quizFlashcard -> QuizFlashcardMapper
                        .toEntity(quizFlashcard, quiz, this.flashcardRepository))
                .toList();

        quiz.setQuizFlashcards(modelQuizFlashcardList);
        quiz.setTotalQuestions(modelQuizFlashcardList.size());

        Quiz savedQuiz = this.quizRepository.save(quiz);
        this.quizFlashcardRepository.saveAll(modelQuizFlashcardList);

        quizFlashcardResponse.setQuizId(savedQuiz.getQuizId());

        return quizFlashcardResponse;
    }

    public QuizAnswerResponse getRandomFlashcard(Integer difficulty, Long userId) {
        List<Flashcard> flashcardList = this.flashcardRepository
                .getFlashcardByDifficultyAndCollection_User_UserId(difficulty, userId);

        List<String> answerList = flashcardList.stream().map(Flashcard::getDefinition).collect(Collectors.toList());

        Collections.shuffle(flashcardList);

        List<QuizFlashcardResponse> flashcardResponseList = flashcardList.stream()
                .map(QuizFlashcardMapper::toDto)
                .toList();


        flashcardResponseList.forEach(response -> {
            response.getAnswerVariantList().addAll(Randomizer.getRandomElements(
                    new ArrayList<>(answerList.stream()
                            .filter(e -> !response.getAnswerVariantList().getFirst().equals(e))
                            .toList())
            ));
            Collections.shuffle(response.getAnswerVariantList());
        });

        return QuizAnswerResponse.builder()
                .list(flashcardResponseList)
                .build();
    }

    public QuizResponse submitQuiz(QuizAnswerRequest quizAnswerRequestDto) {
        Optional<Quiz> optionalQuiz = this.quizRepository.findQuizByQuizId(quizAnswerRequestDto.getQuizId());

        if (optionalQuiz.isEmpty()) throw new RuntimeException("Quiz not found.");

        Quiz quiz = optionalQuiz.get();

        val answers = quizAnswerRequestDto.getQuizFlashcardRequestList();

        LongAdder adder = new LongAdder();

        answers.forEach(answer -> {
            adder.add(checkAnswer(answer) ? 1 : 0);
        });

        quiz.setCompletedAt(LocalDateTime.now());
        quiz.setScore(adder.intValue());

        this.quizRepository.save(quiz);

        return QuizResponse.builder()
                .quizId(quizAnswerRequestDto.getQuizId())
                .userId(quiz.getUser().getUserId())
                .createdAt(quiz.getCreatedAt())
                .completedAt(quiz.getCompletedAt())
                .score(adder.intValue())
                .flashcardNumber(quiz.getTotalQuestions())
                .build();
    }

    public boolean checkAnswer(QuizFlashcardRequest answer) {
        Optional<Flashcard> flashcard = this.flashcardRepository.getFlashcardByFlashcardId(answer.getFlashcardId());

        if (flashcard.isEmpty()) throw new RuntimeException("Invalid flashcard ID when answering quiz.");

        return answer.getAnswer().equals(flashcard.get().getDefinition());
    }
}