package me.srgantmoomoo.beachhouse.gui.clickgui;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.clickgui.components.ModuleButton;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;

public class Panel {
    protected MinecraftClient mc = MinecraftClient.getInstance();

    public ArrayList<Component> components;
    public String title;
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean isSettingOpen;
    private boolean isDragging;
    private boolean open;
    public int dragX;
    public int dragY;
    public Module.Category cat;

    public Panel(String title, int x, int y, int width, int height, Module.Category cat) {
        this.components = new ArrayList<>();
        this.title = title;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dragX = 0;
        this.isSettingOpen = true;
        this.isDragging = false;
        this.open = true;
        this.cat = cat;
        int tY = this.height;

        for (Module mod : Bedroom.moduleManager.getModules()) {
            if (mod.getCategory() == cat) {
                ModuleButton modButton = new ModuleButton(mod, this, tY);
                this.components.add(modButton);
                tY += 12;
            }
        }
    }

    public void drawScreen(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
        InGameHud.fill(matrix, x, y, x + width, y + height, 0xffe6ab17);

        Reference.textRenderer.drawWithShadow(matrix, title, x + 2, y + height / 2 - Reference.textRenderer.fontHeight / 2, -1);

        if (this.open && !this.components.isEmpty()) {
            for (Component component : components) {
                component.renderComponent(matrix);
            }
        }
    }

    public boolean isWithinHeader(int x, int y) {
        if (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height) {
            return true;
        } else {
            return false;
        }
    }

    public void updatePosition(int mouseX, int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - dragX);
            this.setY(mouseY - dragY);
        }
    }

    public void closeAllSetting() {
        for (Component component : components) {
            component.closeAllSub();
        }
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public int getWidth() {
        return width;
    }

    public void setDragging(boolean drag) {
        this.isDragging = drag;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public Module.Category getCategory() {
        return cat;
    }
}