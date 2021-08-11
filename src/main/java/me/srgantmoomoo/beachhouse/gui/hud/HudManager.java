package me.srgantmoomoo.beachhouse.gui.hud;

import java.util.ArrayList;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

public class HudManager {
	
	public ArrayList<HudModule> hudMods = new ArrayList<>();
	
	public HudManager() {
		hudMods.add(new TestModule());
	}
	
	//TODO UPDATE this is called in MixinGameHud.
	public void renderMods(MatrixStack matrix) {
		for(HudModule m : hudMods) {
			if(m.enabled) {
				m.draw(matrix);
			}
		}
	}
	
	public void drawBox(MatrixStack matrix, int x, int y, int width, int height) {
		InGameHud.fill(matrix, x - 2, y - 2, x + width, y + height, 0x90000000);
		InGameHud.fill(matrix, x - 2, y - 2, x, y - 1, 0xffffffff);
		InGameHud.fill(matrix, x - 2, y - 2, x - 1, y, 0xffffffff);
	}

}
