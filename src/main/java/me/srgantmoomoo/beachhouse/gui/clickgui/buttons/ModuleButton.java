package me.srgantmoomoo.beachhouse.gui.clickgui.buttons;

import com.mojang.blaze3d.systems.RenderSystem;
import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGuiScreen;
import me.srgantmoomoo.beachhouse.gui.clickgui.Panel;
import me.srgantmoomoo.beachhouse.gui.clickgui.buttons.subbuttons.*;
import me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.ClickGui;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.Setting;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class ModuleButton extends Button {
    private ArrayList<Button> subcomponents;
    public Module mod;
    public Panel parent;
    public int offset;
    private boolean open;
    private boolean hovered;
    int mousexx;
    int mouseyy;

    public ModuleButton(Module mod, Panel parent, int offset) {
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
        this.subcomponents = new ArrayList<>();
        this.open = false;
        int opY = offset + 12;

        if(mod.settings != null) {
            for(Setting setting : mod.settings) {
                if(setting instanceof BooleanSetting) {
                    this.subcomponents.add(new BooleanButton((BooleanSetting) setting, this, opY));
                    opY += 12;
                }
                if(setting instanceof NumberSetting) {
                    this.subcomponents.add(new NumberButton((NumberSetting) setting, this, opY));
                    opY += 12;
                }
                if(setting instanceof ModeSetting) {
                    this.subcomponents.add(new ModeButton((ModeSetting) setting, this, opY));
                    opY += 12;
                }
                /*if(setting instanceof ColorSetting) {
                    this.subcomponents.add(new ColorComponent((ColorSetting) setting, this, opY));
                    opY += 12;
                }*/
            }
        }
        this.subcomponents.add(new KeybindButton(this, opY));
    }

    // using this method to draw module names with "..." AND some other things like hovering.
    private void drawModuleName(MatrixStack matrix) {
        String newName = this.mod.getName();

        if(newName.length() > 12) {
            newName = newName.substring(0, 10) + Formatting.GRAY + " ...";
        }

        if(hovered) {
            if (ClickGui.INSTANCE.hover.isEnabled())
                minecraft.textRenderer.drawWithShadow(matrix, this.mod.getName(), parent.getX() + 2, (parent.getY() + offset + 1), 0xffffffff);
            else
                minecraft.textRenderer.drawWithShadow(matrix, this.mod.getName(), parent.getX() + 3, (parent.getY() + offset + 2), 0xffffffff);
        }else
            minecraft.textRenderer.drawWithShadow(matrix, this.mod.isEnabled() ? newName : this.mod.getName(), parent.getX() + 3, (parent.getY() + offset + 2), 0xffffffff);
    }

    private final Identifier check = new Identifier(Main.modid, "check.png");
    @Override
    public void drawButton(MatrixStack matrix) {
        // module name and background
        InGameHud.fill(matrix, parent.getX(), parent.getY() + offset, parent.getX() + parent.getWidth(), parent.getY() + 12 + offset, 0x90000000);
        drawModuleName(matrix);

        // draw check marks if module is enabled
        if(this.mod.isEnabled()) {
            RenderSystem.setShaderTexture(0, check);
            InGameHud.drawTexture(matrix,  parent.getX() + parent.getWidth() - 13, (parent.getY() + offset + 1), 10, 10, 0, 0, 10, 10, 10, 10);
        }

        if (this.open && !this.subcomponents.isEmpty()) {
            for (Button comp : this.subcomponents) {
                comp.drawButton(matrix);
            }
        }
    }

    @Override
    public void closeAllSub() {
        this.open = false;
    }

    @Override
    public void updateButton(int mouseX, int mouseY) {
        this.hovered = this.isMouseOnButton(mouseX, mouseY);

        mousexx = mouseX + 10;
        mouseyy = mouseY - 5;

        if (!this.subcomponents.isEmpty()) {
            for (Button comp : this.subcomponents) {
                comp.updateButton(mouseX, mouseY);
            }
        }
    }

    private boolean mouseHeld = false;
    private boolean mouseHeld2 = false;
    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if(ClickGuiScreen.globalFocus)
            return;

        if(isMouseOnButton(mouseX, mouseY)) {
            if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                mouseHeld = true;
                this.mod.toggle();
            }else if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE && mouseHeld)
                mouseHeld = false;

            if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_PRESS && !mouseHeld2) {
                mouseHeld2 = true;
                if(!this.isOpen()) {
                    // if i want settings to close across all panels i would use this.
                    ClickGuiScreen.panels.forEach(Panel::closeAllSettings);
                    //parent.closeAllSettings();
                    this.setOpen(true);
                }else {
                    this.setOpen(false);
                }
            }else if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_RELEASE && mouseHeld2)
                mouseHeld2 = false;
        }

        for(Button comp : this.subcomponents) {
            comp.mouseClicked(mouseX, mouseY);
        }
    }

    @Override
    public void keyTyped(int key) {
        for (Button comp : this.subcomponents) {
            comp.keyTyped(key);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        for (Button comp : this.subcomponents) {
            comp.mouseReleased(mouseX, mouseY);
        }
    }

    public boolean isMouseOnButton(int x, int y) {
        return x > parent.getX() && x < parent.getX() + 88 && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean onWall() {
        int secondWidth = minecraft.getWindow().getScaledWidth() - (parent.getX() + 90);
        return secondWidth < 89;
    }

    public int newx() {
        if(onWall() && ClickGui.INSTANCE.interactWithWall.isEnabled()) {
            return -2;
        }else {
            return 90;
        }
    }

    public int newy() {
        return -12;
    }

    public int newwidth() {
        if(onWall() && ClickGui.INSTANCE.interactWithWall.isEnabled()) {
            return -parent.getWidth();
        }else {
            return parent.getWidth();
        }
    }

    public int stringx() {
        boolean isOnWall = onWall() && ClickGui.INSTANCE.interactWithWall.isEnabled();

        return (isOnWall ? newx() + newwidth() + 2: newx() + 2);
    }
}
