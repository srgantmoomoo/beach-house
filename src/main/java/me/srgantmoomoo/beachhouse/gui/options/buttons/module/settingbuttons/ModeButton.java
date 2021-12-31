package me.srgantmoomoo.beachhouse.gui.options.buttons.module.settingbuttons;

import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.options.buttons.module.ModuleButton;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class ModeButton extends Button {
    private ModeSetting op;
    private ModuleButton parent;
    private int x;
    private int y;
    private int offset;

    public ModeButton(ModeSetting op, ModuleButton parent, int offset) {
        this.op = op;
        this.parent = parent;
        this.offset = offset;
        this.x = 300 + 134;
        this.y = 100 + offset;
    }

    @Override
    public void drawButton(MatrixStack matrix) {
        minecraft.textRenderer.drawWithShadow(matrix, this.op.name + " " + Formatting.GRAY + this.op.getMode(), x, y, -1);
    }

    private boolean mouseHeld = false;
    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if(!parent.open)
            return;

        if(isMouseOnButton(mouseX, mouseY)) {
            if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                mouseHeld = true;
                op.cycle();
            }else if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE && mouseHeld)
                mouseHeld = false;
        }
    }

    public boolean isMouseOnButton(int xx, int yy) {
        return xx > x && xx < x + minecraft.textRenderer.getWidth(op.name + " " + this.op.getMode()) && yy > y  && yy < y + minecraft.textRenderer.fontHeight;
    }

}
