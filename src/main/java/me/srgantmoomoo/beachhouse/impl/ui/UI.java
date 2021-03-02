package me.srgantmoomoo.beachhouse.impl.ui;

import com.google.common.eventbus.Subscribe;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.beachhouse.impl.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class UI extends Module {
	
	public UI() {
		super("ui", "uiiii.", 70, Category.RENDER);
	}
	private MinecraftClient mc = MinecraftClient.getInstance();
	public static MatrixStack matrix;
	
	public void onEnable() {
		super.onEnable();
		System.out.print("hi?");
		Main.EVENTBUS.register(this);
	}
	
	@Subscribe
	public void onDrawOverlay(EventDrawOverlay event) {
		TextRenderer fr = mc.textRenderer;
		System.out.print("hi?");
		fr.drawWithShadow(matrix, "beach house", 1, 1, 0xffffffff);
	}

}
