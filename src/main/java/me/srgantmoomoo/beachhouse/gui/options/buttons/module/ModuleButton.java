package me.srgantmoomoo.beachhouse.gui.options.buttons.module;

import com.mojang.blaze3d.systems.RenderSystem;
import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.clickgui.ClickGuiScreen;
import me.srgantmoomoo.beachhouse.gui.clickgui.Panel;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import me.srgantmoomoo.beachhouse.gui.options.OptionsScreen;
import me.srgantmoomoo.beachhouse.gui.options.buttons.GuiButton;
import me.srgantmoomoo.beachhouse.gui.options.buttons.module.settingbuttons.BooleanButton;
import me.srgantmoomoo.beachhouse.gui.options.buttons.module.settingbuttons.ColorButton;
import me.srgantmoomoo.beachhouse.gui.options.buttons.module.settingbuttons.ModeButton;
import me.srgantmoomoo.beachhouse.gui.options.buttons.module.settingbuttons.NumberButton;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.Setting;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class ModuleButton extends Button {
    private ArrayList<Button> settingButtons;
    public Module mod;
    public boolean open;
    public int x;
    public int y;
    public int offset;

    public ModuleButton(Module mod, int offset) {
        this.x = 300 + 4;
        this.y = 100;
        this.offset = offset;
        this.open = false;
        int opY = 0;

        this.mod = mod;
        this.settingButtons = new ArrayList<>();

        if(mod.settings != null) {
            for(Setting setting : mod.settings) {
                if(setting instanceof BooleanSetting) {
                    this.settingButtons.add(new BooleanButton((BooleanSetting) setting, this, opY));
                    opY += 12;
                }
                if(setting instanceof NumberSetting) {
                    this.settingButtons.add(new NumberButton((NumberSetting) setting, this, opY));
                    opY += 12;
                }
                if(setting instanceof ModeSetting) {
                    this.settingButtons.add(new ModeButton((ModeSetting) setting, this, opY));
                    opY += 12;
                }
                if(setting instanceof ColorSetting) {
                    this.settingButtons.add(new ColorButton((ColorSetting) setting, this, opY));
                    opY += 12;
                }
            }
        }
        //this.subcomponents.add(new KeybindButton(this, opY));
    }

    private final Identifier check = new Identifier(Main.modid, "setting.png");
    @Override
    public void drawButton(MatrixStack matrix) {
        minecraft.textRenderer.drawWithShadow(matrix, mod.getName(), x, y + offset, modIsEnabled() ? 0xff11c1e8 : 0xffffffff);
        RenderSystem.setShaderTexture(0, check);
        InGameHud.drawTexture(matrix,  x + minecraft.textRenderer.getWidth(mod.getName()) + 2, (y + offset), 10, 10, 0, 0, 10, 10, 10, 10);

        if (this.open && !this.settingButtons.isEmpty()) {
            for (Button comp : this.settingButtons) {
                comp.drawButton(matrix);
            }
        }
    }

    private boolean mouseHeld = false;
    private boolean mouseHeld2 = false;
    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if(isMouseOnButton(mouseX, mouseY)) {
            // enabling
            if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                mouseHeld = true;
                if(mod instanceof HudModule)
                    ((HudModule) mod).hudEnabled = !((HudModule) mod).hudEnabled;
                else
                    mod.toggle();
            }else if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE && mouseHeld)
                mouseHeld = false;

            // opening/closing settings
            if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_PRESS && !mouseHeld2) {
                mouseHeld2 = true;
                // close all buttons.
                open = !open;
            }else if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_RELEASE && mouseHeld2)
                mouseHeld2 = false;
        }

        for(Button comp : this.settingButtons) {
            comp.mouseClicked(mouseX, mouseY);
        }
    }

    public boolean modIsEnabled() {
        if(mod instanceof HudModule)
            return ((HudModule) mod).hudEnabled;
        else
            return mod.isEnabled();
    }

    public boolean isMouseOnButton(int xx, int yy) {
        return xx > x && xx < x + minecraft.textRenderer.getWidth(mod.getName()) && yy > y + offset && yy < y + offset + minecraft.textRenderer.fontHeight;
    }

}
