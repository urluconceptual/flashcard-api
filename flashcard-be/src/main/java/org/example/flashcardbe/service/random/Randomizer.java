package org.example.flashcardbe.service.random;

import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class Randomizer {
    public <T> List<T> getRandomElements(List<T> list) {
        Collections.shuffle(list);
        return list.subList(0, 2);
    }
}
