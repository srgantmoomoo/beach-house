package me.srgantmoomoo.beachhouse.gui.chat;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class ChatScreenRenderer {
    public static ChatScreen chatScreen;

    // this is called in MixinInGameHud
    public static void renderChatBox(MatrixStack matrix) {
        if (Reference.minecraft.currentScreen instanceof ChatScreen) {
            IChatScreen chatScreen = (IChatScreen) (ChatScreen) Reference.minecraft.currentScreen;
        
            if(chatScreen.getText().startsWith(Bedroom.INSTANCE.commandManager.prefix)) {
                int screenWidth = Reference.window.getScaledWidth();
                int screenHeight = Reference.window.getScaledHeight();

                if (chatScreen.getText().equals(Bedroom.INSTANCE.commandManager.prefix))
                    Reference.textRenderer.drawWithShadow(matrix, "beach house :)", 6, screenHeight - 12, 0xff999999);

                if(me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.ChatScreen.INSTANCE.background.isEnabled())
                    Reference.blur.render(1);

                final int[] counter = {1};
                // left
                InGameHud.fill(matrix, 1, screenHeight - 14, 2, screenHeight - 2, rainbow(counter[0] * 300));
                // right
                InGameHud.fill(matrix, screenWidth - 1, screenHeight - 14, screenWidth - 2, screenHeight - 2, rainbow(counter[0] * 300));
                // top
                InGameHud.fill(matrix, 1, screenHeight - 14, screenWidth - 1, screenHeight - 15, rainbow(counter[0] * 300));
                // bottom
                InGameHud.fill(matrix, 1, screenHeight - 2, screenWidth - 1, screenHeight - 1, rainbow(counter[0] * 300));
                counter[0]++;
            }
        }
    }

    private static int rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbowState %= -360;
        return Color.getHSBColor((float) (rainbowState / -360.0f), 0.5f, 1f).getRGB();
    }
}
