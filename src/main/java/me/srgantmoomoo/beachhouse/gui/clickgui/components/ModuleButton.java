package me.srgantmoomoo.beachhouse.gui.clickgui.components;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.clickgui.Component;
import me.srgantmoomoo.beachhouse.gui.clickgui.Panel;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.Setting;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class ModuleButton extends Component {
    private ArrayList<Component> subcomponents;
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


        if (mod.settings != null) {
            for (Setting setting : mod.settings) {
                if (setting instanceof BooleanSetting) {
                    this.subcomponents.add(new BooleanComponent((BooleanSetting) setting, this, opY));
                    opY += 12;
                }
                if (setting instanceof NumberSetting) {
                    this.subcomponents.add(new NumberComponent((NumberSetting) setting, this, opY));
                    opY += 12;
                }
                /*if (setting instanceof ModeSetting) {
                    this.subcomponents.add(new ModeComponent(setting, this, opY));
                    opY += 12;
                }*/
            }
        }
        this.subcomponents.add(new KeybindComponent(this, opY));
    }

    @Override
    public void renderComponent(MatrixStack matrix) {

        if(this.mod.isEnabled()) {
            InGameHud.fill(matrix, parent.getX(), parent.getY() + offset, parent.getX() + parent.getWidth(), parent.getY() + 12 + offset, 0x90000000);
            InGameHud.fill(matrix, parent.getX(), parent.getY() + offset, parent.getX() + parent.getWidth(), parent.getY() + 12 + offset, 0xfff868fB);
        } else {
            InGameHud.fill(matrix, parent.getX(), parent.getY() + offset, parent.getX() + parent.getWidth(), parent.getY() + 12 + offset, 0x90000000);
        }

        Reference.textRenderer.drawWithShadow(matrix, this.mod.getName(), parent.getX() + 3, (parent.getY() + offset + 2), -1);

        /*if (this.subcomponents.size() > 0) {
            if (!this.isOpen()) {
                Reference.textRenderer.drawWithShadow(matrix, "+", parent.getX() + parent.getWidth() - 10, (parent.getY() + offset + 2), -1);
            } else if (this.isOpen()) {
                Reference.textRenderer.drawWithShadow(matrix, "-", parent.getX() + parent.getWidth() - 10, (parent.getY() + offset + 2), -1);
            }
        }*/

        if (this.open && !this.subcomponents.isEmpty()) {
            for (Component comp : this.subcomponents) {
                comp.renderComponent(matrix);
            }
        }

        /*if (Past.settingsManager.getSettingID("OldClickGUIDescriptions").getValBoolean() && hovered == true) {
            if (Past.settingsManager.getSettingID("OldClickGUIRainbow").getValBoolean()) {
                if (Past.settingsManager.getSettingID("FontFont").getValueString() == "Lato") {
                    Gui.drawRect(mousexx - 2, mouseyy - 2, mousexx + Past.latoFont.getStringWidth(mod.getDescription()) + 2, mouseyy + FontUtil.getFontHeight() + 2, RainbowUtil.getMultiColour().getRGB());
                } else if (Past.settingsManager.getSettingID("FontFont").getValueString() == "Verdana") {
                    Gui.drawRect(mousexx - 2, mouseyy - 2, mousexx + Past.verdanaFont.getStringWidth(mod.getDescription()) + 2, mouseyy + FontUtil.getFontHeight() + 2, RainbowUtil.getMultiColour().getRGB());
                } else if (Past.settingsManager.getSettingID("FontFont").getValueString() == "Arial") {
                    Gui.drawRect(mousexx - 2, mouseyy - 2, mousexx + Past.arialFont.getStringWidth(mod.getDescription()) + 2, mouseyy + FontUtil.getFontHeight() + 2, RainbowUtil.getMultiColour().getRGB());
                } else {
                    Gui.drawRect(mousexx - 2, mouseyy - 2, mousexx + mc.fontRenderer.getStringWidth(mod.getDescription()) + 2, mouseyy + FontUtil.getFontHeight() + 2, RainbowUtil.getMultiColour().getRGB());
                }
            } else {
                if (Past.settingsManager.getSettingID("FontFont").getValueString() == "Lato") {
                    Gui.drawRect(mousexx - 2, mouseyy - 2, mousexx + Past.latoFont.getStringWidth(mod.getDescription()) + 2, mouseyy + FontUtil.getFontHeight() + 2, 0xFF222222);
                } else if (Past.settingsManager.getSettingID("FontFont").getValueString() == "Verdana") {
                    Gui.drawRect(mousexx - 2, mouseyy - 2, mousexx + Past.verdanaFont.getStringWidth(mod.getDescription()) + 2, mouseyy + FontUtil.getFontHeight() + 2, 0xFF222222);
                } else if (Past.settingsManager.getSettingID("FontFont").getValueString() == "Arial") {
                    Gui.drawRect(mousexx - 2, mouseyy - 2, mousexx + Past.arialFont.getStringWidth(mod.getDescription()) + 2, mouseyy + FontUtil.getFontHeight() + 2, 0xFF222222);
                } else {
                    Gui.drawRect(mousexx - 2, mouseyy - 2, mousexx + mc.fontRenderer.getStringWidth(mod.getDescription()) + 2, mouseyy + FontUtil.getFontHeight() + 2, 0xFF222222);
                }
            }

            FontUtil.drawText(mod.getDescription(), mousexx, mouseyy, -1);
        }*/
    }

    @Override
    public void closeAllSub() {
        this.open = false;
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.hovered = this.isMouseOnButton(mouseX, mouseY);

        mousexx = mouseX + 10;
        mouseyy = mouseY - 5;

        if (!this.subcomponents.isEmpty()) {
            for (Component comp : this.subcomponents) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
    }

    private boolean mouseHeld = false;
    private boolean mouseHeld2 = false;
    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if(isMouseOnButton(mouseX, mouseY)) {
            //InGameHud.fill(matrix, parent.getX(), parent.getY() + 1 + offset, parent.getX() + 1, parent.getY() + 11 + offset, 0xfff868fB);
            //System.out.println("absolutesmall");
            if (GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                mouseHeld = true;
                this.mod.toggle();
            } else if (GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE && mouseHeld)
                mouseHeld = false;

            if (GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_PRESS && !mouseHeld2) {
                mouseHeld2 = true;
                if (!this.isOpen()) {
                    parent.closeAllSetting();
                    this.setOpen(true);
                } else {
                    this.setOpen(false);
                }
            } else if (GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_RELEASE && mouseHeld2)
                mouseHeld2 = false;
        }
        for (Component comp : this.subcomponents) {
            comp.mouseClicked(mouseX, mouseY);
        }
    }

    @Override
    public void keyTyped(int key) {
        for (Component comp : this.subcomponents) {
            comp.keyTyped(key);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        for (Component comp : this.subcomponents) {
            comp.mouseReleased(mouseX, mouseY);
        }
    }

    public boolean isMouseOnButton(int x, int y) {
        if (x > parent.getX() && x < parent.getX() + 88 && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
