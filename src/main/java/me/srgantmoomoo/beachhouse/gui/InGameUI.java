package me.srgantmoomoo.beachhouse.gui;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.bedroom.api.util.TextFormatting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listenable;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class InGameUI {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public void draw(MatrixStack matrix) {
        EventDrawOverlay event = new EventDrawOverlay(matrix);
        TextRenderer tr = mc.textRenderer;
        tr.drawWithShadow(event.matrix, TextFormatting.LIGHT_PURPLE + "{" + TextFormatting.GOLD + "bh" + TextFormatting.LIGHT_PURPLE + "}" + TextFormatting.AQUA + " " + Main.version, 2, 2, 0xffffffff);
    }
    //this is called in MixinInGameHud

}
