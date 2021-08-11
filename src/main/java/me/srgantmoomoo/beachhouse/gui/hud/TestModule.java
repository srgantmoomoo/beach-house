package me.srgantmoomoo.beachhouse.gui.hud;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.util.Reference;
import net.minecraft.client.util.math.MatrixStack;

public class TestModule extends HudModule {

	public TestModule() {
		super("myfathud", 30, 3);
		enabled = true;
	}
	
	@Override
	public void draw(MatrixStack matrix) {
		Reference.tr.drawWithShadow(matrix, "woohoo", getX(), getY(), 0xffffffff);
		super.draw(matrix);
	}
	
	@Override
	public void drawDraggable(MatrixStack matrix, int mouseX, int mouseY) {
		Main.hudManager.drawBox(matrix, getX(), getY(), getWidth(), getHeight());
		Reference.tr.drawWithShadow(matrix, "woohoo", getX(), getY(), 0xffffffff);
		
		super.drawDraggable(matrix, mouseX, mouseY);
	}
	
	@Override
	public int getWidth() {
		return 38;
	}
	
	@Override
	public int getHeight() {
		return 11;
	}

}
