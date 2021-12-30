package me.srgantmoomoo.beachhouse.gui.hud.hudmodules;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.srgantmoomoo.bedroom.util.font.JColor;
import net.minecraft.client.util.math.MatrixStack;

public class Watermark extends HudModule {
	public ModeSetting style = new ModeSetting("style", this, "beachhouse", "beachhouse", "bh");
	public BooleanSetting version = new BooleanSetting("version", this, false);
	public ColorSetting watermarkColor = new ColorSetting("color", this, new JColor(248, 104, 251, 255));

	public Watermark() {
		super("watermark", "watermark", "does watermark stuff", 2, 2, Category.BEACHHOUSE);
		hudEnabled = true;
	}

	private void drawFinale(MatrixStack matrix) {
		JColor watermarkColorRGB = watermarkColor.getValue();

		if(style.is("beachhouse")) {
			if(!version.isEnabled())
				minecraft.textRenderer.drawWithShadow(matrix, "beach house", getX(), getY(), watermarkColorRGB.getRGB());
			else
				minecraft.textRenderer.drawWithShadow(matrix, "beach house" + " " + Main.version, getX(), getY(), watermarkColorRGB.getRGB());
		}else if(style.is("bh")) {
			if(!version.isEnabled())
				minecraft.textRenderer.drawWithShadow(matrix, "bh", getX(), getY(), watermarkColorRGB.getRGB());
			else
				minecraft.textRenderer.drawWithShadow(matrix, "bh" + " " + Main.version, getX(), getY(), watermarkColorRGB.getRGB());
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
