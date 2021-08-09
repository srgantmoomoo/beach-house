package me.srgantmoomoo.beachhouse.gui;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.events.DrawOverlayEvent;
import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.api.util.TextFormatting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.math.MatrixStack;

public class InGameUI {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public void draw(MatrixStack matrix) {
        DrawOverlayEvent event = new DrawOverlayEvent(matrix);
        TextRenderer tr = mc.textRenderer;
        tr.drawWithShadow(event.matrix, TextFormatting.LIGHT_PURPLE + "{" + TextFormatting.GOLD + "bh" + TextFormatting.LIGHT_PURPLE + "}" + TextFormatting.AQUA + " " + Main.version, 2, 2, 0xffffffff);
    
        if(Reference.minecraft.currentScreen instanceof ChatScreen) {
        	System.out.println("stufff");
        }
    }
    //this is called in MixinInGameHud

}
