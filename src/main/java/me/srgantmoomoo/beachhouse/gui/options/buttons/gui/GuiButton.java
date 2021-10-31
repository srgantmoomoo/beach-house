package me.srgantmoomoo.beachhouse.gui.options.buttons.gui;

import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.options.buttons.hud.HudButton;
import me.srgantmoomoo.beachhouse.gui.options.buttons.utilities.UtilitiesButton;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

public class GuiButton extends Button {
    int x;
    int y;
    int addx;
    int addy;
    public static boolean selected = false;

    public GuiButton() {
        x = 300 + 1;
        y = 80;
        addx = 20;
        addy = 12;
    }

    @Override
    public void drawButton(MatrixStack matrix) {
        InGameHud.fill(matrix, x, y, x + addx, y + addy, 0x90000000);
        minecraft.textRenderer.drawWithShadow(matrix, "gui", x + 3, y  + 2, 0xffffffff);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if(isMouseOnButton(mouseX, mouseY)) {
            if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) {
                selected = true;
                HudButton.selected = false;
                UtilitiesButton.selected = false;
            }
        }
    }

    public boolean isMouseOnButton(int xx, int yy) {
        if (xx > x && xx < x + addx && yy > y && yy < y + addy) {
            return true;
        } else {
            return false;
        }
    }

}
