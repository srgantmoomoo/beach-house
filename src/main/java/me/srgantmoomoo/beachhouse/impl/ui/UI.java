package me.srgantmoomoo.beachhouse.impl.ui;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.api.event.events.EventDrawOverlay;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listenable;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class UI {
	private MinecraftClient mc = MinecraftClient.getInstance();

	public UI() {
		Main.EVENTBUS.subscribe(listener);
	}
	
	@EventHandler
	private final Listener<EventDrawOverlay> listener = new Listener<>(e -> {
		System.out.print("nononoyes!");
		TextRenderer fr = mc.textRenderer;
		fr.drawWithShadow(e.matrix, "beach house", 1, 1, 0xffffffff);
	});

}