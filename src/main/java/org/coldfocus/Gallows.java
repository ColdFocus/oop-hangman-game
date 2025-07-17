package org.coldfocus;

public class Gallows {
    private int state;


    private final String[] stages = {
            "_______\n" +
                    "|/   |\n" +
                    "|\n" +
                    "|\n" +
                    "|\n" +
                    "|",

            "_______\n" +
                    "|/   |\n" +
                    "|    O\n" +
                    "|\n" +
                    "|\n" +
                    "|",

            "_______\n" +
                    "|/   |\n" +
                    "|    O\n" +
                    "|    |\n" +
                    "|\n" +
                    "|",

            "_______\n" +
                    "|/   |\n" +
                    "|    O\n" +
                    "|   /|\n" +
                    "|\n" +
                    "|",

            "_______\n" +
                    "|/   |\n" +
                    "|    O\n" +
                    "|   /|\\\n" +
                    "|\n" +
                    "|",

            "_______\n" +
                    "|/   |\n" +
                    "|    O\n" +
                    "|   /|\\\n" +
                    "|   /\n" +
                    "|",

            "_______\n" +
                    "|/   |\n" +
                    "|    O\n" +
                    "|   /|\\\n" +
                    "|   / \\\n" +
                    "|"
    };

    public void wrongGuess() {
        if (state < 6) state++;
    }

    public void print() {
        System.out.println(stages[state]);
    }

    public boolean isDead() {
        return state == 6;
    }
}