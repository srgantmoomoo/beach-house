package me.srgantmoomoo.bedroom.ui;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;

/** 
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

public class UI {
	private MinecraftClient mc = MinecraftClient.getInstance();

	public UI() {
		Main.EVENTBUS.subscribe(listener);
	}
	
	@EventHandler
	private final Listener<EventDrawOverlay> listener = new Listener<>(e -> {
		TextRenderer tr = mc.textRenderer;
		tr.drawWithShadow(e.matrix, Main.name + " " + Main.version, 2, 2, 0xffffffff);
	});

}