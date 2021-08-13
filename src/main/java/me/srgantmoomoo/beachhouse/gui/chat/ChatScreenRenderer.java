package me.srgantmoomoo.beachhouse.gui.chat;

import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;


public class ChatScreenRenderer { //((AccessorChatScreen) chatScreen).getChatField().getText()
    public static ChatScreen chatScreen;

    public static void renderChatBox(MatrixStack matrix) {
        /*chatScreen = new ChatScreen("chat_screen.title");
        if(Reference.minecraft.currentScreen instanceof ChatScreen) {
            if (chatScreen.chatField.getText().startsWith(CommandManager.prefix)) {
                int screenWidth = Reference.window.getScaledWidth();
                int screenHeight = Reference.window.getScaledHeight();

                if (((AccessorChatScreen) chatScreen).getChatField().getText().equals(CommandManager.prefix))
                    Reference.textRenderer.drawWithShadow(matrix, "beach house :)", 6, screenHeight - 12, 0xff999999);

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
        }*/
    }

    private static int rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbowState %= -360;
        return Color.getHSBColor((float) (rainbowState / -360.0f), 0.5f, 1f).getRGB();
    }
}
