package com.beginsecure.domain;

import java.util.*;

public class WordListProcessor {
    public List<String> processList(String input) {
        ArrayList<String> words = new ArrayList<>();
        List<String> wordsInLine = Arrays.asList(input.split("[, ?.@]+"));
        for (String word : wordsInLine) {
            if (word.length() > 30) {
                word = word.substring(0, 30);
            }
            Boolean correct = true;
            if (words.contains(word) || word.isEmpty()) {
                correct = false;
            }
            if (correct) {
                words.add(word);
            }
        }
        Comparator<String> stringLengthComparator = new StringLengthSort();
        Collections.sort(words, stringLengthComparator);
        return words;
    }
}
