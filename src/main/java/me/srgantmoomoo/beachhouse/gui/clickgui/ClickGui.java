package me.srgantmoomoo.beachhouse.gui.clickgui;

import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class ClickGui extends Screen {
    public static ArrayList<Panel> panels;

    public ClickGui() {
        super(new LiteralText("smallppgui"));
        panels = new ArrayList<>();
        int panelX = 10;
        int panelY = 5;
        int panelWidth = 80;
        int panelHeight = 15;

        for (Module.Category c : Module.Category.values()) {
            ClickGui.panels.add(new Panel(c.name, panelX, panelY, panelWidth, panelHeight, c));
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
            if (p.isWithinHeader(mouseX, mouseY) && mouseButton == 0) {
                p.setDragging(true);
                p.dragX = mouseX - p.getX();
                p.dragY = mouseY - p.getY();
            }

            if (p.isWithinHeader(mouseX, mouseY) && mouseButton == 1) {
                p.setOpen(!p.isOpen());
            }

            if (p.isOpen() && !p.getComponents().isEmpty()) {
                for (Component component : p.getComponents()) {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }

        // mouse released

        // key typed

    }

    @Override
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
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        for (Panel p : panels) {
            p.setDragging(false);

            if (p.isOpen() && !p.getComponents().isEmpty()) {
                for (Component component : p.getComponents()) {
                    component.mouseReleased(mouseX, mouseY, state);
                }
            }
        }
    }

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