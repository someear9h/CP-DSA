package miniprojects.alarmclock;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

public class AlarmClock implements Runnable {

    private final LocalTime alarmTime;
    private final String filePath;

    AlarmClock(LocalTime alarmTime, String filePath) {
        this.alarmTime = alarmTime;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        while(LocalTime.now().isBefore(alarmTime)) {
            try {
                Thread.sleep(1000);
                LocalTime now = LocalTime.now();

                int hours = now.getHour();
                int minutes = now.getMinute();
                int seconds = now.getSecond();

                System.out.printf("\r%02d:%02d:%02d", hours, minutes, seconds);
            }
            catch (InterruptedException e) {
                System.out.println("Thread was interrupted!");
            }

        }

        System.out.println("\n*alarm noises*");

       playSound(filePath);
    }

    private void playSound(String filePath) {
        File audioFile = new File(filePath);


        try(AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            Thread.sleep(5000);
        }
        catch(UnsupportedAudioFileException e) {
            System.out.println("Audio file format not supported");
        }
        catch(LineUnavailableException e) {
            System.out.println("Audio is unavailable");
        }
        catch (IOException e) {
            System.out.println("Error Reading the audio file");
        }
        catch (InterruptedException e) {
            System.out.println("Thread was interrupted");
        }
    }
}
