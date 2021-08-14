package me.srgantmoomoo.beachhouse.gui.hud;

import java.util.ArrayList;

import me.srgantmoomoo.beachhouse.gui.hud.hudmodules.*;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

public class HudManager {

	public ArrayList<HudModule> hudModules = new ArrayList<>();

	public HudManager() {
		hudModules.add(new Woohoo());
		hudModules.add(new Watermark());
		hudModules.add(new PlayerInfo());
		hudModules.add(new TabGui());
	}

	// this is called in MixinInGameHud.
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

	public void drawIndicator(MatrixStack matrix, int x, int y, int color) {
		InGameHud.fill(matrix, x, y, x + 1, y + 2, color);
		InGameHud.fill(matrix, x, y, x + 2, y + 1, color);
	}

}
