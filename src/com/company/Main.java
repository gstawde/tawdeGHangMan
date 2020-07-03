package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] list = {"hello", "banana", "books", "boba tea", "masquerade", "Djibouti", "Manchester", "Beauty and the Beast"};
        hangMan(list);
    }

    public static void startGame() {

        // Opening Statement
        System.out.println("Welcome to Hang Man!\n\nDo you know how to play?: ");
        // Convert answer to comparable String
        Scanner reader = new Scanner(System.in);
        String yesOrNo = reader.next();
        // Check what the answer is and act accordingly
        if (yesOrNo.toLowerCase().equals("no"))
            System.out.println("Here is how to play:\nYou will be given a random word not to your knowledge that you " +
                    "must guess by letter.\nIf you do not guess a letter within the word, you will lose a life. " +
                    "You have 6 lives total.\nAfter you type in a letter, hit the 'ENTER' key on your keyboard to continue.\nLet's play!\n");
        else if (yesOrNo.toLowerCase().equals("yes"))
            System.out.println("Excellent! Just one thing to keep in mind:\nAfter you type in a letter, hit the 'ENTER'" +
                    " key on your keyboard to continue.\nOkay let's play!\n");

    } // end startGame

    public static void hangMan(String[] wordList) {

        // Start Game with introduction
        startGame();

        // Pick random word to be used for the game given the wordList parameter
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a number between 1 and " + (wordList.length) + " so we can select a random word for " +
                "you to guess: ");
        int num = reader.nextInt() - 1;
        String originalWordForGame = wordList[num];
        String wordForGame = wordList[num].toLowerCase();

        /* Create and int to track number of lives, a boolean to track if they have won, a String to track letters
        guessed, and an array to track what parts
        of the word have been guessed correctly or have not been guessed yet. */
        int livesLeft = 6;
        boolean winner = false;
        String guessedLetters = "";
        String[] wordChosen = new String[wordForGame.length()];
        for (int i = 0; i < wordChosen.length;i++) {
            if (wordForGame.substring(i,i+1).equals(" "))
                wordChosen[i] = "  ";
            else
                wordChosen[i] = "__";
        }

        // Begin Game
        while (livesLeft != 0 && !winner) {

            // Print current "board", including number of lives, letters guessed, and current hangman spaces
            printCurrentBoard(livesLeft,guessedLetters,wordChosen);

            // Take in user's guess
            System.out.println("Guess a letter: ");
            String userInput = reader.next();

            // Check if user inputted a letter they have already guessed
            if (guessedLetters.contains(userInput))
                System.out.println("You can't guess the same letter twice!! Try again!");
            else { // If user has not duplicated, proceed with check
                //Check if guessed letter is in word
                if (isInWord(userInput, wordForGame)) {
                    System.out.println("\nWow you guessed correct! Keep going!");
                    guessedLetters += userInput + " ";
                    for (int i = 0; i < wordChosen.length; i++) {
                        if (wordForGame.substring(i, i + 1).equals(userInput))
                            wordChosen[i] = userInput;
                    }
                    winner = isWinner(wordChosen);
                } else {
                    System.out.println("\nOh no you guessed incorrect! Keep trying!");
                    guessedLetters += userInput + " ";
                    livesLeft --;
                }
            }
        }

        // Print appropriate sentence for if winner or loser.
        if (winner == true) {
            System.out.println("\nYay you won! The word indeed was '" + originalWordForGame + "'.");
            printFinalBoard(livesLeft,guessedLetters,wordChosen);
        }
        else {
            System.out.println("\nYou lose! The correct word was '" + originalWordForGame + "'.");
            printFinalBoard(livesLeft,guessedLetters,wordChosen);
        }

    } // end hangMan

    public static boolean isInWord(String input, String correctWord){

        // Check if input is in the word
        if (correctWord.contains(input))
            return true;
        return false;

    } // end isInWord

    public static void printCurrentBoard(int livesLeft, String guessedLetters, String[] wordChosen) {

        /* Print the current board, which includes the lives left, letters guessed, and the current spaces and/or letters
        on the "board" */
        System.out.println("Here is the current board:");
        System.out.println("Number of Lives Left: " + livesLeft);
        System.out.println("Letters Guessed: " + guessedLetters);
        for (int i = 0; i < wordChosen.length; i++) {
            if (i != wordChosen.length - 1)
                System.out.print(wordChosen[i] + " ");
            else
                System.out.println(wordChosen[i]);
        }

    } // end printCurrentBoard

    public static boolean isWinner(String[] wordChosen) {

        String checker = "";
        for (int i = 0; i < wordChosen.length; i++)
            checker += wordChosen[i];
        if (!checker.contains("__"))
            return true;
        return false;

    } // end isWinner

    public static void printFinalBoard(int livesLeft, String guessedLetters, String[] wordChosen) {

        /* Print the final board, which includes the lives left, letters guessed, and the current spaces and/or letters
        on the "board" regardless of whether or not they lost. */
        System.out.println("Here is final board:");
        System.out.println("Number of Lives Left: " + livesLeft);
        System.out.println("Letters Guessed: " + guessedLetters);
        System.out.print("WORD: ");
        for (int i = 0; i < wordChosen.length; i++) {
            if (i != wordChosen.length - 1)
                System.out.print(wordChosen[i] + " ");
            else
                System.out.println(wordChosen[i]);
        }

    } // end printFinalBoard
}
