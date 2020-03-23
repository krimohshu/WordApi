package com.aryeet.initrestapi;

import com.aryeet.initrestapi.service.GrepWordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InitrestapiApplicationTests {

    @LocalServerPort
    private int port;

    @InjectMocks
    private GrepWordService grepWordService;

    @Test
    @DisplayName("A positive test to get longest word in sentence")
    void getLongestWordWithLength() {
        Map<String, Integer> actualFoundWord = grepWordService.getLongestWord("The cow jumped over the moon.");

        actualFoundWord.entrySet().stream()
                .forEach(word -> {
                    assertEquals("jumped", word.getKey());
                    assertEquals(6, word.getValue());
                });
    }

    @Test
    @DisplayName("A positive test to get shortest word in sentence")
    void getShortestWordWithLength() {
        Map<String, Integer> actualFoundWord = grepWordService.getShortestWord("The cow jumped over moon.");

        actualFoundWord.entrySet().stream()
                .forEach(word -> {
                    assertEquals("The", word.getKey());
                    assertEquals(3, word.getValue());
                });
    }

    @Test
    @DisplayName("Verify null input for getLongestWord method")
    void manageNullSentence() {

        Map<String, Integer> actualFoundWord = grepWordService.getLongestWord(null);

        actualFoundWord.entrySet().stream()
                .forEach(word -> {
                    assertEquals("Please provide the non-null Input", word.getKey());
                    assertEquals(0, word.getValue());
                });

    }

    @Test
    @DisplayName("Verify Empty input for getLongestWord method")
    void manageEmptySentence() {

        Map<String, Integer> actualFoundWord = grepWordService.getLongestWord("");

        actualFoundWord.entrySet().stream()
                .forEach(word -> {
                    assertEquals("Please provide the non-Empty Input", word.getKey());
                    assertEquals(0, word.getValue());
                });

    }


}
