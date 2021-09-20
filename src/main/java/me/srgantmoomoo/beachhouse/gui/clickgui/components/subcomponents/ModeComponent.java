package me.srgantmoomoo.beachhouse.gui.clickgui.components.subcomponents;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.clickgui.Component;
import me.srgantmoomoo.beachhouse.gui.clickgui.components.ModuleComponent;
import me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.ClickGui;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class ModeComponent extends Component {
    private ModeSetting op;
    private ModuleComponent parent;
    private int offset;
    private int x;
    private int y;
    private int modeIndex;

    public ModeComponent(ModeSetting op, ModuleComponent parent, int offset) {
        this.op = op;
        this.parent = parent;
        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
        this.offset = offset;
        this.modeIndex = 0;
    }
    public boolean toBig = false;
    public boolean hovered = false;

    @Override
    public void renderComponent(MatrixStack matrix) {
        InGameHud.fill(matrix, parent.parent.getX() + parent.newx(), parent.parent.getY() + parent.newy() + offset, parent.parent.getX() + parent.newx() + parent.newwidth(), parent.parent.getY() + offset, 0x90000000);

        if (Reference.textRenderer.getWidth(this.op.name + " " + Formatting.GRAY + this.op.getMode()) > 86)
            toBig = true;
        else if (Reference.textRenderer.getWidth(this.op.name + " " + Formatting.GRAY + this.op.getMode()) <= 86)
            toBig = false;

        if (toBig && !hovered) {
            Reference.textRenderer.drawWithShadow(matrix, this.op.name + " " + Formatting.GRAY + "...", parent.parent.getX() + parent.stringx(), (parent.parent.getY() + offset - 10), -1);
        } else {
            Reference.textRenderer.drawWithShadow(matrix, this.op.name + " " + Formatting.GRAY + this.op.getMode(), parent.parent.getX() + parent.stringx(), (parent.parent.getY() + offset - 10), -1);
        }
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.y = parent.parent.getY() + parent.newy() + this.offset;
        this.x = parent.parent.getX() + parent.newx();
    }

    private boolean mouseHeld = false;
    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if (this.isMouseOnButton(mouseX, mouseY)) {
            hovered = true;
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
            hovered = false;
    }

    public boolean isMouseOnButton(int x, int y) {
        if(parent.onWall() && ClickGui.INSTANCE.dynamicSide.isEnabled()) {
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