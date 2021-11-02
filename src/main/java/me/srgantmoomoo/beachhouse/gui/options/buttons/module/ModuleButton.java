package me.srgantmoomoo.beachhouse.gui.options.buttons.module;

import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

public class ModuleButton extends Button {
    public Module mod;
    public int x;
    public int y;
    public int offset;

    public ModuleButton(Module mod, int offset) {
        this.x = 300 + 4;
        this.y = 100;
        this.offset = offset;

        this.mod = mod;
    }

    // if module instanceof hudmodule;
    @Override
    public void drawButton(MatrixStack matrix) {
        minecraft.textRenderer.drawWithShadow(matrix, mod.getName(), x, y + offset, modIsEnabled() ? 0xff11c1e8 : 0xffffffff);
        //InGameHud.fill(matrix, );
    }

    private boolean mouseHeld = false;
    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if(isMouseOnButton(mouseX, mouseY)) {
            if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                mouseHeld = true;
                if(mod instanceof HudModule)
                    ((HudModule) mod).hudEnabled = !((HudModule) mod).hudEnabled;
                else
                    mod.toggle();
            }else if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE && mouseHeld)
                mouseHeld = false;
        }
    }

    public boolean modIsEnabled() {
        if(mod instanceof HudModule)
            return ((HudModule) mod).hudEnabled;
        else
            return mod.isEnabled();
    }

    public boolean isMouseOnButton(int xx, int yy) {
        return xx > x && xx < x + minecraft.textRenderer.getWidth(mod.getName()) && yy > y + offset && yy < y + offset + minecraft.textRenderer.fontHeight;
    }

}
