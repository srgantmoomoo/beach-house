package me.srgantmoomoo.beachhouse.gui.clickgui.buttons.subbuttons;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.ClickGui;
import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.clickgui.buttons.ModuleButton;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.util.font.JColor;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class ColorButton extends Button {
    private ColorSetting op;
    private ModuleButton parent;
    private int offset;
    private int x;
    private int y;

    public ColorButton(ColorSetting op, ModuleButton parent, int offset) {
        this.op = op;
        this.parent = parent;
        this.x = parent.parent.getX() + parent.parent.getWidth();
        this.y = parent.parent.getY() + parent.offset;
        this.offset = offset;
    }
    private boolean hovered = false;
    private boolean isTyping = false;
    public static String input = "";

    @Override
    public void drawButton(MatrixStack matrix) {
        InGameHud.fill(matrix, parent.parent.getX() + parent.newx(), parent.parent.getY() + parent.newy() + offset, parent.parent.getX() + parent.newx() + parent.newwidth(), parent.parent.getY() + offset, 0x90000000);
        JColor colorRGB = op.getValue();

        if(!isTyping) {
            if(!hovered) {
                minecraft.textRenderer.drawWithShadow(matrix, this.op.name, parent.parent.getX() + parent.stringx(), parent.parent.getY() + offset - 10, -1);
                InGameHud.fill(matrix, parent.parent.getX() + parent.newx() + parent.newwidth() - 10, parent.parent.getY() + offset - 9, parent.parent.getX() + parent.newx() + parent.newwidth() - 4, parent.parent.getY() + offset - 3, colorRGB.getRGB());
            }else
                minecraft.textRenderer.drawWithShadow(matrix, "" + Formatting.GRAY + colorRGB.getRed() + " " + colorRGB.getGreen() + " " + colorRGB.getBlue() + " " + colorRGB.getAlpha(), parent.parent.getX() + parent.stringx(), (parent.parent.getY() + offset - 10), -1);
        }else {
            if(input.equals(""))
                minecraft.textRenderer.drawWithShadow(matrix, input + Formatting.GRAY + "rrr ggg bbb aaa ...", parent.parent.getX() + parent.stringx(), (parent.parent.getY() + offset - 10), 0Xff11c1e8);
            else
                minecraft.textRenderer.drawWithShadow(matrix, input + Formatting.GRAY + " ...", parent.parent.getX() + parent.stringx(), (parent.parent.getY() + offset - 10), 0Xff11c1e8);
        }
    }

    @Override
    public void updateButton(int mouseX, int mouseY) {
        this.y = parent.parent.getY() + parent.newy() + this.offset;
        this.x = parent.parent.getX() + parent.newx();
    }

    private boolean mouseHeld = false;
    private boolean mouseHeld2 = false;
    private boolean rainbow = false;
    @Override
    public void mouseClicked(int mouseX, int mouseY) {
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

    //TODO enter value when max is typed
    // split by space // or just substring using format 000
    // set to 0 if less than 0
    // set to 255 if greater than 255

    // help me
    @Override
    public void keyTyped(int key) {
        if(isTyping) {
            if (key == GLFW.GLFW_KEY_ENTER && !input.equals("")) {
                int valR = Integer.parseInt(input.substring(0, 3));
                int valG = Integer.parseInt(input.substring(4, 7));
                int valB = Integer.parseInt(input.substring(8, 11));
                int valA = Integer.parseInt(input.substring(12, 15));

                try {
                    op.setValue(false, new JColor(valR, valG, valB, valA));
                }catch (Exception invalid) {
                    op.setValue(false, new JColor(255, 0, 0, 255));
                }
                input = "";
                return;
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

    public boolean isMouseOnButton(int x, int y) {
        if (parent.onWall() && ClickGui.INSTANCE.interactWithWall.isEnabled()) {
            return x < this.x && x > this.x + parent.newwidth() && y > this.y && y < this.y + 12;
        } else {
            return x > this.x && x < this.x + parent.newwidth() && y > this.y && y < this.y + 12;
        }
    }
}
