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

    @Test
    @DisplayName("Verify space only string (\"    \")")
    void sendOnlySpaceInSentence() {
        Map<String, Integer> actualFoundWord = grepWordService.getLongestWord("      ");

        actualFoundWord.entrySet().stream()
                .forEach(word -> {
                    assertEquals("You have wrongly provided space-only input", word.getKey());
                    assertEquals(0, word.getValue());
                });

    }

    @Test
    @DisplayName("Verify hypenated longest string")
    void hypenatedLongedWordInSentence() {

        Map<String, Integer> actualFoundWord = grepWordService.getLongestWord("the worst in-a-recession year.");


        actualFoundWord.entrySet().stream()
                .forEach(word -> {
                    assertEquals("in-a-recession", word.getKey());
                    assertEquals(14, word.getValue());
                });

    }

    @Test
    @DisplayName("Verify hypenated longest string")
    void onlyhypenatedLongedWordInSentence() {

        Map<String, Integer> actualFoundWord = grepWordService.getLongestWord("the-worst-in-a-recession-year");

        actualFoundWord.entrySet().stream()
                .forEach(word -> {
                    assertEquals("the-worst-in-a-recession-year", word.getKey());
                    assertEquals(29, word.getValue());
                });

    }

    @Test
    @DisplayName("period is not counted in the word jupiter.")
    void periodCharNotCountedInWord() {

        Map<String, Integer> actualFoundWord = grepWordService.getLongestWord("The cow jumped over the jupiter.");

        actualFoundWord.entrySet().stream()
                .forEach(word -> {
                    assertEquals("jupiter", word.getKey());
                    assertEquals(7, word.getValue());
                });

    }

    @Test
    @DisplayName("period is not counted in the word jupiter.")
    void otheLanguageWordInSentence() {

        Map<String, Integer> actualFoundWord = grepWordService.getLongestWord("इंगलैंड कोरोना से लड़ाई जीतेगा");

        actualFoundWord.entrySet().stream()
                .forEach(word -> {
                    assertEquals("इंगलैंड", word.getKey());
                    assertEquals(7, word.getValue());
                });

    }

    @Test
    @DisplayName("This ia XSS <alert>attacking site </alert>")
    void escapeSequenceInTheSentence() {

        Map<String, Integer> actualFoundWord = grepWordService.getLongestWord("This ia XSS <alert>attacking site </alert>");

        actualFoundWord.entrySet().stream()
                .forEach(word -> {
                    assertEquals("alert", word.getKey());
                    assertEquals(7, word.getValue());
                });

    }


}
