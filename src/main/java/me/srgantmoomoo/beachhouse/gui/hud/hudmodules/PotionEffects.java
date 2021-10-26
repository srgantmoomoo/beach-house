package me.srgantmoomoo.beachhouse.gui.hud.hudmodules;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;

public class PotionEffects extends HudModule {

    public PotionEffects() {
        super("potion effects", "potioneffects", "see ur potion effects.", 100, 20, Category.BEACHHOUSE);
    }

    private void drawFinale(MatrixStack matrix) {
        assert minecraft.player != null;
    }

    @Override
    public void draw(MatrixStack matrix) {
        drawFinale(matrix);

        super.draw(matrix);
    }

    @Override
    public void drawDraggable(MatrixStack matrix, int mouseX, int mouseY) {
        Main.hudManager.drawBox(matrix, getX(), getY(), getWidth(), getHeight(), hudEnabled ? 0xff00ff00 : 0xffffffff);

        if(minecraft.player == null)
            return;

        for (StatusEffectInstance statusEffectInstance : minecraft.player.getStatusEffects()) {
            StatusEffect statusEffect = statusEffectInstance.getEffectType();
        }

        minecraft.textRenderer.drawWithShadow(matrix, "potion example 0:00", getX(), getY(), 0xffffffff);

        drawFinale(matrix);

        super.drawDraggable(matrix, mouseX, mouseY);
    }

    @Override
    public int getWidth() {
        if(minecraft.player != null) {
            int width = minecraft.textRenderer.getWidth("potion example 0:00") + 1;

            return width;
        }

        return 70;
    }

    @Override
    public int getHeight() {
        return 10;
    }
}
