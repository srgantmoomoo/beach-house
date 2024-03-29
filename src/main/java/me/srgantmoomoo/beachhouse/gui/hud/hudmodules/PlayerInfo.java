package me.srgantmoomoo.beachhouse.gui.hud.hudmodules;

import com.mojang.blaze3d.systems.RenderSystem;
import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class PlayerInfo extends HudModule {

    //TODO fix player name going off the rect when too long.
    public PlayerInfo() {
        super("player info", "playerinfo", "sucks ur pp for u.", 2, 88, Category.BEACHHOUSE);
    }

    private final Identifier FULL_HEALTH = new Identifier(Main.INSTANCE.modid, "healthbar/full.png");
    private final Identifier MODERATE_HEALTH = new Identifier(Main.INSTANCE.modid, "healthbar/moderate.png");
    private final Identifier WARNING_HEALTH = new Identifier(Main.INSTANCE.modid, "healthbar/warning.png");
    private final Identifier DANGER_HEALTH = new Identifier(Main.INSTANCE.modid, "healthbar/danger.png");

    @Override
    public void draw(MatrixStack matrix) {
        drawFinale(matrix);

        super.draw(matrix);
    }

    @Override
    public void drawDraggable(MatrixStack matrix, int mouseX, int mouseY) {
        drawFinale(matrix);
        Main.INSTANCE.hudManager.drawIndicator(matrix, getX(), getY(), hudEnabled ? 0xff00ff00 : 0xffffffff);

        super.drawDraggable(matrix, mouseX, mouseY);
    }

    @Override
    public int getWidth() {
        return 106;
    }

    @Override
    public int getHeight() {
        return 46;
    }

    private void drawFinale(MatrixStack matrix) {
        InGameHud.fill(matrix, getX(), getY(), getX() + getWidth(), getY() + getHeight(), 0x90000000); //0x60EB78DF

        assert minecraft.player != null;
        minecraft.textRenderer.drawWithShadow(matrix, minecraft.player.getName(), (getX() + getWidth()) - (minecraft.textRenderer.getWidth(minecraft.player.getName()) + 2), getY() + 34, 0xffffffff);

        healthString(matrix);
        healthBar(matrix);

        // mainhand and offhand items
        int x = 1;
        for (ItemStack itemStack : minecraft.player.getItemsHand()) {
            minecraft.getItemRenderer().renderGuiItemIcon(itemStack, getX() + 1 + x, getY() + 28);
            minecraft.getItemRenderer().renderGuiItemOverlay(minecraft.textRenderer, itemStack, getX() + 1 + x, getY() + 28);
            x += 20;
            //mc.getItemRenderer().renderGuiItemIcon(itemStack.split(1), 0 ,0);
        }

        // armor items
        int x1 = 1;
        for (ItemStack itemStack : minecraft.player.getArmorItems()) {
            minecraft.getItemRenderer().renderGuiItemIcon(itemStack, getX() + getWidth() - 19 + x1, getY() + 4);
            minecraft.getItemRenderer().renderGuiItemOverlay(minecraft.textRenderer, itemStack, getX() + getWidth() - 19 + x1, getY() + 4);
            x1 += -18;
        }

    }

    private void healthBar(MatrixStack matrix) {
        assert minecraft.player != null;
        if(minecraft.player.getHealth() == 20) {
            drawBar(matrix, FULL_HEALTH);
        }

        if(minecraft.player.getHealth() < 20 && minecraft.player.getHealth() > 10) {
            drawBar(matrix, MODERATE_HEALTH);
        }

        if(minecraft.player.getHealth() <= 10 && minecraft.player.getHealth() > 5) {
            drawBar(matrix, WARNING_HEALTH);
        }

        if(minecraft.player.getHealth() <= 5) {
            drawBar(matrix, DANGER_HEALTH);
        }
    }

    private void drawBar(MatrixStack matrix, Identifier identifier) {
        RenderSystem.setShaderTexture(0, identifier);
        InGameHud.drawTexture(matrix, getX() + 8, getY() + 4, 22, 22, 0, 0, 22, 22, 22, 22);
    }

    private void healthString(MatrixStack matrix) {
        assert minecraft.player != null;
        String playerHealth = String.valueOf((int) minecraft.player.getHealth());

        if(minecraft.player.getHealth() == 20) {
            int x = 14;
            minecraft.textRenderer.drawWithShadow(matrix, playerHealth, getX() + x, getY() + 12, 0xff00ff00);
        }

        if(minecraft.player.getHealth() < 20 && minecraft.player.getHealth() >= 10) {
            int x = 14;
            minecraft.textRenderer.drawWithShadow(matrix, playerHealth, getX() + x, getY() + 12, 0xffffffff);
        }

        if(minecraft.player.getHealth() < 10 && minecraft.player.getHealth() > 5) {
            int x = 17;
            minecraft.textRenderer.drawWithShadow(matrix, playerHealth, getX() + x, getY() + 12, 0xffffa500);
        }

        if(minecraft.player.getHealth() <= 5) {
            int x = 17;
            minecraft.textRenderer.drawWithShadow(matrix, playerHealth, getX() + x, getY() + 12, 0xffff0000);
        }
    }

}
