package me.srgantmoomoo.beachhouse.modules.beachhouse;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.api.event.events.EventDrawOverlay;
import me.srgantmoomoo.bedroom.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

public class PlayerInfo extends Module {

    public PlayerInfo() {
        super("player info", "playerinfo", "sucks ur pp for u.", 0, Category.BEACHHOUSE);
    }

    @EventHandler
    private final Listener<EventDrawOverlay> overlayListener = new Listener<>(e -> {
        TextRenderer tr = MinecraftClient.getInstance().textRenderer;
        int screenWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int screenHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();

        InGameHud.fill(e.matrix, screenWidth - 2, screenHeight - 2, screenWidth - 108, screenHeight - 46, 0x80000000); //0x60EB78DF

        tr.drawWithShadow(e.matrix, mc.player.getName(), screenWidth - tr.getWidth(mc.player.getName()) - 6, screenHeight - 14, 0xffffffff);

        // health string & bar
        healthString(e.matrix, tr, screenWidth, screenHeight);
        healthBar(e.matrix, screenWidth, screenHeight);

        // mainhand and offhand items
        int x = 1;
        for(ItemStack itemStack : mc.player.getItemsHand()) {

            mc.getItemRenderer().renderGuiItemIcon(itemStack, screenWidth - 108 + x, screenHeight - 19);
            x += 20;
            //mc.getItemRenderer().renderGuiItemIcon(itemStack.split(1), 0 ,0);
        }

        // armor items
        int x1 = 1;
        for(ItemStack itemStack : mc.player.getArmorItems()) {
            mc.getItemRenderer().renderGuiItemIcon(itemStack, screenWidth - 20 + x1, screenHeight - 44);
            x1 += -18;
        }
    });

    private final Identifier FULL_HEALTH = new Identifier(Main.modid, "full.png");
    private final Identifier MODERATE_HEALTH = new Identifier(Main.modid, "moderate.png");
    private final Identifier WARNING_HEALTH = new Identifier(Main.modid, "warning.png");
    private final Identifier DANGER_HEALTH = new Identifier(Main.modid, "danger.png");

    public void healthBar(MatrixStack matrix, int scrWidth, int scrHeight) {

        if(mc.player.getHealth() == 20) {
            mc.getTextureManager().bindTexture(FULL_HEALTH);
            InGameHud.drawTexture(matrix, scrWidth - 101, scrHeight - 43, 24, 24, 0, 0, 24, 24, 24, 24);
        }

        if(mc.player.getHealth() < 20 && mc.player.getHealth() > 10) {
            mc.getTextureManager().bindTexture(MODERATE_HEALTH);
            InGameHud.drawTexture(matrix, scrWidth - 101, scrHeight - 43, 24, 24, 0, 0, 24, 24, 24, 24);
        }

        if(mc.player.getHealth() <= 10 && mc.player.getHealth() > 5) {
            mc.getTextureManager().bindTexture(WARNING_HEALTH);
            InGameHud.drawTexture(matrix, scrWidth - 101, scrHeight - 43, 24, 24, 0, 0, 24, 24, 24, 24);
        }

        if(mc.player.getHealth() <= 5) {
            mc.getTextureManager().bindTexture(DANGER_HEALTH);
            InGameHud.drawTexture(matrix, scrWidth - 101, scrHeight - 43, 24, 24, 0, 0, 24, 24, 24, 24);
        }
    }

    public void healthString(MatrixStack matrix, TextRenderer tr, int scrWidth, int scrHeight) {

        String playerHealth = String.valueOf((int) mc.player.getHealth());
        if(mc.player.getHealth() == 20) {
            tr.drawWithShadow(matrix, playerHealth, scrWidth - tr.getWidth(playerHealth) - 82, scrHeight - 34, 0xff00ff00);
        }

        if(mc.player.getHealth() < 20 && mc.player.getHealth() > 10) {
            tr.drawWithShadow(matrix, playerHealth, scrWidth - tr.getWidth(playerHealth) - 82, scrHeight - 34, 0xffffffff);
        }

        if(mc.player.getHealth() <= 10 && mc.player.getHealth() > 5) {
            tr.drawWithShadow(matrix, playerHealth, scrWidth - tr.getWidth(playerHealth) - 84, scrHeight - 34, 0xffffa500);
        }

        if(mc.player.getHealth() <= 5) {
            tr.drawWithShadow(matrix, playerHealth, scrWidth - tr.getWidth(playerHealth) - 84, scrHeight - 34, 0xffff0000);
        }
    }
}
