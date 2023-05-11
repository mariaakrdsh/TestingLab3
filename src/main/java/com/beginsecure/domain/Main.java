package com.beginsecure.domain;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        WordListProcessor wordListProcessor = new WordListProcessor();
        List<String> words = wordListProcessor.processList();

        for (String word : words) {
            System.out.println(word);
        }
    }
}
