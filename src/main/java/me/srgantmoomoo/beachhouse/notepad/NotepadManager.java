package me.srgantmoomoo.beachhouse.notepad;

import java.util.ArrayList;
import java.util.List;

public class NotepadManager {
    public static List<Notepad> notes;

    public NotepadManager() {
        notes = new ArrayList<>();
    }

    public static List<String> getNotes() {
        ArrayList<String> notesL = new ArrayList<>();
        notes.forEach(note -> notesL.add(note.getName()));

        return notesL;
    }

    public static Notepad getNoteByName(String name) {
        Notepad nn = null;
        for(Notepad n : notes) {
            if(n.getName().equalsIgnoreCase(name)) {
                nn = n;
            }
        }

        return nn;
    }

    public static void addNote(String name, String msg) {
        notes.add(new Notepad(name, msg));
    }

    public static void removeNote(String name) {
        notes.remove(getNoteByName(name));
    }

    public static void clearNotes() {
        notes.clear();
    }

}
