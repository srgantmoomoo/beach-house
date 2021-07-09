package me.srgantmoomoo.beachhouse.modules.beachhouse;

import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.bedroom.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;

public class PlayerInfo extends Module {

    public PlayerInfo() {
        super("player info", "sucks ur pp for u.", 0, Category.BEACHHOUSE);
    }

    @EventHandler
    private final Listener<EventDrawOverlay> overlayListener = new Listener<>(e -> {
        int screenWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int screenHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();

        InGameHud.fill(e.matrix, screenWidth - 2, screenHeight - 2, screenWidth - 104, screenHeight - 44, 0x80ffffff);

    });
}
