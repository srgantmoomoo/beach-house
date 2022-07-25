package me.srgantmoomoo.beachhouse.gui.navbar.buttons;

import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.options.OptionsScreen;
import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

public class OptionsButton extends Button {
    int x;
    int y;
    int addx;
    int addy;

    public OptionsButton() {
        x = 547;
        y = 1;
        addx = 40;
        addy = 12;
    }

    @Override
    public void drawButton(MatrixStack matrix) {
        InGameHud.fill(matrix, x, y, x + addx, y + addy, 0x90000000);
        minecraft.textRenderer.drawWithShadow(matrix, "options", x + 2, y + 2, 0xffffffff);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if(isMouseOnButton(mouseX, mouseY)) {
            if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) {
                minecraft.openScreen(new OptionsScreen());
                Bedroom.INSTANCE.moduleManager.getModule("options").setEnabled(true);
                Bedroom.INSTANCE.moduleManager.getModule("hud editor").setEnabled(false);
                Bedroom.INSTANCE.moduleManager.getModule("click gui").setEnabled(false);
                Bedroom.INSTANCE.moduleManager.getModule("command line").setEnabled(false);
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