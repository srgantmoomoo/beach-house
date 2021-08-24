package me.srgantmoomoo.beachhouse.gui.clickgui.components;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.clickgui.Component;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

public class BooleanComponent extends Component {
    private BooleanSetting op;
    private ModuleButton parent;
    private int offset;
    private int x;
    private int y;

    public BooleanComponent(BooleanSetting op, ModuleButton parent, int offset) {
        this.op = op;
        this.parent = parent;
        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
        this.offset = offset;
    }

    @Override
    public void renderComponent(MatrixStack matrix) {
        if(this.op.isEnabled()) {
            InGameHud.fill(matrix, parent.parent.getX() + 90, parent.parent.getY() - 12 + offset, parent.parent.getX() + 90 + parent.parent.getWidth(), parent.parent.getY() + offset, 0xff11c1e8);
        } else {
            InGameHud.fill(matrix, parent.parent.getX() + 90, parent.parent.getY() - 12 + offset, parent.parent.getX() + 90 + parent.parent.getWidth(), parent.parent.getY() + offset, 0x90000000);
        }

        Reference.textRenderer.drawWithShadow(matrix, this.op.name, parent.parent.getX() + 92, (parent.parent.getY() + offset - 10), -1);
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.y = parent.parent.getY() - 12 + this.offset;
        this.x = parent.parent.getX() + 90;
    }

    private boolean mouseHeld = false;
    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if (isMouseOnButton(mouseX, mouseY)) {
            if(GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                mouseHeld = true;
                if (this.parent.isOpen()) {
                    this.op.setEnabled(!op.isEnabled());
                }
            }else if(GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE)
                mouseHeld = false;
        }
    }

    public boolean isMouseOnButton(int x, int y) {
        if (x > this.x && x < this.x + 80 && y > this.y && y < this.y + 12) {
            return true;
        } else {
            return false;
        }
    }
}