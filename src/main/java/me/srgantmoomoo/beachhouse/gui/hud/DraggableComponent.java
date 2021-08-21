package me.srgantmoomoo.beachhouse.gui.hud;

import org.lwjgl.glfw.GLFW;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import net.minecraft.client.util.math.MatrixStack;

public class DraggableComponent {

    private int x;
    private int y;
    private int width;
    private int height;
    private int lastX;
    private int lastY;

    private boolean dragging;
    private boolean mouseHeld = false;

    public DraggableComponent(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    public void setXPos(int x) {
        this.x = x;
    }

    public void setYPos(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void draw(MatrixStack matrix, int mouseX, int mouseY, HudModule module) {
        draggingFix(mouseX, mouseY);
        boolean mouseOverX = (mouseX >= this.getXPos() && mouseX <= this.getXPos() + this.getWidth());
        boolean mouseOverY = (mouseY >= this.getYPos() && mouseY <= this.getYPos() + this.getHeight());
        if(mouseOverX && mouseOverY) {
            if(GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                mouseHeld = true;
                if (!this.dragging) {
                    this.lastX = x - mouseX;
                    this.lastY = y - mouseY;
                    this.dragging = true;
                }
            }

            if(GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_PRESS && !mouseHeld) {
                mouseHeld = true;
                if(!module.hudEnabled) module.hudEnabled = true;
                else module.hudEnabled = false;
            }else if (GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_RELEASE) {
                mouseHeld = false;
            }
        }
    }

    private void draggingFix(int mouseX, int mouseY) {
        if (this.dragging) {
            this.x = mouseX + this.lastX;
            this.y = mouseY + this.lastY;
            if(GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE) this.dragging = false;
        }
    }
}