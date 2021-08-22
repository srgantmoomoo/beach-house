package me.srgantmoomoo.beachhouse.gui.hud.hudmodules;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import me.srgantmoomoo.bedroom.api.util.font.TextFormatting;
import net.minecraft.client.util.math.MatrixStack;

public class Watermark extends HudModule {

	public Watermark() {
		super("watermark", "watermark", "does watermark stuff", 2, 2, Category.BEACHHOUSE);
		hudEnabled = true;
	}

	private void drawFinale(MatrixStack matrix) {
		minecraft.textRenderer.drawWithShadow(matrix, "{                 }", getX(), getY(), 0xfff868fb);
		minecraft.textRenderer.drawWithShadow(matrix, "beach house", getX() + 6, getY(), 0xffe6ab17);
		minecraft.textRenderer.drawWithShadow(matrix, Main.version, getX() + 80, getY(), 0xff11c1e8);
	}

	@Override
	public void draw(MatrixStack matrix) {
		drawFinale(matrix);

		super.draw(matrix);
	}

	@Override
	public void drawDraggable(MatrixStack matrix, int mouseX, int mouseY) {
		Main.hudManager.drawBox(matrix, getX(), getY(), getWidth(), getHeight(), hudEnabled ? 0xff00ff00 : 0xffffffff);
		drawFinale(matrix);

		super.drawDraggable(matrix, mouseX, mouseY);
	}

	@Override
	public int getWidth() {
		return 101;
	}

	@Override
	public int getHeight() {
		return 10;
	}
}
