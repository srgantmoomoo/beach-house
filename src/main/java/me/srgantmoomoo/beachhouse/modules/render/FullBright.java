package me.srgantmoomoo.beachhouse.modules.render;

import java.awt.Font;

import org.lwjgl.glfw.GLFW;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.external.renderer.FontRenderer;
import me.srgantmoomoo.external.renderer.GlyphPage;

public class FullBright extends Module {
	
	public FullBright() {
		super("full bright", "fullness of brightness.", GLFW.GLFW_KEY_H, Category.RENDER);
	}
	
	public void onEnable() {

	}

}
