package me.srgantmoomoo.beachhouse.gui.clickgui.components;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.clickgui.Component;
import me.srgantmoomoo.beachhouse.module.modules.beachhouse.ClickGui;
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
        if(onWall() && ClickGui.INSTANCE.dynamicSide.isEnabled()) {
            if (this.op.isEnabled()) {
                InGameHud.fill(matrix, parent.parent.getX() - 2, parent.parent.getY() - 12 + offset, parent.parent.getX() - 92, parent.parent.getY() + offset, 0xff11c1e8);
            } else {
                InGameHud.fill(matrix, parent.parent.getX() - 2, parent.parent.getY() - 12 + offset, parent.parent.getX() - 92, parent.parent.getY() + offset, 0x90000000);
            }

            Reference.textRenderer.drawWithShadow(matrix, this.op.name, parent.parent.getX() - 90, (parent.parent.getY() + offset - 10), -1);
        }else {
            if (this.op.isEnabled()) {
                InGameHud.fill(matrix, parent.parent.getX() + 90, parent.parent.getY() - 12 + offset, parent.parent.getX() + 90 + parent.parent.getWidth(), parent.parent.getY() + offset, 0xff11c1e8);
            } else {
                InGameHud.fill(matrix, parent.parent.getX() + 90, parent.parent.getY() - 12 + offset, parent.parent.getX() + 90 + parent.parent.getWidth(), parent.parent.getY() + offset, 0x90000000);
            }

            Reference.textRenderer.drawWithShadow(matrix, this.op.name, parent.parent.getX() + 92, (parent.parent.getY() + offset - 10), -1);
        }
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        if(onWall() && ClickGui.INSTANCE.dynamicSide.isEnabled()) {
            this.y = parent.parent.getY() - 12 + this.offset;
            this.x = parent.parent.getX() - 2;
        }else {
            this.y = parent.parent.getY() - 12 + this.offset;
            this.x = parent.parent.getX() + 90;
        }
    }

    private boolean mouseHeld = false;
    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if (isMouseOnButton(mouseX, mouseY)) {
            if(GLFW.glfwGetMouseButton(Reference.window.getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                mouseHeld = true;
                if (this.parent.isOpen()) {
                    this.op.setEnabled(!op.isEnabled());
                }
            }else if(GLFW.glfwGetMouseButton(Reference.window.getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE)
                mouseHeld = false;
        }
    }

    public boolean onWall() {
        int secondWidth = Reference.minecraft.getWindow().getScaledWidth() - (parent.parent.getX() + 90);
        if(secondWidth < 89)
            return true;
        else
            return false;
    }

    public boolean isMouseOnButton(int x, int y) {
        if(onWall() && ClickGui.INSTANCE.dynamicSide.isEnabled()) {
            if (x < this.x && x > this.x - 92 && y > this.y && y < this.y + 12) {
                return true;
            } else {
                return false;
            }
        }else {
            if (x > this.x && x < this.x + 90 && y > this.y && y < this.y + 12) {
                return true;
            } else {
                return false;
            }
        }
    }
}