package org.coldfocus;

public class Word {
    private final String original;
    private StringBuilder guessed;

    public Word(String word) {
        this.original = word.toLowerCase();
        this.guessed = new StringBuilder("*".repeat(word.length()));
    }

    public boolean tryGuess(char letter) {
        boolean found = false;
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == letter && guessed.charAt(i) == '*') {
                guessed.setCharAt(i, letter);
                found = true;
            }
        }
        return found;
    }

    public boolean isGuessed() {
        return !guessed.toString().contains("*");
    }

    public String getGuessedWord() {
        return guessed.toString();
    }

    public String getOriginal() {
        return original;
    }
}