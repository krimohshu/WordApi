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

        if (sentence != null) {
            String[] currentWord = sentence.split(" ");
            String longword = " ";

            for (int i = 0; i < currentWord.length; i++) {

                if (currentWord[i].length() >= longword.length()) {

                    longword = currentWord[i];
                }
            }
            wordMap.put(longword, longword.length());
        }

        /*if (sentence != null) {
            Set<String> setOfSmallestNumber = Arrays.asList(sentence.split(" "))
                    .stream()
                    .sorted((e2, e1) -> e1.length()  e2.length() ? -1 : 1)
                    .collect(Collectors.toSet());

            setOfSmallestNumber.stream().forEach(word -> {
                wordMap.put(word, word.length());
            });
        }*/
        else {
            wordMap.put("Please provide the non-null Input", 0);
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
        if(sentence !=null) {
            Optional<String> shortest = Arrays.asList(sentence.split(" ")).stream()
                    .sorted(byLength.reversed()).findFirst();

            wordMap.put(shortest.get(), shortest.get().length());
        }
        else{
            wordMap.put("Please provide the non-null Input", 0);
        }



     /*   if (sentence != null) {
            List<String> setOfSmallestNumber = Arrays.asList(sentence.split(" "))
                    .stream()
                    .sorted((e2, e1) -> e1.length() > e2.length() ? -1 : 1)
                    .collect(Collectors.toList());

            setOfSmallestNumber.stream().forEach(word -> {
                wordMap.put(word, word.length());
            });
        } else {
            wordMap.put("Please provide the non-null Input", 0);
        }*/

        return wordMap;
    }

}
