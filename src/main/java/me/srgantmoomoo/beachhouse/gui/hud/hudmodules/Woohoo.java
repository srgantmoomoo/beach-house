package me.srgantmoomoo.beachhouse.gui.hud.hudmodules;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.mixins.AccessorChatScreen;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.math.MatrixStack;

public class Woohoo extends HudModule {

	public Woohoo() {
		super("woohoo", "woohoo", "is a test", 107, 2, Category.BEACHHOUSE);
	}
	public ChatScreen chatScreen;

	private void drawFinale(MatrixStack matrix) {
		minecraft.textRenderer.drawWithShadow(matrix, "woohoo", getX(), getY(), 0xffffffff);
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
		return 38;
	}

	@Override
	public int getHeight() {
		return 10;
	}

}
