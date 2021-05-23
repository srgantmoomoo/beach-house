package me.srgantmoomoo.beachhouse.modules.movement;

import org.lwjgl.glfw.GLFW;

import me.srgantmoomoo.bedroom.module.Module;

public class Sprint extends Module {
	
	public Sprint() {
		super("sprint", "sprinttt.", GLFW.GLFW_KEY_G, Category.PLAYER);
	}
	
	public void onEnable() {
		mc.player.setSprinting(true);
	}
	
	public void onDisable() {
		mc.player.setSprinting(false);
	}
}
