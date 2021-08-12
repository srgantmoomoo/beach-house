package me.srgantmoomoo.beachhouse.gui.hud;

import net.minecraft.client.util.math.MatrixStack;
import me.srgantmoomoo.bedroom.module.Module;

//TODO extends this class from module.
public class HudModule extends Module {
	
	public String name;
	public DraggableComponent drag;
	public boolean hudEnabled;
	
	public int x, y;
	
	public HudModule(String name, String id, String description, int x, int y, Category category) {
		super(name, id, description, 0, category);
		this.name = name;
		this.x = x;
		this.y = y;
		
		hudEnabled = false;
		drag = new DraggableComponent(x, y, getWidth(), getHeight());
	}
	
	public int getWidth() {
		return 20;
		
	}
	
	public int getHeight() {
		return 50;
	}
	
	// this is called in HudManager by renderMods(). //TODO UPDATEE
	public void draw(MatrixStack matrix) {
		
	}
	
	// this is called in HudScreen. 
	public void drawDraggable(MatrixStack matrix, int mouseX, int mouseY) {
		drag.draw(matrix, mouseX, mouseY);
	}
	
	public boolean isHudEnabled() {
		return hudEnabled;
	}
	
	public int getX() {
		return drag.getXPos();
	}
	
	public int getY() {
		return drag.getYPos();
	}

}
