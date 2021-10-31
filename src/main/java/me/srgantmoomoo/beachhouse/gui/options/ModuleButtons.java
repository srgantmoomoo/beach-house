package me.srgantmoomoo.beachhouse.gui.options;

import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

public class ModuleButtons extends Button {
    public Module mod;
    public int x;
    public int y;
    public int offset;

    public ModuleButtons(Module mod, int offset) {
        this.x = 300 + 2;
        this.y = 96;
        this.offset = offset;

        this.mod = mod;
    }

    @Override
    public void drawButton(MatrixStack matrix) {
        minecraft.textRenderer.drawWithShadow(matrix, mod.getName(), x, y + offset, 0xffffffff);
        //InGameHud.fill(matrix, );
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if(isMouseOnButton(mouseX, mouseY)) {
            if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) {
                mod.toggle();
            }
        }
    }

    public boolean isMouseOnButton(int xx, int yy) {
        if (xx > x && xx < x + minecraft.textRenderer.getWidth(mod.getName()) && yy > y && yy < y + minecraft.textRenderer.fontHeight) {
            return true;
        } else {
            return false;
        }
    }

}
