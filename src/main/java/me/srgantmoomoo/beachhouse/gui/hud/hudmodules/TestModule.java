package me.srgantmoomoo.beachhouse.gui.hud.hudmodules;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import net.minecraft.client.util.math.MatrixStack;

public class TestModule extends HudModule {

	public TestModule() {
		super("test module", "testmodule", "is a test", 30, 3, Category.BEACHHOUSE);
	}

	@Override
	public void draw(MatrixStack matrix) {
		minecraft.textRenderer.drawWithShadow(matrix, "woohoo", getX(), getY(), 0xffffffff);
		super.draw(matrix);
	}

	@Override
	public void drawDraggable(MatrixStack matrix, int mouseX, int mouseY) {
		Main.hudManager.drawBox(matrix, getX(), getY(), getWidth(), getHeight(), hudEnabled ? 0xffffffff : 0xffff0000);
		minecraft.textRenderer.drawWithShadow(matrix, "woohoo", getX(), getY(), 0xffffffff);

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
