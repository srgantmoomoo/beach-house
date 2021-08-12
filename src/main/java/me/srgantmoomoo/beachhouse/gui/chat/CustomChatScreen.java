package me.srgantmoomoo.beachhouse.gui.chat;

import java.awt.Color;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.command.CommandManager;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.math.MatrixStack;

public class CustomChatScreen extends ChatScreen {

	public CustomChatScreen(String prefix) {
		super(prefix);
	}
	
	//TODO when t is clicked first, this doesn't work. replace ChatScreen with CustomChatScreen.
	@Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
		int screenWidth = Reference.window.getScaledWidth();
        int screenHeight = Reference.window.getScaledHeight();
        
		// background
    	InGameHud.fill(matrix, 2, screenHeight - 14, screenWidth - 2, screenHeight - 2, 0x90000000);
    	// text
    	Reference.tr.drawWithShadow(matrix, chatField.getText(), 4, screenHeight - 12, 0xffffffff);
		if(chatField.getText().startsWith(CommandManager.prefix)) {
			if(chatField.getText().equals(","))
		        Reference.tr.drawWithShadow(matrix, "beach house :)", 6, screenHeight - 12, 0xff999999);
	    	final int[] counter = {1};
	    	// left
	    	InGameHud.fill(matrix, 1, screenHeight - 14, 2, screenHeight - 2, rainbow(counter[0] * 300));
	    	// right
	    	InGameHud.fill(matrix, screenWidth - 1, screenHeight - 14, screenWidth - 2, screenHeight - 2, rainbow(counter[0] * 300));
	    	// top
	    	InGameHud.fill(matrix, 1, screenHeight - 14, screenWidth - 1, screenHeight - 15, rainbow(counter[0] * 300));
	    	// bottom
	    	InGameHud.fill(matrix, 1, screenHeight - 2, screenWidth - 1, screenHeight - 1, rainbow(counter[0] * 300));
	    	counter[0]++;
		}
	}
	
	private int rainbow(int delay) {
		   double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
		   rainbowState %= -360;
	       return Color.getHSBColor((float) (rainbowState / -360.0f), 0.5f, 1f).getRGB();
	}

}