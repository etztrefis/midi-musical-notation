package com.company;

import com.company.colors.ConsoleColors;
import org.jfugue.devices.MusicTransmitterToParserListener;

import java.util.List;
import java.util.Scanner;
import java.util.TimerTask;
import javax.sound.midi.*;

public class MidiHandler {
    public MidiHandler() {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        System.out.println("\uD83D\uDCC4 " + ConsoleColors.YELLOW_BRIGHT + ConsoleColors.PURPLE_BACKGROUND + "List of midi devices: " + ConsoleColors.RESET);
        for (int i = 0; i < infos.length; i++) {
            System.out.println(i + ". " + infos[i].getName() + " - " + infos[i].getDescription());
        }

        System.out.println(ConsoleColors.YELLOW_BRIGHT + ConsoleColors.PURPLE_BACKGROUND + "Enter integer of your device: " + ConsoleColors.RESET);
        String textInput;
        Integer integerInput;
        Scanner in = new Scanner(System.in);
        textInput = in.nextLine();
        while (!isInteger(textInput)) {
            System.out.println("❌ " + ConsoleColors.YELLOW_BRIGHT + ConsoleColors.RED_BACKGROUND + "Only integers are allowed." + ConsoleColors.RESET);
            textInput = in.nextLine();
        }
        integerInput = Integer.parseInt(textInput);

        MidiDevice input = null;
        try {
            input = MidiSystem.getMidiDevice(infos[integerInput]);
        } catch (MidiUnavailableException e) {
            System.out.println("❌ " + ConsoleColors.YELLOW_BRIGHT + ConsoleColors.RED_BACKGROUND + "Error: " + e + ConsoleColors.RESET);
        }

        System.out.println("✅ " + ConsoleColors.PURPLE + "Selected: " + ConsoleColors.RESET + ConsoleColors.PURPLE_BOLD_BRIGHT + infos[integerInput].getName() + ConsoleColors.RESET);

        Sequencer sequencer = null;
        try {
            sequencer = MidiSystem.getSequencer();
        } catch (MidiUnavailableException e) {
            System.out.println("❌ " + ConsoleColors.YELLOW_BRIGHT + ConsoleColors.RED_BACKGROUND + "Error: " + e + ConsoleColors.RESET);
        }
        MusicTransmitterToParserListener m = null;
        try {
            input.open();
            sequencer.open();
            m = new MusicTransmitterToParserListener(input);
            m.addParserListener(new NoteParserListener());
            m.listenForMillis(30000);
        } catch (NullPointerException | MidiUnavailableException | InterruptedException e ) {
            System.out.println("❌ " + ConsoleColors.YELLOW_BRIGHT + ConsoleColors.RED_BACKGROUND + "Error: " + e + ConsoleColors.RESET);
        }
    }
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}

