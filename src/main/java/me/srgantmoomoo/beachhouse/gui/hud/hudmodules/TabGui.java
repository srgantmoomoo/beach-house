package me.srgantmoomoo.beachhouse.gui.hud.hudmodules;

import com.mojang.blaze3d.systems.RenderSystem;
import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventKeyPress;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class TabGui extends HudModule {
    public static TabGui INSTANCE;

    public TabGui() {
        super("tab gui", "tabgui", "does tabb stuffs.", 2, 12, Category.BEACHHOUSE);
        INSTANCE = this;
    }

    public int currentTab;
    public boolean expanded;

    private final Identifier check = new Identifier(Main.INSTANCE.modid, "check.png");
    public void drawFinale(MatrixStack matrix) {
        TextRenderer tr = minecraft.textRenderer;

        int backgroundColor = 0x90000000;
        int tabColor = 0xff000000;
        int primaryColor = 0xffEB78DF;

        InGameHud.fill(matrix, getX(), getY(), getX() + getWidth(), getY() + getHeight(), backgroundColor);
        InGameHud.fill(matrix, getX() + 1, getY() + 1 + currentTab * 12, getX() + 2, getY() + 2 + currentTab * 12 + 11, primaryColor);

        int count = 0;
        for (Category c : Module.Category.values()) {

            String catName = c.name;
            if(c.name.equals("miscellaneous")) catName = "misc";
            if(c.name.equals("beach house")) catName = "beach";

            int catLength = 1;
            if(c.name.equals("player")) catLength = 13;
            if(c.name.equals("render")) catLength = 12;
            if(c.name.equals("combat")) catLength = 12;
            if(c.name.equals("movement")) catLength = 6;
            if(c.name.equals("miscellaneous")) catLength = 19;
            if(c.name.equals("beach house")) catLength = 14;

            tr.drawWithShadow(matrix, catName, getX() + catLength, getY() + 3 + count * 12, 0xffffffff);
            count++;
        }

        if (expanded) {
            Category category = Module.Category.values()[currentTab];
            List<Module> modules = Bedroom.INSTANCE.moduleManager.getModulesByCategory(category);

            if (modules.size() == 0)
                return;

            int settingsListX = getX() + getWidth() + 1;

            // background
            InGameHud.fill(matrix, settingsListX, getY(), getX() + getWidth() + 90, getY() + 2 + modules.size() * 12, backgroundColor);

            // selector
            tr.draw(matrix, "<", settingsListX + 90, getY() + 2 + category.moduleIndex * 12 + 1, primaryColor);

            // module names
            drawModuleName(matrix, modules, tr, settingsListX);
        }
    }

    // draw the module name (with ... if too long & enabled) and checkmark if enabled. //TODO make some sorta focused boolean or smthn so i can draw ...'s.
    private void drawModuleName(MatrixStack matrix, List<Module> modulesList, TextRenderer textRenderer, int x) {
        int count = 0;
        for (Module m : modulesList) {
            textRenderer.drawWithShadow(matrix, m.name, getX() + getWidth() + 3, getY() + 3 + count * 12, -1);
            if (m.isEnabled()) {
                RenderSystem.setShaderTexture(0, check);
                InGameHud.drawTexture(matrix,  getX() + getWidth() + 90 - 12, getY() + 1 + count * 12, 10, 10, 0, 0, 10, 10, 10, 10);
            }
            count++;
        }
        // if currenttab == something dot get indexOf(m)
    }

    // called in MixinKeyboard.
    public void onKeyPressed(Event e) {
        if(e instanceof EventKeyPress) {
            if(this.hudEnabled) {
                int code = ((EventKeyPress) e).getKey();

                Category category = Module.Category.values()[currentTab];
                List<Module> modules = Bedroom.INSTANCE.moduleManager.getModulesByCategory(category);

                if (code == GLFW.GLFW_KEY_UP) {
                    if (expanded) {
                        if (category.moduleIndex <= 0) {
                            category.moduleIndex = modules.size() - 1;
                        } else
                            category.moduleIndex--;
                    } else {
                        if (currentTab <= 0) {
                            currentTab = Module.Category.values().length - 1;
                        } else
                            currentTab--;
                    }
                }

                if (code == GLFW.GLFW_KEY_DOWN) {
                    if (expanded) {
                        if (category.moduleIndex >= modules.size() - 1) {
                            category.moduleIndex = 0;
                        } else
                            category.moduleIndex++;
                    } else {
                        if (currentTab >= Module.Category.values().length - 1) {
                            currentTab = 0;
                        } else
                            currentTab++;
                    }
                }

                if (code == GLFW.GLFW_KEY_RIGHT) {
                    if (expanded && modules.size() != 0) {
                        Module module = modules.get(category.moduleIndex);
                        if (!module.name.equals("TabGUI"))
                            module.toggle();
                    } else {
                        expanded = true;


                    }
                }

                if (code == GLFW.GLFW_KEY_LEFT) {
                    expanded = false;
                }
            }
        }
    }

    @Override
    public void draw(MatrixStack matrix) {
        drawFinale(matrix);

        super.draw(matrix);
    }

    @Override
    public void drawDraggable(MatrixStack matrix, int mouseX, int mouseY) {
        drawFinale(matrix);
        Main.INSTANCE.hudManager.drawIndicator(matrix, getX(), getY(), this.hudEnabled ? 0xff00ff00 : 0xffffffff);

        super.drawDraggable(matrix, mouseX, mouseY);
    }

    @Override
    public int getWidth() {
        return 58;
    }

    @Override
    public int getHeight() {
        return 74;
    }

}
