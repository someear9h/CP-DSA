package miniprojects.music_player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "miniprojects/music_player/Eyes - Patrick Jordan Patrikios.wav";

        File file = new File(filePath);


        try(Scanner sc = new Scanner(System.in);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            System.out.println("-----------------------------");
            System.out.println(" WELCOME TO MY MUSIC PLAYER ");
            System.out.println("-----------------------------");

            String response = "";
            while(!response.equals("Q")) {
                System.out.println("P = Play");
                System.out.println("S = Stop");
                System.out.println("R = Reset");
                System.out.println("Q = Quit");
                System.out.print("Enter your choice: ");

                response = sc.next().toUpperCase();

                switch(response) {
                    case "P" -> clip.start();
                    case "S" -> clip.stop();
                    case "R" -> clip.setMicrosecondPosition(0);
                    case "Q" -> clip.close();
                    default -> System.out.println("Invalid Choice!");
                }
            }

        }
        catch(FileNotFoundException ex) {
            System.out.println("Could not locate file");
        }
        catch(UnsupportedAudioFileException ex) {
            System.out.println("Audio file is not supported");
        }
        catch(LineUnavailableException ex) {
            System.out.println("Unable to access audio resource");
        }
        catch(IOException ex) {
            System.out.println("Something went wrong");
        }
        finally {
            System.out.println("Bye!");
        }
    }
}
