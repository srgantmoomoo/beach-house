package me.srgantmoomoo.beachhouse.gui;

import java.awt.Color;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.events.DrawOverlayEvent;
import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.api.util.TextFormatting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.ChatHudListener;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.option.ChatOptionsScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.ChatMessages;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.util.ChatUtil;

public class InGameUI {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    public static boolean in = false;
    
    // this is called in MixinInGameHud
    public void draw(MatrixStack matrix) {
        DrawOverlayEvent event = new DrawOverlayEvent(matrix);
        TextRenderer tr = mc.textRenderer;
        tr.drawWithShadow(event.matrix, TextFormatting.LIGHT_PURPLE + "{" + TextFormatting.GOLD + "bh" + TextFormatting.LIGHT_PURPLE + "}" + TextFormatting.AQUA + " " + Main.version, 2, 2, 0xffffffff);
    
        //TODO needs to properly check if first letter typed is the command prefix.
        if(in) {
	        int screenWidth = Reference.window.getScaledWidth();
	        int screenHeight = Reference.window.getScaledHeight();
	        if(Reference.minecraft.currentScreen instanceof ChatScreen) {
	        	ChatScreen chat = new ChatScreen(null);
	            //tr.drawWithShadow(event.matrix, "" + chat.chat, 20, 20, 0);
	        	final int[] counter = {1};
	        	// left
	        	InGameHud.fill(event.matrix, 1, screenHeight - 14, 2, screenHeight - 2, rainbow(counter[0] * 300));
	        	// right
	        	InGameHud.fill(event.matrix, screenWidth - 1, screenHeight - 14, screenWidth - 2, screenHeight - 2, rainbow(counter[0] * 300));
	        	// top
	        	InGameHud.fill(event.matrix, 1, screenHeight - 14, screenWidth - 1, screenHeight - 15, rainbow(counter[0] * 300));
	        	// bottom
	        	InGameHud.fill(event.matrix, 1, screenHeight - 2, screenWidth - 1, screenHeight - 1, rainbow(counter[0] * 300));
	        	counter[0]++;
	        }
        }
    }

    private int rainbow(int delay) {
		   double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
		   rainbowState %= -360;
	       return Color.getHSBColor((float) (rainbowState / -360.0f), 0.5f, 1f).getRGB();
	}
}
