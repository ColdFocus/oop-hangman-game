package org.coldfocus;

import java.io.IOException;
import java.util.Scanner;

public class GameUI {
    String encoding = System.console() != null ? "CP866" : "UTF-8";//консоль винды или консоль IDE
    private final Scanner scanner = new Scanner(System.in, encoding);

    public char getLetter(String prompt) throws InterruptedException {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.length() != 1) {
                System.out.println("Введите только один символ!");
                continue;
            }

            char letter = input.charAt(0);

            if (!Character.isLetter(letter)) {
                System.out.println("Это не буква. Попробуйте снова.");
                continue;
            }

            if (Character.UnicodeBlock.of(letter) != Character.UnicodeBlock.CYRILLIC) {
                System.out.println("Разрешено вводить только русские буквы!");
                Thread.sleep(1000);
                continue;
            }

            return letter;
        }
    }

    public void print(String message) {
        System.out.println(message);
    }

    public boolean askToPlayAgain() {
        System.out.println("Сыграем ещё? Нажми 'y' для начала игры или любой другой символ для выхода");
        return scanner.nextLine().trim().toLowerCase().startsWith("y");
    }

    public void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}