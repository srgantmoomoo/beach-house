package me.srgantmoomoo.beachhouse.modules.render;

import org.lwjgl.glfw.GLFW;

import me.srgantmoomoo.bedroom.module.Module;

public class FullBright extends Module {
	
	public FullBright() {
		super("full bright", "fullness of brightness.", GLFW.GLFW_KEY_H, Category.RENDER);
	}
	
	public void onEnable() {

	}

}
