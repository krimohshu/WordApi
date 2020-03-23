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
		Map<String , Integer> actualFoundWord = grepWordService.getLongWord("The cow jumped over the moon.");

		actualFoundWord.entrySet().stream()
				.forEach(word -> {
					assertEquals(word.getKey(), "jumped");
					assertEquals(word.getValue(), 6);
				});
	}

	@Test
	@DisplayName("A positive test to get shortest word in sentence")
	void getShortestWordWithLength() {
		Map<String , Integer> actualFoundWord = grepWordService.getShotestWord("The cow jumped over moon.");

		actualFoundWord.entrySet().stream()
				.forEach(word -> {
					assertEquals(word.getKey(), "the");
					assertEquals(word.getValue(), 6);
				});

	}




}
