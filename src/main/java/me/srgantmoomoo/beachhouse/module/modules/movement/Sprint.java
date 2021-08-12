package me.srgantmoomoo.beachhouse.module.modules.movement;

import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.api.event.events.EventTick;
import org.lwjgl.glfw.GLFW;

import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.MinecraftClient;

public class Sprint extends Module {
	
	public Sprint() {
		super("sprint", "sprint", "sprinttt.", GLFW.GLFW_KEY_G, Category.MOVEMENT);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventTick) {
			assert minecraft.player != null;
			minecraft.player.setSprinting(true);
		}
	}
	
	@Override
	public void onDisable() {
		assert minecraft.player != null;
		minecraft.player.setSprinting(false);
	}
}
