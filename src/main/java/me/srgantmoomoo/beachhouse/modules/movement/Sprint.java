package me.srgantmoomoo.beachhouse.modules.movement;

import org.lwjgl.glfw.GLFW;

import me.srgantmoomoo.bedroom.module.Module;

public class Sprint extends Module {
	
	public Sprint() {
		super("sprint", "sprinttt.", GLFW.GLFW_KEY_G, Category.MOVEMENT);
	}
	
	@Override
	public void onUpdate() {
		mc.player.setSprinting(true);
	}
	
	@Override
	public void onDisable() {
		mc.player.setSprinting(false);
	}
}
