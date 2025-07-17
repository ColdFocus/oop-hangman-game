package org.coldfocus;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangmanGame {
    private final List<String> words = new ArrayList<>();
    private final Random random = new Random();
    private final GameUI ui = new GameUI();
    public void start() throws IOException, InterruptedException {
        loadWords();
        printGreeting();
        Thread.sleep(1500);
        do {
            playGame();
        } while (ui.askToPlayAgain());
    }



    private void playGame() throws IOException, InterruptedException {
        String hiddenWord = words.get(random.nextInt(words.size()));
        Word word = new Word(hiddenWord);
        Gallows gallows = new Gallows();
        StringBuilder wrongLetters = new StringBuilder();

        while (true) {
            ui.clearScreen();
            gallows.print();
            ui.print(word.getGuessedWord());
            if (wrongLetters.length() > 0) {
                ui.print("Неверные буквы: " + wrongLetters);
            }

            char letter = ui.getLetter("Введите букву:");

            if (!Character.isLetter(letter) || Character.UnicodeBlock.of(letter) != Character.UnicodeBlock.CYRILLIC) {
                ui.print("Введите русскую букву!");
                Thread.sleep(1000);
                continue;
            }

            if (word.tryGuess(letter)) {
                if (word.isGuessed()) {
                    ui.clearScreen();
                    ui.print("Вы угадали слово: " + word.getOriginal());
                    printCongrats();
                    break;
                }
            } else {
                if (wrongLetters.indexOf(String.valueOf(letter)) == -1) {
                    wrongLetters.append(letter).append(" ");
                    gallows.wrongGuess();
                    if (gallows.isDead()) {
                        ui.clearScreen();
                        gallows.print();
                        ui.print("Вы проиграли! Слово было: " + word.getOriginal());
                        break;
                    }
                } else {
                    ui.print("Вы уже вводили эту букву.");
                    Thread.sleep(1000);
                }
            }
        }
    }

    private void loadWords() throws IOException {
        if (!words.isEmpty()) words.clear();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("nouns.txt"), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 5) {
                    words.add(line);
                }
            }
        }
    }



    private void printGreeting() {
        System.out.print(
                "||   ||       ||       ||\\   ||   ||||||||   ||\\    /||       ||       ||\\   ||\n" +
                        "||   ||      ||||      || \\  ||   ||    ||   || \\  / ||      ||||      || \\  ||\n" +
                        "|||||||     ||  ||     ||  \\ ||   ||         ||  \\/  ||     ||  ||     ||  \\ ||\n" +
                        "||   ||    ||    ||    ||   \\||   ||  ||||   ||      ||    ||    ||    ||   \\||\n" +
                        "||   ||   ||      ||   ||    ||   ||||||||   ||      ||   ||      ||   ||    ||\n"
        );
    }

    static void printCongrats() {
        System.out.print(
                "                                 \\ /                 \n" +
                        "                        \\ /     --0--                \n" +
                        "  \\ /       \\ /        --O--     / \\     \\ /      \n" +
                        " --O--     --O--        / \\      /      --O--        \n" +
                        "  / \\       / \\          |      /        / \\       \n" +
                        "      \\       \\          |     /        /           \n" +
                        "       |       |              /        /              \n" +
                        "       |       |                                      \n" +
                        "\n" +
                        "\\\\  //  ||===||  ||   ||    \\\\              //  ||===||  ||\\   ||   ||\n" +
                        " \\\\//   ||   ||  ||   ||     \\\\    //\\\\    //   ||   ||  || \\  ||   ||\n" +
                        "  ||    ||   ||  ||   ||      \\\\  //  \\\\  //    ||   ||  ||  \\ ||       \n" +
                        "  ||    ||===||  ||===||       \\\\//    \\\\//     ||===||  ||   \\||   ||\n"
        );
    }

}