package me.srgantmoomoo.beachhouse.feature.module.modules.movement;

import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventTick;
import org.lwjgl.glfw.GLFW;

import me.srgantmoomoo.bedroom.module.Module;

public class Sprint extends Module {
	
	public Sprint() {
		super("sprint", "sprint", "sprinttt.", 0, Category.MOVEMENT);
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
