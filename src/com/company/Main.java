package com.company;

import com.company.colors.ConsoleColors;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class Main {

    public static void main(String[] args) {
        try {
            new MidiHandler();
        }catch(Exception e){
            System.out.println("❌" + ConsoleColors.YELLOW_BRIGHT + ConsoleColors.RED_BACKGROUND + "Error: " + e + ConsoleColors.RESET);
        }
    }
}
