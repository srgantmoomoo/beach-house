package me.srgantmoomoo.beachhouse.gui.hud;

import org.lwjgl.glfw.GLFW;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;

public class DraggableComponent {

    private int x;
    private int y;
    private int width;
    private int height;
    private int lastX;
    private int lastY;

    private boolean dragging;

    //TODO merge with HudModule
    public DraggableComponent(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public int getxPosition() {
        return x;
    }

    public int getyPosition() {
        return y;
    }

    public void setxPosition(int x) {
        this.x = x;
    }

    public void setyPosition(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void draw(MatrixStack matrix, int mouseX, int mouseY) {
        draggingFix(mouseX, mouseY);
        boolean mouseOverX = (mouseX >= this.getxPosition() && mouseX <= this.getxPosition()+this.getWidth());
        boolean mouseOverY = (mouseY >= this.getyPosition() && mouseY <= this.getyPosition()+this.getHeight());
        if(mouseOverX && mouseOverY) {
        	if(GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) {
                if (!this.dragging) {
                    this.lastX = x - mouseX;
                    this.lastY = y - mouseY;
                    this.dragging = true;
                }
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