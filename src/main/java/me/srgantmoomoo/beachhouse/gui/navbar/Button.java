package me.srgantmoomoo.beachhouse.gui.navbar;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class Button {

    protected MinecraftClient minecraft = MinecraftClient.getInstance();

    public void drawButton(MatrixStack matrix) {}

    public void updateComponent(int mouseX, int mouseY) {}

    public void mouseClicked(int mouseX, int mouseY) {}

    public void mouseReleased(int mouseX, int mouseY) {}

    public void keyTyped(int key) {}

    public void closeAllSub() {}

    public void setOff(final int newOff) {}

}
