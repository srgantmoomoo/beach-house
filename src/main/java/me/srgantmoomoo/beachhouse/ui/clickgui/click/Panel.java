package me.srgantmoomoo.beachhouse.ui.clickgui.click;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.input.Mouse;

import com.sun.jna.platform.win32.Guid.GUID;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.ui.clickgui.Component;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.Module.Category;
import me.srgantmoomoo.bedroom.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
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
    public Category cat;
    public int tY;

    public Panel(String title, int x, int y, int width, int height, Category cat) {
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
        this.tY = this.height;

        for (Module mod : ModuleManager.getModules()) {
            if (mod.getCategory() == cat) {
                ModuleButton modButton = new ModuleButton(mod, this, tY);
                this.components.add(modButton);
                tY += 15;
            }
        }
        this.refresh();
    }

    public void drawScreen(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
    	TextRenderer tr = mc.textRenderer;
    	DrawableHelper.fill(matrix, x - 1, y - 1, x + width + 1, y + height + 1, 0xffff0000);
        DrawableHelper.fill(matrix, x, y, x + width, y + height, 0x75101010);

        tr.drawWithShadow(matrix, title, x + 2 + width / 2 - tr.getWidth(title) / 2, y + height / 2 - tr.fontHeight / 2, -1);
        

        if (this.open && !this.components.isEmpty()) {
            for (Component component : components) {
                component.renderComponent();
            }
        }
    }

    public void refresh() {
        int off = this.height;
        for (Component comp : components) {
            comp.setOff(off);
            off += comp.getHeight();
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
        scroll();
    }

    public void scroll() {
        int scrollWheel = Mouse.getDWheel();	
        
        for (Panel panels : Past.clickGUITwo.panels) {
            if (scrollWheel < 0) {
                panels.setY((int) (panels.getY() - Past.settingsManager.getSettingID("ClickGUIScrollSpeed").getValueDouble()));
                continue;
            }
            if (scrollWheel <= 0) continue;
            panels.setY((int) (panels.getY() + Past.settingsManager.getSettingID("ClickGUIScrollSpeed").getValueDouble()));
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

    public int getHeight() {
        return height;
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

    public Category getCategory() {
        return cat;
    }
}
