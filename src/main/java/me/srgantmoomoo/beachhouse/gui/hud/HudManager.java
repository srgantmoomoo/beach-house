package me.srgantmoomoo.beachhouse.gui.hud;

import java.util.ArrayList;

import me.srgantmoomoo.beachhouse.gui.hud.hudmodules.TestModule;
import me.srgantmoomoo.beachhouse.gui.hud.hudmodules.Watermark;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

public class HudManager {
	
	public ArrayList<HudModule> hudModules = new ArrayList<>();
	
	public HudManager() {
		hudModules.add(new TestModule());
		hudModules.add(new Watermark());
	}
	
	//TODO UPDATE this is called in MixinInGameHud.
	public void renderMods(MatrixStack matrix) {
		for(HudModule m : hudModules) {
			if(m.isHudEnabled())
				m.draw(matrix);
		}
	}
	
	public void drawBox(MatrixStack matrix, int x, int y, int width, int height, int color) {
		InGameHud.fill(matrix, x - 2, y - 2, x + width, y + height, 0x90000000);
		InGameHud.fill(matrix, x - 2, y - 2, x, y - 1, color);
		InGameHud.fill(matrix, x - 2, y - 2, x - 1, y, color);
	}

}
