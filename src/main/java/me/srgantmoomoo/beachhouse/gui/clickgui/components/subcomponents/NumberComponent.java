package me.srgantmoomoo.beachhouse.gui.clickgui.components.subcomponents;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.clickgui.Component;
import me.srgantmoomoo.beachhouse.gui.clickgui.components.ModuleComponent;
import me.srgantmoomoo.beachhouse.module.modules.beachhouse.ClickGui;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberComponent extends Component {
    private NumberSetting set;
    private ModuleComponent parent;
    private int offset;
    private int x;
    private int y;
    private boolean dragging;
    private double sliderWidth;

    public NumberComponent(NumberSetting value, ModuleComponent button, int offset) {
        this.dragging = false;
        this.set = value;
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
    }

    @Override
    public void renderComponent(MatrixStack matrix) {
        if(onWall() && ClickGui.INSTANCE.dynamicSide.isEnabled()) {
            InGameHud.fill(matrix, parent.parent.getX() - 2, parent.parent.getY() - 12 + offset, parent.parent.getX() - 92, parent.parent.getY() + offset, 0x90000000);
            InGameHud.fill(matrix, parent.parent.getX() - 92, parent.parent.getY() - 1 + offset, parent.parent.getX() - 92 + (int) sliderWidth, parent.parent.getY() + offset, 0xff11c1e8);

            Reference.textRenderer.drawWithShadow(matrix, this.set.name + " " + Formatting.GRAY + "<" + this.set.getValue() + ">", parent.parent.getX() - 90, (parent.parent.getY() + offset - 10), -1);
        }else {
            InGameHud.fill(matrix, parent.parent.getX() + 90, parent.parent.getY() - 12 + offset, parent.parent.getX() + 90 + parent.parent.getWidth(), parent.parent.getY() + offset, 0x90000000);
            InGameHud.fill(matrix, parent.parent.getX() + 90, parent.parent.getY() - 1 + offset, parent.parent.getX() + 90 + (int) sliderWidth, parent.parent.getY() + offset, 0xff11c1e8);

            Reference.textRenderer.drawWithShadow(matrix, this.set.name + " " + Formatting.GRAY + "<" + this.set.getValue() + ">", parent.parent.getX() + 92, (parent.parent.getY() + offset - 10), -1);
        }
    }

    private void renderOne(int xx, int yy) {
        if(onWall() && ClickGui.INSTANCE.dynamicSide.isEnabled()) {
            this.y = parent.parent.getY() - 12 + this.offset;
            this.x = parent.parent.getX() - 92;
            double diff = Math.min(90, Math.max(0, xx - this.x));
            double min = this.set.getMinimum();
            double max = this.set.getMaximum();
            this.sliderWidth = 90 * (this.set.getValue() - min) / (max - min);
            if (this.dragging) {
                if (diff == 0) {
                    this.set.setValue(this.set.getMinimum());
                } else {
                    double newValue = roundToPlace(diff / 90 * (max - min) + min, 2);
                    this.set.setValue(newValue);
                }
            }
        }else {
            this.y = parent.parent.getY() - 12 + this.offset;
            this.x = parent.parent.getX() + 90;
            double diff = Math.min(88, Math.max(0, xx - this.x));
            double min = this.set.getMinimum();
            double max = this.set.getMaximum();
            this.sliderWidth = 88 * (this.set.getValue() - min) / (max - min);
            if (this.dragging) {
                if (diff == 0) {
                    this.set.setValue(this.set.getMinimum());
                } else {
                    double newValue = roundToPlace(diff / 88 * (max - min) + min, 2);
                    this.set.setValue(newValue);
                }
            }
        }
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        renderOne(mouseX, mouseY);
    }

    private static double roundToPlace(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if (this.isMouseOnButton(mouseX, mouseY)) {
            if(this.parent.isOpen()) {
                if (GLFW.glfwGetMouseButton(Reference.window.getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) {
                    this.dragging = true;

                    renderOne(mouseX, mouseY);
                }
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        this.dragging = false;
    }

    public boolean onWall() {
        int secondWidth = Reference.minecraft.getWindow().getScaledWidth() - (parent.parent.getX() + 90);
        if(secondWidth < 89)
            return true;
        else
            return false;
    }

    public boolean isMouseOnButton(int x, int y) {
        if(onWall() && ClickGui.INSTANCE.dynamicSide.isEnabled()) {
            if (x < this.x && x > this.x - 92 && y > this.y && y < this.y + 12) {
                return true;
            } else {
                return false;
            }
        }else {
            if (x > this.x && x < this.x + 90 && y > this.y && y < this.y + 12) {
                return true;
            } else {
                return false;
            }
        }
    }
}
