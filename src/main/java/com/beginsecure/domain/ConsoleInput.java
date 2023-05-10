package com.beginsecure.domain;

import java.util.Scanner;

public class ConsoleInput implements UserInput {
    @Override
    public String getInput() {
        Scanner userInputScanner = new Scanner(System.in);
        System.out.println("Please enter a list of words separated by commas: ");
        String userInput = userInputScanner.nextLine();
        userInputScanner.close();
        return userInput;
    }
}
