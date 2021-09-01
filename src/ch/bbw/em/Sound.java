package ch.bbw.em;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    public void music(String soundLocation) {
        try {
            File soundPath = new File(soundLocation);
            if (soundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                JOptionPane.showMessageDialog(null, "Press Ok to stop");
            } else {
                System.out.println("error");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}