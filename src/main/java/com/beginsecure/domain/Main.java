package com.beginsecure.domain;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserInput userInput = new ConsoleInput();
        String input = userInput.getInput();

        WordListProcessor wordListProcessor = new WordListProcessor();
        List<String> words = wordListProcessor.processList(input);

        for (String word : words) {
            System.out.println(word);
        }
    }
}
