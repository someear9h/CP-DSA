package miniprojects;

import java.util.Scanner;

public class Project2 {

    public static void main(String[] args) {


        // declare variables
        // welcome message
        // questions, options, get guess from user, check guess
        // display final score

        // Questions array[]
        String[] questions = {"what is the main functions of the router?",
                "which part of the computer is considered as brain?",
                "what year was facebook launched?",
                "Who is known as the father of the computers?",
                "What's Samarth's Favourite programming language?"
        };

        // options array[][]
        String[][] options = {
                {"1. Storing files", "2. Encrypting data", "3. Directing internet traffic", "4. Managing passwords"},
                {"1. CPU", "2. Hard drive", "3. Mouse", "4. Monitor"},
                {"1. 2000", "2. 2004", "3. 2006", "4. 2008"},
                {"1. Steve Jobs", "2. Bill Gates", "3. Alan Turing", "4. Charles Babbage"},
                {"1. Java", "2. C++", "3. Python", "4. Javascript"}
        };

        int[] answers = {3, 1, 2, 4, 1};
        int score = 0;
        int guess = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("******************************");
        System.out.println("Welcome to Java quiz program");
        System.out.println("******************************");
        System.out.println();

        // display the questions
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);

            for(String option : options[i]) {
                System.out.println(option);
            }
            System.out.println();

            System.out.print("Enter your choice: ");
            guess = sc.nextInt();
            System.out.println();

            if(guess == answers[i]) {
                score++;
                System.out.println("*******************");
                System.out.println(" CORRECT ANSWER! ");
                System.out.println("*******************");
                System.out.println();
            } else {
                System.out.println("*******************");
                System.out.println(" WRONG ANSWER! ");
                System.out.println("*******************");
                System.out.println();
            }
        }

        System.out.println("*******************");
        System.out.println(" END OF THE QUIZ! ");
        System.out.printf("Your Score is %d", score);
        System.out.println("*******************");

        sc.close();
    }
}
