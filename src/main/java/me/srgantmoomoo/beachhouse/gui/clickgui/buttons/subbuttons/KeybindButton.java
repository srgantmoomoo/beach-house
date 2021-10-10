package me.srgantmoomoo.beachhouse.gui.clickgui.buttons.subbuttons;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.clickgui.Button;
import me.srgantmoomoo.beachhouse.gui.clickgui.buttons.ModuleButton;
import me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.ClickGui;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class KeybindButton extends Button {
    private boolean isBinding;
    private ModuleButton parent;
    private int offset;
    private int x;
    private int y;

    public KeybindButton(ModuleButton parent, int offset) {
        this.parent = parent;
        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
        this.offset = offset;
    }

    @Override
    public void renderComponent(MatrixStack matrix) {
        //TODO make this less fucking ugly wtf
        InGameHud.fill(matrix, parent.parent.getX() + parent.newx(), parent.parent.getY() + parent.newy() + offset, parent.parent.getX() + parent.newx() + parent.newwidth(), parent.parent.getY() + offset, 0x90000000);

        if (isBinding) {
            Reference.textRenderer.drawWithShadow(matrix, "Listening" + Formatting.GRAY + " ...", parent.parent.getX() + parent.stringx(), (parent.parent.getY() + offset - 10), -1);
        } else {
            String keyName = GLFW.glfwGetKeyName(this.parent.mod.getKey(), GLFW.glfwGetKeyScancode(this.parent.mod.getKey()));
            Reference.textRenderer.drawWithShadow(matrix, "Bind" + Formatting.GRAY + " " + keyName, parent.parent.getX() + parent.stringx(), (parent.parent.getY() + offset - 10), -1);
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
        if (this.parent.isOpen()) {
            if(isMouseOnButton(mouseX, mouseY)) {
                if (GLFW.glfwGetMouseButton(Reference.window.getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                    mouseHeld = true;
                    this.isBinding = !this.isBinding;
                } else if (GLFW.glfwGetMouseButton(Reference.window.getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE)
                    mouseHeld = false;
            }
        }
    }

    @Override
    public void keyTyped(int key) {
        if (this.isBinding) {
            if(GLFW.glfwGetKey(Reference.window.getHandle(), GLFW.GLFW_KEY_BACKSPACE) == GLFW.GLFW_PRESS) {
                this.parent.mod.setKey(0);
                this.isBinding = false;
            }else if(GLFW.glfwGetKey(Reference.window.getHandle(), GLFW.GLFW_KEY_DELETE) == GLFW.GLFW_PRESS) {
                this.parent.mod.setKey(0);
                this.isBinding = false;
            }else if(GLFW.glfwGetKey(Reference.window.getHandle(), GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS) {
                this.isBinding = false;
            }else {
                this.parent.mod.setKey(key);
                this.isBinding = false;
            }
        }
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