package me.srgantmoomoo.beachhouse.modules.movement;

import org.lwjgl.glfw.GLFW;

import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.MinecraftClient;

public class Sprint extends Module {
	
	public Sprint() {
		super("sprint", "sprint", "sprinttt.", GLFW.GLFW_KEY_G, Category.MOVEMENT);
	}
	
	@Override
	public void onUpdate() {
		MinecraftClient.getInstance().player.setSprinting(true);
	}
	
	@Override
	public void onDisable() {
		MinecraftClient.getInstance().player.setSprinting(false);
	}
}
