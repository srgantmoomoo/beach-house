package me.srgantmoomoo.beachhouse.gui.hud.hudmodules;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import net.minecraft.client.util.math.MatrixStack;

public class Watermark extends HudModule {

	public Watermark() {
		super("watermark", "watermark", "does watermark stuff", 2, 2, Category.BEACHHOUSE);
		hudEnabled = true;
	}

	private void drawFinale(MatrixStack matrix) {
		if(me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.Watermark.INSTANCE.style.is("style1")) {
			minecraft.textRenderer.drawWithShadow(matrix, "{                 }", getX(), getY(), 0xfff868fb);
			minecraft.textRenderer.drawWithShadow(matrix, "beach house", getX() + 6, getY(), 0xffe6ab17);
			minecraft.textRenderer.drawWithShadow(matrix, Main.version, getX() + 80, getY(), 0xff11c1e8);
		}
		if(me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.Watermark.INSTANCE.style.is("style2")) {
			minecraft.textRenderer.drawWithShadow(matrix, "beach house", getX(), getY(), 0xfff868fB);
		}
		if(me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.Watermark.INSTANCE.style.is("style3")) {
			// draw beach house logo image  (small & big)
		}
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
