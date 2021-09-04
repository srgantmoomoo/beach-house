package me.srgantmoomoo.beachhouse.feature.notepad;

public class Notepad {
    String name;
    String message;

    public Notepad(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return this.name;
    }

    public String getMessage() {
        return this.message;
    }

}
