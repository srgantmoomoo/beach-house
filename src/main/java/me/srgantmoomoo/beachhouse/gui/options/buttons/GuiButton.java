package me.srgantmoomoo.beachhouse.gui.options.buttons;

import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.options.buttons.module.ModuleButton;
import me.srgantmoomoo.bedroom.Bedroom;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class GuiButton extends Button {
    int x;
    int y;
    int addx;
    int addy;
    public static boolean selected = false;
    public ArrayList<Button> buttons;

    public GuiButton() {
        x = 300 + 1;
        y = 80;
        addx = 20;
        addy = 12;
        this.buttons = new ArrayList<>();

        ModuleButton button1 = new ModuleButton(Bedroom.INSTANCE.moduleManager.getModuleByID("clickgui"), 0);
        this.buttons.add(button1);

        ModuleButton button2 = new ModuleButton(Bedroom.INSTANCE.moduleManager.getModuleByID("commandline"), 20);
        this.buttons.add(button2);

        ModuleButton button3 = new ModuleButton(Bedroom.INSTANCE.moduleManager.getModuleByID("options"), 40);
        this.buttons.add(button3);
    }

    @Override
    public void drawButton(MatrixStack matrix) {
        InGameHud.fill(matrix, x, y, x + addx, y + addy, 0x90000000);
        minecraft.textRenderer.drawWithShadow(matrix, "gui", x + 3, y  + 2, 0xffffffff);

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
                HudButton.selected = false;
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
        return xx > x && xx < x + addx && yy > y && yy < y + addy;
    }

}
