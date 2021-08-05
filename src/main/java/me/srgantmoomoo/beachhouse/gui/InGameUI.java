package me.srgantmoomoo.beachhouse.gui;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.bedroom.api.util.TextFormatting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listenable;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class InGameUI implements Listenable {
    private MinecraftClient mc = MinecraftClient.getInstance();

    /*@EventHandler
    private final Listener<EventDrawOverlay> listener = new Listener<>(e -> {
        TextRenderer tr = mc.textRenderer;
        //tr.drawWithShadow(e.matrix, Main.name + " " + Main.version, 2, 2, 0xffffffff);

        tr.drawWithShadow(e.matrix, TextFormatting.LIGHT_PURPLE + "{" + TextFormatting.GOLD + "bh" + TextFormatting.LIGHT_PURPLE + "}" + TextFormatting.AQUA + " " + Main.version, 2, 2, 0xffffffff);
    });*/
    public MatrixStack matrix;
    public void draw() {
        TextRenderer tr = mc.textRenderer;
        tr.drawWithShadow(matrix, TextFormatting.LIGHT_PURPLE + "{" + TextFormatting.GOLD + "bh" + TextFormatting.LIGHT_PURPLE + "}" + TextFormatting.AQUA + " " + Main.version, 2, 2, 0xffffffff);
        Bedroom.onEvent(new EventDrawOverlay());
    }

}
