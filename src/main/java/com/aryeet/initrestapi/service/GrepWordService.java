package com.aryeet.initrestapi.service;

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

        if (!(sentence == null || sentence.isEmpty())) {

            Optional<String> shortest = Arrays.asList(sentence.split(" ")).stream()
                    .sorted(byLength).findFirst();

            wordMap.put(shortest.get(), shortest.get().length());

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

        if (sentence == null) return "Please provide the non-null Input";
        if (sentence.toString().equalsIgnoreCase("")) return "Please provide the non-Empty Input";


        return "No suitable error code found";

    }

}
