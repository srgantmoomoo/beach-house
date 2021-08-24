package me.srgantmoomoo.beachhouse.gui.clickgui.components;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.clickgui.Component;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class ModeComponent extends Component {
    private ModeSetting op;
    private ModuleButton parent;
    private int offset;
    private int x;
    private int y;
    private int modeIndex;

    public ModeComponent(ModeSetting op, ModuleButton parent, int offset) {
        this.op = op;
        this.parent = parent;
        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
        this.offset = offset;
        this.modeIndex = 0;
    }
    public boolean toBig = false;
    public boolean hoverCrafted = false;
    @Override
    public void renderComponent(MatrixStack matrix) {
        InGameHud.fill(matrix, parent.parent.getX() + 90, parent.parent.getY() - 12 + offset, parent.parent.getX() + 90 + parent.parent.getWidth(), parent.parent.getY() + offset, 0x90000000);

        if(Reference.textRenderer.getWidth(this.op.name + " " + Formatting.GRAY + this.op.getMode()) > 86) toBig = true;
        else if(Reference.textRenderer.getWidth(this.op.name + " " + Formatting.GRAY + this.op.getMode()) <= 86) toBig = false;

        if(toBig && !hoverCrafted) {
            Reference.textRenderer.drawWithShadow(matrix, this.op.name + " " + Formatting.GRAY + "...", parent.parent.getX() + 92, (parent.parent.getY() + offset - 10), -1);
        }else {
            Reference.textRenderer.drawWithShadow(matrix, this.op.name + " " + Formatting.GRAY + this.op.getMode(), parent.parent.getX() + 92, (parent.parent.getY() + offset - 10), -1);
        }
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.y = parent.parent.getY() - 12 + this.offset;
        this.x = parent.parent.getX() + 90;
    }

    private boolean mouseHeld = false;
    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if (this.isMouseOnButton(mouseX, mouseY)) {
            hoverCrafted = true;
            if(this.parent.isOpen()) {
                if(GLFW.glfwGetMouseButton(Reference.window.getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                    mouseHeld = true;
                    final int maxIndex = this.op.modes.size() - 1;
                    this.modeIndex++;
                    if (this.modeIndex > maxIndex) {
                        this.modeIndex = 0;
                    }
                    this.op.setMode(this.op.modes.get(this.modeIndex));
                }else if(GLFW.glfwGetMouseButton(Reference.window.getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE)
                    mouseHeld = false;
            }
        }else
            hoverCrafted = false;
    }

    public boolean isMouseOnButton(int x, int y) {
        if (x > this.x && x < this.x + 80 && y > this.y && y < this.y + 12) {
            return true;
        } else {
            return false;
        }
    }
}