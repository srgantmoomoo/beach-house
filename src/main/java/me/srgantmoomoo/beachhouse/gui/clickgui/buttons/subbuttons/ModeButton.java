package me.srgantmoomoo.beachhouse.gui.clickgui.buttons.subbuttons;

import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.clickgui.buttons.ModuleButton;
import me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.ClickGui;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class ModeButton extends Button {
    private ModeSetting op;
    private ModuleButton parent;
    private int offset;
    private int x;
    private int y;
    private int modeIndex;

    public ModeButton(ModeSetting op, ModuleButton parent, int offset) {
        this.op = op;
        this.parent = parent;
        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
        this.offset = offset;
        this.modeIndex = 0;
    }
    private boolean tooBig = false;
    private boolean hovered = false;

    @Override
    public void drawButton(MatrixStack matrix) {
        InGameHud.fill(matrix, parent.parent.getX() + parent.newx(), parent.parent.getY() + parent.newy() + offset, parent.parent.getX() + parent.newx() + parent.newwidth(), parent.parent.getY() + offset, 0x90000000);

        if (minecraft.textRenderer.getWidth(this.op.name + " " + Formatting.GRAY + this.op.getMode()) > 86)
            tooBig = true;
        else if (minecraft.textRenderer.getWidth(this.op.name + " " + Formatting.GRAY + this.op.getMode()) <= 86)
            tooBig = false;

        if (tooBig && !hovered) {
            minecraft.textRenderer.drawWithShadow(matrix, this.op.name + " " + Formatting.GRAY + "...", parent.parent.getX() + parent.stringx(), (parent.parent.getY() + offset - 10), -1);
        } else {
            minecraft.textRenderer.drawWithShadow(matrix, this.op.name + " " + Formatting.GRAY + this.op.getMode(), parent.parent.getX() + parent.stringx(), (parent.parent.getY() + offset - 10), -1);
        }
    }

    @Override
    public void updateButton(int mouseX, int mouseY) {
        this.y = parent.parent.getY() + parent.newy() + this.offset;
        this.x = parent.parent.getX() + parent.newx();
    }

    private boolean mouseHeld = false;
    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if (this.isMouseOnButton(mouseX, mouseY)) {
            hovered = true;
            if(this.parent.isOpen()) {
                if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                    op.cycle();
                    mouseHeld = true;
                }else if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE)
                    mouseHeld = false;
            }
        }else
            hovered = false;
    }

    public boolean isMouseOnButton(int x, int y) {
        if(parent.onWall() && ClickGui.INSTANCE.interactWithWall.isEnabled()) {
            if (x < this.x && x > this.x + parent.newwidth() && y > this.y && y < this.y + 12) {
                return true;
            } else {
                return false;
            }
        }else {
            if (x > this.x && x < this.x + parent.newwidth() && y > this.y && y < this.y + 12) {
                return true;
            } else {
                return false;
            }
        }
    }
}