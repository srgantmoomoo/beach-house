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
		minecraft.textRenderer.drawWithShadow(matrix, TextFormatting.LIGHT_PURPLE + "{" + TextFormatting.GOLD + "bh" + TextFormatting.LIGHT_PURPLE + "}" +
				TextFormatting.AQUA + " " + Main.version, getX(), getY(), 0xffffffff);
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
		return 46;
	}

	@Override
	public int getHeight() {
		return 10;
	}
}
