package miniprojects.hangman_game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String filePath = "miniprojects/hangman_game/words.txt";

        List<String> words = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        }
        catch (FileNotFoundException ex) {
            System.out.printf("File not found on the path: %s", filePath);
        }
        catch(IOException ex) {
            System.out.println("Something went wrong");
        }

        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));

        System.out.println(word);

        Scanner sc = new Scanner(System.in);

        List<Character> wordState = new ArrayList<>();
        int wrongGuesses = 0;

        for(int i = 0; i < word.length(); i++) {
            wordState.add('_');
        }

        System.out.println("**************************");
        System.out.println("WELCOME TO JAVA HANGMAN!");
        System.out.println("**************************");

        while(wrongGuesses < 6) {

            System.out.print(getHangmanArt(wrongGuesses));

            System.out.print("Word: ");
            for(char ch : wordState) {
                System.out.print(ch + " ");
            }

            System.out.println();

            System.out.print("Guess a letter: ");
            char guess = sc.next().toLowerCase().charAt(0);

            if(word.indexOf(guess) >= 0) {
                System.out.println("Correct Guess!");

                for(int i = 0; i < word.length(); i++) {
                    if(word.charAt(i) == guess) {
                        wordState.set(i, guess);
                    }
                }

                if(!wordState.contains('_')) {
                    System.out.println("YOU WIN!");
                    System.out.print(getHangmanArt(wrongGuesses));
                    System.out.printf("The word was %s", word);
                    break;
                }

            } else {
                wrongGuesses++;
                System.out.println("Wrong Guess!");
            }
        }

        if(wrongGuesses == 6) {
            System.out.print(getHangmanArt(wrongGuesses));
            System.out.println("GAME OVER!");
            System.out.printf("The word was %s", word);
        }

        sc.close();
    }

    static String getHangmanArt(int wrongGuesses) {
        return switch(wrongGuesses) {
            case 0 -> """
                      
                      
                      
                      """;

            case 1 -> """
                       o
                      
                      
                      """;

            case 2 -> """
                       o
                       |
                      
                      """;

            case 3 -> """
                       o
                      /|
                      
                      """;

            case 4 -> """
                       o
                      /|\\
                      
                      """;

            case 5 -> """
                       o
                      /|\\
                      /
                      """;

            case 6 -> """
                       o
                      /|\\
                      / \\
                      """;

            default -> "";
        };
    }
}