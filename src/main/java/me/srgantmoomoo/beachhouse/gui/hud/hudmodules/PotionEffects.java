package me.srgantmoomoo.beachhouse.gui.hud.hudmodules;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import net.minecraft.client.util.math.MatrixStack;

public class PotionEffects extends HudModule {

    public PotionEffects() {
        super("potion effects", "potioneffects", "see ur potion effects.", 100, 20, Category.BEACHHOUSE);
    }

    private void drawFinale(MatrixStack matrix) {
        assert minecraft.player != null;
        minecraft.textRenderer.drawWithShadow(matrix, minecraft.player.getStatusEffects() + "", getX(), getY(), 0xffffffff);
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
        return 30;
    }

    @Override
    public int getHeight() {
        return 30;
    }
}
