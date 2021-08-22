package me.srgantmoomoo.beachhouse.gui.hud.hudmodules;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import net.minecraft.client.util.math.MatrixStack;

public class SessionInfo extends HudModule {

    public SessionInfo() {
        super("session info", "sessioninfo", "see ur sesh info.", 20, 100, Category.BEACHHOUSE);
    }

    private void drawFinale(MatrixStack matrix) {
        assert minecraft.player != null;
        assert minecraft.world != null;
        String playerHealth = String.valueOf((int) minecraft.world.getMoonPhase());
        minecraft.textRenderer.drawWithShadow(matrix, minecraft.world.getBiomeAccess() + "", getX(), getY() + 10, 0xfffffff);
        minecraft.textRenderer.drawWithShadow(matrix, minecraft.world.getMaxLightLevel() + "", getX(), getY() + 20, 0xfffffff);
        minecraft.textRenderer.drawWithShadow(matrix, minecraft.world.getSpawnPos() + "", getX(), getY() + 30, 0xfffffff);
        minecraft.textRenderer.drawWithShadow(matrix, minecraft.world.getLunarTime() + "", getX(), getY() + 40, 0xfffffff);
        minecraft.textRenderer.drawWithShadow(matrix, playerHealth, getX(), getY() + 50, 0xfffffff);
        System.out.println("nigger" + minecraft.world.getMoonPhase());

        /*
        minecraft.world.getBiome()
        minecraft.world.getMaxLightLevel()
        minecraft.world.getSpawnPos()
        minecraft.world.getLunarTime()
        minecraft.world.getMoonPhase() --
        minecraft.world.getTime()
        minecraft.world.getTimeOfDay()
        minecraft.world.getProfiler()
        minecraft.world.getSeaLevel()
        */

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
        return 10;
    }

    @Override
    public int getHeight() {
        return 10;
    }
}
