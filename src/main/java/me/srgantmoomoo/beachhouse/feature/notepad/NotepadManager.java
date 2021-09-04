package me.srgantmoomoo.beachhouse.feature.notepad;

import java.util.ArrayList;
import java.util.List;

public class NotepadManager {
    public List<Notepad> notes;

    public NotepadManager() {
        notes = new ArrayList<>();
    }

    public boolean isNote(String name) {
        for(Notepad n : notes) {
            if(n.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getNotes() {
        ArrayList<String> notesL = new ArrayList<>();
        notes.forEach(note -> notesL.add(note.getName()));

        return notesL;
    }

    public Notepad getNoteByName(String name) {
        for(Notepad n : notes) {
            if(n.getName().equalsIgnoreCase(name)) {
                return n;
            }
        }
        return null;
    }

    public void addNote(String name, String msg) {
        notes.add(new Notepad(name, msg));
    }

    public void removeNote(String name) {
        notes.remove(getNoteByName(name));
    }

    public void clearNotes() {
        notes.clear();
    }

}
