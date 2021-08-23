package me.srgantmoomoo.beachhouse.gui.clickgui.components;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.clickgui.Component;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class KeybindComponent extends Component {
    private boolean isBinding;
    private ModuleButton parent;
    private int offset;
    private int x;
    private int y;

    public KeybindComponent(ModuleButton parent, int offset) {
        this.parent = parent;
        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
        this.offset = offset;
    }

    @Override
    public void renderComponent(MatrixStack matrix) {
        // the second x value has a +90 instaed of +width because in this case, we are drawing the panel out 90 pixels rather than 88, width would return 88.
        InGameHud.fill(matrix, parent.parent.getX() + 90, parent.parent.getY() - 12 + offset, parent.parent.getX() + 90 + parent.parent.getWidth(), parent.parent.getY() + offset, 0x90000000);

        if (isBinding) {
            Reference.textRenderer.drawWithShadow(matrix, "Listening" + Formatting.GRAY + " " + "...", parent.parent.getX() + 92, (parent.parent.getY() + offset - 10), -1);
        } else {
            //Reference.textRenderer.drawWithShadow(matrix, "Bind", parent.parent.getX() + 92, (parent.parent.getY() + offset - 10), -1);
            Reference.textRenderer.drawWithShadow(matrix, "Bind" + Formatting.GRAY + " " + GLFW.glfwGetKeyName(this.parent.mod.getKey(), GLFW.glfwGetKeyScancode(this.parent.mod.getKey())), parent.parent.getX() + 92, (parent.parent.getY() + offset - 10), -1);
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
        if (this.parent.isOpen()) {
            if(isMouseOnButton(mouseX, mouseY)) {
                if (GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                    mouseHeld = true;
                    this.isBinding = !this.isBinding;
                } else if (GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE)
                    mouseHeld = false;
            }
        }
    }

    @Override
    public void keyTyped(int key) {
        if (this.isBinding) {
            if(GLFW.glfwGetKey(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_KEY_BACKSPACE) == GLFW.GLFW_PRESS) {
                this.parent.mod.setKey(0);
                this.isBinding = false;
            }else if(GLFW.glfwGetKey(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_KEY_DELETE) == GLFW.GLFW_PRESS) {
                this.parent.mod.setKey(0);
                this.isBinding = false;
            }else if(GLFW.glfwGetKey(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS) {
                this.isBinding = false;
            }else {
                this.parent.mod.setKey(key);
                this.isBinding = false;
            }
        }
    }

    public boolean isMouseOnButton(int x, int y) {
        if (x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12) {
            return true;
        } else {
            return false;
        }
    }
}