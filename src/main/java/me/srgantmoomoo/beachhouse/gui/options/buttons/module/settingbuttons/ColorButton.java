package me.srgantmoomoo.beachhouse.gui.options.buttons.module.settingbuttons;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.options.buttons.module.ModuleButton;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ModeSetting;
import me.srgantmoomoo.bedroom.util.font.JColor;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class ColorButton extends Button {
    private ColorSetting op;
    private ModuleButton parent;
    private int x;
    private int y;
    private int offset;

    public ColorButton(ColorSetting op, ModuleButton parent, int offset) {
        this.op = op;
        this.parent = parent;
        this.offset = offset;
        this.x = 300 + 134;
        this.y = 100 + offset;
    }
    private boolean hovered = false;
    private boolean isTyping = false;
    public static String input = "";

    @Override
    public void drawButton(MatrixStack matrix) {
        JColor colorRGB = op.getValue();

        if(!isTyping) {
            minecraft.textRenderer.drawWithShadow(matrix, this.op.name + " " + Formatting.GRAY + colorRGB.getRed() + " " + colorRGB.getGreen() + " " + colorRGB.getBlue() + " " + colorRGB.getAlpha(), x + 9, y, 0xffffffff);
            InGameHud.fill(matrix, x, y + 1, x + 6, y + 7, colorRGB.getRGB());
        }else {
            if(input.equals(""))
                minecraft.textRenderer.drawWithShadow(matrix, Formatting.GRAY + "rrr ggg bbb aaa ...", x, y, 0xffffffff);
            else {
                if(input.length() == 15) {
                    minecraft.textRenderer.drawWithShadow(matrix, input + Formatting.GRAY + " ...", x + 9, y, 0Xff11c1e8);
                }else minecraft.textRenderer.drawWithShadow(matrix, input + Formatting.GRAY + " ...", x + 9, y, 0Xff11c1e8);
            }
        }
    }

    @Override
    public void updateButton(int mouseX, int mouseY) {
        this.y = 100 + offset;
        this.x = 300 + 134;
    }

    private boolean mouseHeld = false;
    private boolean mouseHeld2 = false;
    private boolean rainbow = false;
    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if(!parent.open)
            return;

        if(isMouseOnButton(mouseX, mouseY)) {
            hovered = true;
            if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS && !mouseHeld) {
                mouseHeld = true;
                isTyping = !isTyping;
                input = "";
            }else if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE)
                mouseHeld = false;

            if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_PRESS && !mouseHeld2) {
                rainbow = !rainbow;
                op.setValue(rainbow, op.getValue());

                mouseHeld2 = true;
            }else if(GLFW.glfwGetMouseButton(minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_RELEASE)
                mouseHeld2 = false;
        }else {
            hovered = false;
            isTyping = false;
        }
    }

    @Override
    public void keyTyped(int key) {
        if(!parent.open)
            return;

        // enter
        if(isTyping) {
            if (key == GLFW.GLFW_KEY_ENTER) {
                if(input.length() == 15) {
                    int valR = Integer.parseInt(input.substring(0, 3));
                    int valG = Integer.parseInt(input.substring(4, 7));
                    int valB = Integer.parseInt(input.substring(8, 11));
                    int valA = Integer.parseInt(input.substring(12, 15));

                    if(!(valR <= 255))
                        op.setValue(false, new JColor(255, 0, 0, 255));
                    if(!(valG <= 255))
                        op.setValue(false, new JColor(255, 0, 0, 255));
                    if(!(valB <= 255))
                        op.setValue(false, new JColor(255, 0, 0, 255));
                    if(!(valA <= 255))
                        op.setValue(false, new JColor(255, 0, 0, 255));

                    try {
                        op.setValue(false, new JColor(valR, valG, valB, valA));
                    } catch (Exception invalid) {
                        op.setValue(false, new JColor(255, 0, 0, 255));
                    }
                    input = "";
                    return;
                }
            }

            String keyPressed = "";

            if (key == GLFW.GLFW_KEY_0 || key == GLFW.GLFW_KEY_1 || key == GLFW.GLFW_KEY_2 || key == GLFW.GLFW_KEY_3 || key == GLFW.GLFW_KEY_4 || key == GLFW.GLFW_KEY_5 || key == GLFW.GLFW_KEY_6
                    || key == GLFW.GLFW_KEY_7 || key == GLFW.GLFW_KEY_8 || key == GLFW.GLFW_KEY_9 || key == GLFW.GLFW_KEY_SPACE || key == GLFW.GLFW_KEY_BACKSPACE) {
                if (GLFW.glfwGetKey(Reference.window.getHandle(), key) == GLFW.GLFW_PRESS) {
                    // space
                    if (key == GLFW.GLFW_KEY_SPACE) {
                        keyPressed = " ";
                    }
                    // backspace
                    else if (key == GLFW.GLFW_KEY_BACKSPACE) {
                        if (input.length() > 0)
                            input = input.substring(0, input.length() - 1);
                    }
                    // number keys
                    else keyPressed = GLFW.glfwGetKeyName(key, GLFW.glfwGetKeyScancode(key));
                }
            }
            input += keyPressed;
        }
    }

    public boolean isMouseOnButton(int xx, int yy) {
        return xx > x && xx < x + minecraft.textRenderer.getWidth(op.name) && yy > y  && yy < y + minecraft.textRenderer.fontHeight;
    }

}
