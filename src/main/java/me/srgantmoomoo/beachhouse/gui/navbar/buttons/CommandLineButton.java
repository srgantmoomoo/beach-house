package me.srgantmoomoo.beachhouse.gui.navbar.buttons;

import me.srgantmoomoo.beachhouse.gui.commandline.CommandLineScreen;
import me.srgantmoomoo.beachhouse.gui.navbar.Button;
import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

public class CommandLineButton extends Button {
    int x;
    int y;
    int addx;
    int addy;

    public CommandLineButton() {
        x = 391;
        y = 1;
        addx = 67;
        addy = 12;
    }

    @Override
    public void drawButton(MatrixStack matrix) {
        InGameHud.fill(matrix, x, y, x + addx, y + addy, 0x90000000);
        minecraft.textRenderer.drawWithShadow(matrix, "command line", x + 2, y  + 2, 0xffffffff);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if(isMouseOnButton(mouseX, mouseY)) {
            if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) {
                minecraft.openScreen(new CommandLineScreen());
                Bedroom.moduleManager.getModule("command line").setEnabled(true);
                Bedroom.moduleManager.getModule("hud editor").setEnabled(false);
                Bedroom.moduleManager.getModule("click gui").setEnabled(false);
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

