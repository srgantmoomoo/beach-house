package me.srgantmoomoo.beachhouse.module.modules.beachhouse;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.events.DrawOverlayEvent;
import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class PlayerInfo extends Module {

    public PlayerInfo() {
        super("player info", "playerinfo", "sucks ur pp for u.", 0, Category.BEACHHOUSE);
    }
    public MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onEvent(Event e) {
        if(e instanceof DrawOverlayEvent) {
            TextRenderer tr = MinecraftClient.getInstance().textRenderer;
            int screenWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
            int screenHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();

            InGameHud.fill(((DrawOverlayEvent) e).matrix, screenWidth - 2, screenHeight - 2, screenWidth - 108, screenHeight - 46, 0x80000000); //0x60EB78DF

            tr.drawWithShadow(((DrawOverlayEvent) e).matrix, mc.player.getName(), screenWidth - tr.getWidth(mc.player.getName()) - 6, screenHeight - 14, 0xffffffff);

            healthString(((DrawOverlayEvent) e).matrix, tr, screenWidth, screenHeight);
            healthBar(((DrawOverlayEvent) e).matrix, screenWidth, screenHeight);

            // mainhand and offhand items
            int x = 1;
            for (ItemStack itemStack : mc.player.getItemsHand()) {
                mc.getItemRenderer().renderGuiItemIcon(itemStack, screenWidth - 108 + x, screenHeight - 19);
                x += 20;
                //mc.getItemRenderer().renderGuiItemIcon(itemStack.split(1), 0 ,0);
            }

            // armor items
            int x1 = 1;
            for (ItemStack itemStack : mc.player.getArmorItems()) {
                mc.getItemRenderer().renderGuiItemIcon(itemStack, screenWidth - 20 + x1, screenHeight - 44);
                x1 += -18;
            }
        }
    }

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
