package me.srgantmoomoo.beachhouse.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Button {
    protected MinecraftClient minecraft = MinecraftClient.getInstance();

    public void drawButton(MatrixStack matrix) {}

    public void updateButton(int mouseX, int mouseY) {}

    public void mouseClicked(int mouseX, int mouseY) {}

    public void mouseReleased(int mouseX, int mouseY) {}

    public void keyTyped(int key) {}

    public void closeAllSub() {}
}