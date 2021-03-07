package me.srgantmoomoo.beachhouse.modules.movement;

import org.lwjgl.glfw.GLFW;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.bedroom.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.font.TextRenderer;

public class Sprint extends Module {
	
	public Sprint() {
		super("sprint", "sprinttt.", GLFW.GLFW_KEY_G, Category.PLAYER);
	}
	
	public void onEnable() {
		Main.EVENTBUS.subscribe(listener);
		mc.player.setSprinting(true);
	}
	
	public void onDisable() {
		Main.EVENTBUS.unsubscribe(listener);
		mc.player.setSprinting(false);
	}
	
	@EventHandler
	private final Listener<EventDrawOverlay> listener = new Listener<>(e -> {
		TextRenderer tr = mc.textRenderer;
		tr.drawWithShadow(e.matrix, Main.name + " is the best client for sprinting", 2, 2, 0xffffffff);
		
	});
}
