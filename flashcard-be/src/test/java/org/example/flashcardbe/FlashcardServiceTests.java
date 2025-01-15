package org.example.flashcardbe;

import com.opencsv.exceptions.CsvException;
import org.example.flashcardbe.dto.FlashcardRequest;
import org.example.flashcardbe.dto.FlashcardResponse;
import org.example.flashcardbe.model.Collection;
import org.example.flashcardbe.model.Flashcard;
import org.example.flashcardbe.repository.CollectionRepository;
import org.example.flashcardbe.repository.FlashcardRepository;
import org.example.flashcardbe.service.FlashcardService;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlashcardServiceTests {

    @Mock
    private FlashcardRepository flashcardRepository;

    @Mock
    private CollectionRepository collectionRepository;

    @InjectMocks
    private FlashcardService flashcardService;

    private FlashcardRequest validFlashcardRequest;
    private Collection mockCollection;

    @BeforeEach
    void setUp() {
        validFlashcardRequest = FlashcardRequest.builder()
                .term("Java")
                .definition("A programming language")
                .difficulty(3)
                .collectionId(1L)
                .build();

        mockCollection = new Collection();
        mockCollection.setCollectionId(1L);
    }

    @Test
    void createFlashcard_shouldReturnFlashcardResponse_whenValidRequest() {
        Flashcard mockFlashcard = new Flashcard();
        mockFlashcard.setFlashcardId(1L);
        mockFlashcard.setTerm("Java");
        mockFlashcard.setDefinition("A programming language");
        mockFlashcard.setDifficulty(3);
        mockFlashcard.setCollection(mockCollection);

        when(collectionRepository.findById(validFlashcardRequest.getCollectionId())).thenReturn(
                Optional.of(mockCollection));
        when(flashcardRepository.save(any(Flashcard.class))).thenReturn(mockFlashcard);

        FlashcardResponse response = flashcardService.createFlashcard(validFlashcardRequest);

        assertNotNull(response);
        assertEquals(1L, response.getFlashcardId());
        assertEquals("Java", response.getTerm());
        assertEquals("A programming language", response.getDefinition());
    }

    @Test
    void createFlashcard_shouldThrowObjectNotFoundException_whenCollectionNotFound() {
        when(collectionRepository.findById(validFlashcardRequest.getCollectionId())).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> flashcardService.createFlashcard(validFlashcardRequest));
    }

    @Test
    void getFlashcardsByCollectionId_shouldReturnListOfFlashcards() {
        Flashcard mockFlashcard = new Flashcard();
        mockFlashcard.setFlashcardId(1L);
        mockFlashcard.setTerm("Java");
        mockFlashcard.setDefinition("A programming language");
        mockFlashcard.setDifficulty(3);
        mockFlashcard.setCollection(mockCollection);

        when(flashcardRepository.findByCollection_CollectionId(validFlashcardRequest.getCollectionId()))
                .thenReturn(List.of(mockFlashcard));

        List<FlashcardResponse> flashcardResponses =
                flashcardService.getFlashcardsByCollectionId(validFlashcardRequest.getCollectionId());

        assertNotNull(flashcardResponses);
        assertEquals(1, flashcardResponses.size());
        assertEquals("Java", flashcardResponses.get(0).getTerm());
    }

    @Test
    void deleteFlashcard_shouldReturnTrue_whenFlashcardExists() {
        when(flashcardRepository.findById(1L)).thenReturn(Optional.of(new Flashcard()));

        boolean result = flashcardService.deleteFlashcard(1L);

        assertTrue(result);
        verify(flashcardRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteFlashcard_shouldReturnFalse_whenFlashcardNotFound() {
        when(flashcardRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = flashcardService.deleteFlashcard(1L);

        assertFalse(result);
    }

    @Test
    void updateFlashcardById_shouldReturnTrue_whenFlashcardExists() {
        Flashcard mockFlashcard = new Flashcard();
        when(flashcardRepository.findById(1L)).thenReturn(Optional.of(mockFlashcard));
        doNothing().when(flashcardRepository).updateFlashcardById(anyString(), anyString(),
                anyInt(), anyLong());

        boolean result = flashcardService.updateFlashcardById(validFlashcardRequest, 1L);

        assertTrue(result);
        verify(flashcardRepository, times(1)).updateFlashcardById(anyString(), anyString(),
                anyInt(), eq(1L));
    }

    @Test
    void updateFlashcardById_shouldReturnFalse_whenFlashcardNotFound() {
        when(flashcardRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = flashcardService.updateFlashcardById(validFlashcardRequest, 1L);

        assertFalse(result);
    }

    @Test
    void importFlashcards_shouldReturnListOfFlashcardResponses_whenValidFile()
            throws CsvException, IOException {
        Flashcard mockFlashcard = new Flashcard();
        mockFlashcard.setFlashcardId(1L);
        mockFlashcard.setTerm("Java");
        mockFlashcard.setDefinition("A programming language");
        mockFlashcard.setDifficulty(3);
        mockFlashcard.setCollection(mockCollection);

        when(collectionRepository.findById(validFlashcardRequest.getCollectionId())).thenReturn(
                Optional.of(mockCollection));
        when(flashcardRepository.save(any(Flashcard.class))).thenReturn(mockFlashcard);

        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getInputStream()).thenReturn(getClass().getResourceAsStream("/example.csv"));
        when(collectionRepository.findById(validFlashcardRequest.getCollectionId())).thenReturn(
                Optional.of(mockCollection));

        List<FlashcardResponse> flashcardResponses = flashcardService.importFlashcards(mockFile);

        assertNotNull(flashcardResponses);
        assertFalse(flashcardResponses.isEmpty());
    }
}