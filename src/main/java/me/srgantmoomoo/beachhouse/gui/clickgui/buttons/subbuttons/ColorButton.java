package me.srgantmoomoo.beachhouse.gui.clickgui.buttons.subbuttons;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.ClickGui;
import me.srgantmoomoo.beachhouse.gui.clickgui.Button;
import me.srgantmoomoo.beachhouse.gui.clickgui.buttons.ModuleButton;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

public class ColorButton extends Button {
    private ColorSetting op;
    private ModuleButton parent;
    private int offset;
    private int x;
    private int y;

    public ColorButton(ColorSetting op, ModuleButton parent, int offset) {
        this.op = op;
        this.parent = parent;
        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
        this.offset = offset;
    }

    @Override
    public void renderComponent(MatrixStack matrix) {
        InGameHud.fill(matrix, parent.parent.getX() + parent.newx(), parent.parent.getY() + parent.newy() + offset, parent.parent.getX() + parent.newx() + parent.newwidth(), parent.parent.getY() + offset, 0x90000000);

        Reference.textRenderer.drawWithShadow(matrix, this.op.name, parent.parent.getX() + parent.stringx(), (parent.parent.getY() + offset - 10), -1);
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.y = parent.parent.getY() + parent.newy() + this.offset;
        this.x = parent.parent.getX() + parent.newx();
    }

    private boolean mouseHeld = false;

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if(isMouseOnButton(mouseX, mouseY)) {
            if(GLFW.glfwGetMouseButton(Reference.window.getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                mouseHeld = true;
                if(this.parent.isOpen()) {
                }
            }else if(GLFW.glfwGetMouseButton(Reference.window.getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE)
                mouseHeld = false;
        }
    }

    public boolean isMouseOnButton(int x, int y) {
        if (parent.onWall() && ClickGui.INSTANCE.interactWithWall.isEnabled()) {
            if (x < this.x && x > this.x + parent.newwidth() && y > this.y && y < this.y + 12) {
                return true;
            } else {
                return false;
            }
        } else {
            if (x > this.x && x < this.x + parent.newwidth() && y > this.y && y < this.y + 12) {
                return true;
            } else {
                return false;
            }
        }
    }
}
