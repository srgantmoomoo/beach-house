package me.srgantmoomoo.beachhouse.gui.clickgui;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.ClickGui;
import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.navbar.NavBar;
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

    public NavBar navBar = new NavBar();
    public static boolean globalFocus = false;

    public ClickGuiScreen() {
        super(new LiteralText("clickgui"));
        panels = new ArrayList<>();
        int panelX = 10;
        int panelY = 15;
        int panelWidth = 88;
        int panelHeight = 12;

        for (Module.Category c : Module.Category.values()) {
            if(c != Module.Category.BEACHHOUSE)
                panels.add(new Panel(c.name, panelX, panelY, panelWidth, panelHeight, c));
            panelX += 89;
        }
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        if(ClickGui.INSTANCE.background.is("beach")) {
            Reference.art.render(1);
            Reference.blur.render(1);
        }

        if(ClickGui.INSTANCE.background.is("blur"))
            Reference.blur.render(1);

        if(ClickGui.INSTANCE.background.is("art"))
            Reference.art.render(1);

        if(ClickGui.INSTANCE.background.is("dim"))
            this.renderBackground(matrix);

        for(Panel p : panels) {
            p.updatePosition(mouseX, mouseY);
            p.drawScreen(matrix, mouseX, mouseY, delta);

            for(Button comp : p.getComponents()) {
                comp.updateButton(mouseX, mouseY);
            }
        }

        // MOUSE
        // mouse clicked
        for(Panel p : panels) {
            if(p.isWithinHeader(mouseX, mouseY)) {
                if (GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) {
                    //if(!globalFocus)
                        //p.setFocused();

                    //if(p.focused) {
                        p.setDragging(true);
                        p.dragX = mouseX - p.getX();
                        p.dragY = mouseY - p.getY();
                    //}
                }
                if (GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE) {
                    if(p.focused) {
                        p.focused = false;
                        globalFocus = false;
                    }
                }
            }

            if(p.isWithinHeader(mouseX, mouseY) && GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_PRESS && !mouseHeld) {
                mouseHeld = true;
                p.setOpen(!p.isOpen());
            }else if (p.isWithinHeader(mouseX, mouseY) && GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_RELEASE) {
                mouseHeld = false;
            }

            if(p.isOpen() && !p.getComponents().isEmpty()) {
                for (Button button : p.getComponents()) {
                    button.mouseClicked(mouseX, mouseY);
                }
            }
        }

        // mouse released
        for(Panel p : panels) {
            if(p.isWithinHeader(mouseX, mouseY) && GLFW.glfwGetMouseButton(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_RELEASE) {
                p.setDragging(false);
            }

            if(p.isOpen() && !p.getComponents().isEmpty()) {
                for(Button button : p.getComponents()) {
                    button.mouseReleased(mouseX, mouseY);
                }
            }
        }

        // NAVBAR
        navBar.draw(matrix, mouseX, mouseY, delta);
        navBar.mouseClicked(mouseX, mouseY);
    }

    // called in MixinKeyboard
    public void onKeyPressed(int key) {
        for (Panel panel : panels) {
            if (panel.isOpen() && !panel.getComponents().isEmpty() && GLFW.glfwGetKey(Reference.minecraft.getWindow().getHandle(), GLFW.GLFW_KEY_ESCAPE) != GLFW.GLFW_PRESS) {
                for (Button button : panel.getComponents()) {
                    button.keyTyped(key);
                }
            }
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public ArrayList<Panel> getPanels() {
        return panels;
    }

    public Panel getPanelByName(String name) {
        Panel panel = null;
        for (Panel p : getPanels()) {
            if (p.title.equalsIgnoreCase(name)) {
                panel = p;
            }
        }
        return panel;
    }
}