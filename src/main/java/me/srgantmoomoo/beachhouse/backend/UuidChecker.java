package me.srgantmoomoo.beachhouse.backend;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventTick;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.Arrays;

public class UuidChecker {
    public static UuidChecker INSTANCE;
    private ArrayList<String> uuids;
    private boolean isOnList = false;
    private boolean ran = false;

    public UuidChecker() {
        INSTANCE = this;
    }

    // called in MixinClientWorld
    public void onEvent(Event e) {
        if(e instanceof EventTick) {
            if(!ran) {
                if(Main.checkUuids)
                    logic();
            }
        }
    }

    public void logic() {
        uuids = new ArrayList<>(Arrays.asList("62cf9cdc-2cbd-44b1-bb6f-754b48ede1d0", "6cabf93b-bd71-46bc-bf99-33dd3d26a6d3", "3daf3d9d-629f-49f9-bc19-a86e0b6f125b"));

        check();
        execute();
        ran = true;
    }

    public void check() {
        if(MinecraftClient.getInstance().player != null) {
            String playerUUID = MinecraftClient.getInstance().player.getUuidAsString();
            isOnList = uuids.contains(playerUUID);
            System.out.println(playerUUID);
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
