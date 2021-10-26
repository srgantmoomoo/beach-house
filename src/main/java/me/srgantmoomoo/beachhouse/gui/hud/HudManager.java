package me.srgantmoomoo.beachhouse.gui.hud;

import java.util.ArrayList;

import me.srgantmoomoo.beachhouse.gui.hud.hudmodules.*;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

public class HudManager {

	public ArrayList<HudModule> hudModules = new ArrayList<>();

	public HudManager() {
		hudModules.add(new Woohoo());
		hudModules.add(new Watermark());
		hudModules.add(new PlayerInfo());
		hudModules.add(new TabGui());
		//hudModules.add(new EnabledModules());
		hudModules.add(new SessionInfo());
		hudModules.add(new PotionEffects());
		hudModules.add(new Coordinates());
	}

	public HudModule getHudModule(String name) {
		for (HudModule h : this.hudModules) {
			if(h.getName().equalsIgnoreCase(name)) {
				return h;
			}
		}
		return null;
	}

	public Module getModule(String name) {
		for (Module m : Bedroom.moduleManager.modules) {
			if(m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
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
