package me.srgantmoomoo.beachhouse.gui.options.buttons;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import me.srgantmoomoo.beachhouse.gui.options.buttons.module.ModuleButton;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class HudButton extends Button {
    int x;
    int y;
    int addx;
    int addy;
    public static boolean selected = false;
    public ArrayList<Button> buttons;

    public HudButton() {
        x = 300 + 22;
        y = 80;
        addx = 23;
        addy = 12;
        this.buttons = new ArrayList<>();

        int offset = 0;
        for (HudModule hudMod : Main.INSTANCE.hudManager.hudModules) {
                ModuleButton hudModButton = new ModuleButton(hudMod, offset);
                this.buttons.add(hudModButton);
            offset += 20;
        }
    }

    @Override
    public void drawButton(MatrixStack matrix) {
        InGameHud.fill(matrix, x, y, x + addx, y + addy, 0x90000000);
        minecraft.textRenderer.drawWithShadow(matrix, "hud", x + 3, y  + 2, 0xffffffff);

        if(selected) {
            for (Button button : buttons) {
                button.drawButton(matrix);
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if(isMouseOnButton(mouseX, mouseY)) {
            if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) {
                selected = true;
                GuiButton.selected = false;
                UtilitiesButton.selected = false;
            }
        }

        if(selected) {
            for (Button button : buttons) {
                button.mouseClicked(mouseX, mouseY);
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