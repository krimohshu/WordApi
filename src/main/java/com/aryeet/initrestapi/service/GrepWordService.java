package com.aryeet.initrestapi.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GrepWordService {

    /**
     * @param sentence
     * @return Map of longest word and its length
     * @author Krishan Shukla
     * @since 1.0
     */
    public Map<String, Integer> getLongWord(String sentence) {

        Map<String, Integer> wordMap = new HashMap<>();
        String[] currentWord = sentence.split(" ");
        String longword = " ";

        for (int i = 0; i < currentWord.length; i++) {

            if (currentWord[i].length() >= longword.length()) {

                longword = currentWord[i];
            }
        }

        System.out.println("Longest word is " + longword + " and Its length is  " + longword.length() + " char");
        wordMap.put(longword, longword.length());

        return wordMap;
    }

    /**
     * @param sentence
     * @return Map of shortest word and its length
     * @author Krishan Shukla
     * @since 1.0
     */
    public Map<String, Integer> getShotestWord(String sentence) {

        Map<String, Integer> wordMap = new HashMap<>();

        Set<String> setOfSmallestNumber = Arrays.asList(sentence.split(" "))
                .stream()
                .sorted((e2, e1) -> e1.length() > e2.length() ? -1 : 1)
                .collect(Collectors.toSet());

        setOfSmallestNumber.stream().forEach(word -> {
            wordMap.put(word ,word.length());
        });

        return wordMap;
    }


}
