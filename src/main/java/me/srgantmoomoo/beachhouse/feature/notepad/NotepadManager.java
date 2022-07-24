package me.srgantmoomoo.beachhouse.feature.notepad;

import me.srgantmoomoo.beachhouse.Main;

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

    public List<Notepad> getNotes() {
        return notes;
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

        if (Main.INSTANCE.save != null) {
            try {
                Main.INSTANCE.save.saveNotepad();
            } catch (Exception e) {}
        }
    }

    public void removeNote(String name) {
        notes.remove(getNoteByName(name));

        if (Main.INSTANCE.save != null) {
            try {
                Main.INSTANCE.save.saveNotepad();
            } catch (Exception e) {}
        }
    }

    public void clearNotes() {
        notes.clear();
    }

}
