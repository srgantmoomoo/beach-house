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
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class PlayerInfo extends Module {

    public PlayerInfo() {
        super("player info", "playerinfo", "sucks ur pp for u.", 0, Category.BEACHHOUSE);
    }

    private final Identifier TEXTURE = new Identifier(Main.modid, "circletran.png");

    @EventHandler
    private final Listener<EventDrawOverlay> overlayListener = new Listener<>(e -> {
        TextRenderer tr = MinecraftClient.getInstance().textRenderer;
        int screenWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int screenHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();

        InGameHud.fill(e.matrix, screenWidth - 2, screenHeight - 2, screenWidth - 104, screenHeight - 44, 0x80000000); //0x60EB78DF

        String playerHealth = String.valueOf((int) mc.player.getHealth());
        tr.drawWithShadow(e.matrix, playerHealth, screenWidth - tr.getWidth(playerHealth) - 83, screenHeight - 33, mc.player.getHealth() == 20 ? 0xff00ff00 : mc.player.getHealth() <= 10 ? 0xffffff00 :
                mc.player.getHealth() <= 5 ? 0xffff0000 : 0xffffffff);

        mc.getTextureManager().bindTexture(TEXTURE);
        InGameHud.drawTexture(e.matrix, screenWidth - 102, screenHeight - 42, 24, 24, 0, 0, 24, 24, 24, 24);
    });
}
