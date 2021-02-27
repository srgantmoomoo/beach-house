package me.srgantmoomoo.beachhouse.impl.ui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class UI {
	private MinecraftClient mc = MinecraftClient.getInstance();
	
	public void draw(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
		TextRenderer fr = mc.textRenderer;
		
		fr.drawWithShadow(matrix, "beach house", 1, 1, 0xffffffff);
	}

}
