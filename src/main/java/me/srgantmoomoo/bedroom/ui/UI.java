package me.srgantmoomoo.bedroom.ui;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.bedroom.api.font.JColor;
import me.srgantmoomoo.external.renderer.FontRenderer;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;

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
		FontRenderer fr = Main.fontRenderer;
		fr.drawString(e.matrix, "beach house", 1, 1, true, new JColor(255, 255, 255));
	});
}