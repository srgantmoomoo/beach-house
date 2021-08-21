package me.srgantmoomoo.beachhouse.gui.clickgui;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

// this screen is opened in the ClickGui module.
public class ClickGuiScreen extends Screen {
    public static ArrayList<Panel> panels;
    private boolean mouseHeld = false;

    public ClickGuiScreen() {
        super(new LiteralText("smallppgui"));
        panels = new ArrayList<>();
        int panelX = 10;
        int panelY = 5;
        int panelWidth = 80;
        int panelHeight = 15;

        for (Module.Category c : Module.Category.values()) {
            ClickGuiScreen.panels.add(new Panel(c.name, panelX, panelY, panelWidth, panelHeight, c));
            panelX += 81;
        }
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrix);

        for (Panel p : panels) {
            p.updatePosition(mouseX, mouseY);
            p.drawScreen(matrix, mouseX, mouseY, delta);

            for (Component comp : p.getComponents()) {
                comp.updateComponent(mouseX, mouseY);
            }
        }

        // mouse clicked
        for (Panel p : panels) {
            if(p.isWithinHeader(mouseX, mouseY) && GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) {
                p.setDragging(true);
                p.dragX = mouseX - p.getX();
                p.dragY = mouseY - p.getY();
            }

            if(p.isWithinHeader(mouseX, mouseY) && GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_PRESS && !mouseHeld) {
                mouseHeld = true;
                p.setOpen(!p.isOpen());
            }else if(p.isWithinHeader(mouseX, mouseY) && GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_RELEASE) {
                mouseHeld = false;
            }

            if(p.isOpen() && !p.getComponents().isEmpty()) {
                for (Component component : p.getComponents()) {
                    component.mouseClicked(mouseX, mouseY);
                }
            }
        }

        // mouse released
        for (Panel p : panels) {
            p.setDragging(false);

            if (p.isOpen() && !p.getComponents().isEmpty()) {
                for (Component component : p.getComponents()) {
                    component.mouseReleased(mouseX, mouseY);
                }
            }
        }

        // key typed

    }

    /*@Override
    protected void keyTyped(char typedChar, int keyCode) {
        for (Panel panel : panels) {
            if (panel.isOpen() && !panel.getComponents().isEmpty() && keyCode != 1) {
                for (Component component : panel.getComponents()) {
                    component.keyTyped(typedChar, keyCode);
                }
            }
        }
        if (keyCode == 1) {
            this.mc.displayGuiScreen(null);
        }
    }*/

    public static ArrayList<Panel> getPanels() {
        return panels;
    }

    public static Panel getPanelByName(String name) {
        Panel panel = null;
        for (Panel p : getPanels()) {
            if (p.title.equalsIgnoreCase(name)) {
                panel = p;
            }
        }
        return panel;
    }
}