package me.srgantmoomoo.beachhouse.impl.ui;

import com.google.common.eventbus.Subscribe;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.beachhouse.impl.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class UI {
	private MinecraftClient mc = MinecraftClient.getInstance();
	public static MatrixStack matrix;
	
	@Subscribe
	public void onDrawOverlay(EventDrawOverlay event) {
		TextRenderer fr = mc.textRenderer;
		System.out.print("hi?");
		fr.drawWithShadow(matrix, "beach house", 1, 1, 0xffffffff);
	}

}
