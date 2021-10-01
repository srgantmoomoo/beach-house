package me.srgantmoomoo.beachhouse.backend;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventTick;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.Arrays;

public class NameChecker {
    public static NameChecker INSTANCE;
    private ArrayList<String> names;
    private boolean isOnList = false;
    private boolean ran = false;

    public NameChecker() {
        INSTANCE = this;
    }

    // called in MixinClientWorld
    public void onEvent(Event e) {
        if(e instanceof EventTick) {
            if(!ran) {
                if(Main.checkNames)
                    logic();
            }
        }
    }

    public void logic() {
        names = new ArrayList<>(Arrays.asList("srgantmoomoo", "philip"));

        check();
        execute();
        ran = true;
    }

    public void check() {
        if(MinecraftClient.getInstance().player != null) {
            String playerName = MinecraftClient.getInstance().player.getEntityName().toLowerCase();
            isOnList = names.contains(playerName);
            System.out.println(playerName);
        }
    }

    public void execute() {
        if (isOnList) {
            System.out.println("player is on the list.");
        }else {
            System.out.println("player is not on list, shutting down mc.");

            MinecraftClient.getInstance().close();
        }
    }

}
