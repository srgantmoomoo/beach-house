package me.srgantmoomoo.beachhouse.gui.hud.hudmodules;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;

public class Coordinates extends HudModule {

    public Coordinates() {
        super("coordinates", "coordinates", "look aat u coords luol.", 70, 72, Category.BEACHHOUSE);
    }

    private void drawFinale(MatrixStack matrix) {
        assert minecraft.player != null;
        minecraft.textRenderer.drawWithShadow(matrix,
                Formatting.GRAY + "(x)" + Formatting.RESET + (int)minecraft.player.getX() + " "
                + Formatting.GRAY + "(y)" + Formatting.RESET + (int)minecraft.player.getY() + " "
                + Formatting.GRAY + "(z)" + Formatting.RESET + (int)minecraft.player.getZ(),
                getX(), getY(), 0xffffffff);
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
        if(minecraft.player != null) {
            int width = minecraft.textRenderer.getWidth(
                    "(x)" + (int) minecraft.player.getX() + " "
                    + "(y)" + (int) minecraft.player.getY() + " "
                    + "(z)" + (int) minecraft.player.getZ());

            return width;
        }

        return 10;
    }

    @Override
    public int getHeight() {
        return 10;
    }

}
