package org.example.flashcardbe.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.flashcardbe.dto.FlashcardRequest;
import org.example.flashcardbe.dto.FlashcardResponse;
import org.example.flashcardbe.mapper.FlashcardMapper;
import org.example.flashcardbe.model.Collection;
import org.example.flashcardbe.model.Flashcard;
import org.example.flashcardbe.repository.CollectionRepository;
import org.example.flashcardbe.repository.FlashcardRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlashcardService {
    private final FlashcardRepository flashcardRepository;
    private final CollectionRepository collectionRepository;

    public List<FlashcardResponse> createFlashcards(List<FlashcardRequest> requestDTO) {
        return requestDTO.stream().map(this::createFlashcard).toList();
    }

    public FlashcardResponse createFlashcard(FlashcardRequest requestDTO) {
        Collection collection = collectionRepository.findById(requestDTO.getCollectionId())
                .orElseThrow(() -> new ObjectNotFoundException(Collection.class,
                        String.valueOf(requestDTO.getCollectionId())));

        Flashcard flashcard = FlashcardMapper.toEntity(requestDTO, collection);
        Flashcard savedFlashcard = flashcardRepository.save(flashcard);
        return FlashcardMapper.toResponseDTO(savedFlashcard);
    }

    public List<FlashcardResponse> getFlashcardsByCollectionId(Long collectionId) {
        return flashcardRepository.findByCollection_CollectionId(collectionId)
                .stream().map(FlashcardMapper::toResponseDTO).toList();
    }

    public boolean deleteFlashcard(Long flashcardId) {
        if (this.flashcardRepository.findById(flashcardId).isEmpty()) {
            return false;
        }

        flashcardRepository.deleteById(flashcardId);
        return true;
    }

    @Transactional
    public boolean updateFlashcardById(FlashcardRequest flashcardRequest, Long flashcardId) {
        if (this.flashcardRepository.findById(flashcardId).isEmpty()) {
            return false;
        }

        this.flashcardRepository.updateFlashcardById(flashcardRequest.getTerm(), flashcardRequest.getDefinition(),
                flashcardRequest.getDifficulty(), flashcardId);

        return true;
    }

    public List<FlashcardResponse> importFlashcards(MultipartFile file) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> rows = csvReader.readAll();
            List<FlashcardRequest> flashcards = new ArrayList<>();
            boolean isFirstRow = true;

            for (String[] row : rows) {
                if (row.length < 4) {
                    continue;
                }
                if (isFirstRow) {
                    isFirstRow = false;  // Skip the first row
                    continue;  // Skip this iteration and move to the next row
                }
                FlashcardRequest flashcardRequest =
                        FlashcardRequest.builder().term(row[0]).definition(row[1]).difficulty(Integer.parseInt(row[2]))
                                .collectionId(Long.parseLong(row[3])).build();
                flashcards.add(flashcardRequest);
            }
            return this.createFlashcards(flashcards);
        }
    }
}