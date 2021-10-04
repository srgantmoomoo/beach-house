package me.srgantmoomoo.beachhouse.gui.hud.hudmodules;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;

public class SessionInfo extends HudModule {

    public SessionInfo() {
        super("session info", "sessioninfo", "see ur sesh info.", 70, 16, Category.BEACHHOUSE);
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
        return 92;
    }

    @Override
    public int getHeight() {
        return 70;
    }

    private void drawFinale(MatrixStack matrix) {
        assert minecraft.player != null;
        assert minecraft.world != null;
        minecraft.textRenderer.drawWithShadow(matrix, "hello", getX(), getY(), 0xff11c1e8);

        minecraft.textRenderer.drawWithShadow(matrix, minecraft.world.isRaining() ? Formatting.GRAY + "weather" + Formatting.WHITE + " : rainy"
                : Formatting.GRAY + "weather" + Formatting.WHITE + " : sunny with a chance of meatballs.", getX(), getY() + 10, 0xffffffff);

        minecraft.textRenderer.drawWithShadow(matrix, Formatting.GRAY + "game time" + Formatting.WHITE + " : " + minecraft.world.getTimeOfDay(), getX(), getY() + 20, 0xffffffff);

        minecraft.textRenderer.drawWithShadow(matrix, Formatting.GRAY + "time played" + Formatting.WHITE + " : " + minecraft.player.age, getX(), getY() + 30, 0xffffffff);

        minecraft.textRenderer.drawWithShadow(matrix, Formatting.GRAY + "player speed" + Formatting.WHITE + " : " + minecraft.player.getMovementSpeed(), getX(), getY() + 40, 0xffffffff);

        minecraft.textRenderer.drawWithShadow(matrix, Formatting.GRAY + "server" + Formatting.WHITE + " : " + minecraft.world.getServer() + " " + minecraft.player.getServerBrand(), getX(), getY() + 50, 0xffffffff);

        minecraft.textRenderer.drawWithShadow(matrix, Formatting.GRAY + "chunk pos" + Formatting.WHITE + " : " + minecraft.player.getChunkPos(), getX(), getY() + 60, 0xffffffff);

    }

}
    // time - minecraft.world.getLunarTime()
    //minecraft.world.isRaining()

    //playername
    //weather
    //time
    //time played
    //speed
    //ping
    //fps
    //tps

        /*
        minecraft.world.getBiome()
        minecraft.world.getMaxLightLevel()
        minecraft.world.getSpawnPos()
        minecraft.world.getLunarTime()
        minecraft.world.getMoonPhase()
        minecraft.world.getTime()--
        minecraft.world.getTimeOfDay()
        minecraft.world.getProfiler()
        minecraft.world.getSeaLevel()
        */

        /*
        minecraft.player.forwardSpeed --
        minecraft.player.age --
        minecraft.player.getServer() --
        minecraft.player.getChunkPos()
        minecraft.player.getBrightnessAtEyes() --
        minecraft.player.getDimensions()
        minecraft.player.getMovementSpeed() --
        minecraft.player.getLuck()
        minecraft.player.getAttacker()
        minecraft.player.getMovementDirection()
        minecraft.player.deathTime --
        minecraft.player.getServer() + " : " + minecraft.player.getServerBrand()
         */
