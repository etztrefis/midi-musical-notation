package com.company;

import com.company.colors.ConsoleColors;
import org.jfugue.parser.ParserListenerAdapter;
import org.jfugue.theory.Note;

public class NoteParserListener extends ParserListenerAdapter {
    @Override
    public void onNotePressed(Note note) {
        System.out.println("♪ " + ConsoleColors.BLUE_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + "Pressed" + ConsoleColors.RESET + " "
                + ConsoleColors.YELLOW_BRIGHT + "Octave: " + note.getOctave() + " Note: " + note.getToneString()
                + " Velocity: " + note.getVelocityString() + ConsoleColors.RESET);
    }

    @Override
    public void onNoteReleased(Note note) {
        System.out.println("♪ " + ConsoleColors.RED_BACKGROUND + ConsoleColors.WHITE_BOLD_BRIGHT + "Released" + ConsoleColors.RESET + " "
                + ConsoleColors.YELLOW_BRIGHT + "Note: " + note.getToneString() + ConsoleColors.RESET);
    }
}
