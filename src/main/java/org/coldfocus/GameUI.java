package org.coldfocus;

import java.io.IOException;
import java.util.Scanner;

public class GameUI {
    String encoding = System.console() != null ? "CP866" : "UTF-8";//консоль винды или консоль IDE
    private final Scanner scanner = new Scanner(System.in, encoding);

    public char getLetter(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.length() == 0) return ' ';
        return input.charAt(0);
    }

    public void print(String message) {
        System.out.println(message);
    }

    public boolean askToPlayAgain() {
        System.out.println("Сыграть ещё? (y/n)");
        return scanner.nextLine().trim().toLowerCase().startsWith("y");
    }

    public void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}