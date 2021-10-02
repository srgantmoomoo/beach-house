package me.srgantmoomoo.beachhouse.backend.events;

import me.srgantmoomoo.bedroom.event.Event;

// posted in MixinKeyboard
public class EventGuiKeyPress extends Event<EventGuiKeyPress> {
    private int key;
    private int scanCode;

    public EventGuiKeyPress(int key, int scanCode) {
        this.key = key;
        this.scanCode = scanCode;
    }

    public int getKey() {
        return key;
    }

    public int getScanCode() {
        return scanCode;
    }
}
