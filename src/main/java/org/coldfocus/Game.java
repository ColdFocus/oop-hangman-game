package org.coldfocus;

import java.io.IOException;

public class Game {
    public static void main(String[] args) {
        try {
            new HangmanGame().start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}