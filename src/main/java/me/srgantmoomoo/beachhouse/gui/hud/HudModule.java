package me.srgantmoomoo.beachhouse.gui.hud;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

//TODO extends this class from module.
public class HudModule {
	
	public String name;
	public boolean enabled;
	public DraggableComponent drag;
	
	public int x, y;
	
	public HudModule(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
		
		drag = new DraggableComponent(x, y, x + getWidth(), y + getHeight());
	}
	
	public int getWidth() {
		return 50;
		
	}
	
	public int getHeight() {
		return 50;
	}
	
	// this is called in HudManager by renderMods(). //TODO UPDATEE
	public void draw(MatrixStack matrix) {
		
	}
	
	// this is called in HudConfigScreen. //TODO update class names.
	public void drawDraggable(MatrixStack matrix, int mouseX, int mouseY) {
		drag.draw(matrix, mouseX, mouseY);
	}
	
	public int getX() {
		return drag.getxPosition();
	}
	
	public int getY() {
		return drag.getyPosition();
	}

}
