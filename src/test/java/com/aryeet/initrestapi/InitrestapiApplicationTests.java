package com.aryeet.initrestapi;

import com.aryeet.initrestapi.service.GrepWordService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

    @Test
    @DisplayName("Two same length longest word should return both words")
    void multipleLargestWordsInSentence() {

        Map<String, Integer> actualFoundWord = grepWordService.getLongestWord("The monkey and cow and jumped over the moon");


        assertTrue(actualFoundWord.keySet().contains("jumped"), "finding if jumped return by method");
        assertTrue(actualFoundWord.keySet().contains("monkey"), "finding if monkey return by method");

        actualFoundWord.entrySet().stream()
                .forEach(word -> {
                    assertThat(word.getValue(), Matchers.is(6));
                });

    }

}
