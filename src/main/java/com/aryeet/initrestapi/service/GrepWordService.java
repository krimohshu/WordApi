package com.aryeet.initrestapi.service;

import exceptions.IncorrectInputException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GrepWordService {

    Comparator<String> byLength = (e1, e2) -> e1.length() > e2.length() ? -1 : 1;

    /**
     * @param sentence
     * @return Map of longest word and its length
     * @author Krishan Shukla
     * @since 1.0
     */
    public Map<String, Integer> getLongestWord(String sentence) {

        Map<String, Integer> wordMap = new HashMap<>();
        String errorMessage = "";

        if (!(sentence == null || sentence.isEmpty() || sentence.split(" ").length == 0)) {

            String[] arrSentenceSplitBySpace = sentence.replaceAll("[-+<>//.^:,]", "").split(" ");

            Integer lenghtOfWord = Arrays.asList(arrSentenceSplitBySpace).stream()
                    .sorted(byLength)
                    .map(x -> x.replace(".", ""))
                    .findFirst().get().length();

            List<String> wordList = Arrays.asList(sentence.split(" ")).stream()
                    .sorted(byLength)
                    .map(x -> x.replace(".", ""))
                    .filter(longestWordLength -> longestWordLength.length() == lenghtOfWord)
                    .collect(Collectors.toList());

            wordList.stream()
                    .forEach(word -> {
                        wordMap.put(word, word.length());

                    });

        } else {
            wordMap.put(errorMessageParser(sentence), 0);
        }

        return wordMap;
    }

    /**
     * @param sentence
     * @return Map of shortest word and its length
     * @author Krishan Shukla
     * @since 1.0
     */
    public Map<String, Integer> getShortestWord(String sentence) {

        Map<String, Integer> wordMap = new HashMap<>();
        if (sentence != null) {
            Optional<String> shortest = Arrays.asList(sentence.split(" ")).stream()
                    .sorted(byLength.reversed()).findFirst();

            wordMap.put(shortest.get(), shortest.get().length());
        } else {
            wordMap.put("Please provide the non-null Input", 0);
        }
        return wordMap;
    }

    private String errorMessageParser(Object sentence) {

        if (sentence == null) return new IncorrectInputException("Please provide the non-null Input").toString();

        if (sentence.toString().equalsIgnoreCase(""))
            return new IncorrectInputException("Please provide the non-Empty Input").toString();

        if (sentence.toString().split(" ").length == 0)
            return new IncorrectInputException("You have wrongly provided space-only input").toString();

        return "No suitable error code found";

    }

}
