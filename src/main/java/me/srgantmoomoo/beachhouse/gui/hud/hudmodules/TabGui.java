package me.srgantmoomoo.beachhouse.gui.hud.hudmodules;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.util.Timer;
import me.srgantmoomoo.beachhouse.gui.hud.HudModule;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.api.event.events.EventKeyPress;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
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
    public boolean Tab;

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

        // for expanded, i often use getX() + getWidth() and than plus an extra 1 to whatever number i would originally use for the x value, this gets the true x value of the expanded list...
        // ... since getX() would just return the x value of the normal tab.
        if (expanded) {
            Category category = Module.Category.values()[currentTab];
            List<Module> modules = Bedroom.moduleManager.getModulesByCategory(category);

            if (modules.size() == 0)
                return;

            InGameHud.fill(matrix, getX() + getWidth() + 1, getY(), getX() + getWidth() + 70, getY() + 2 + modules.size() * 12, backgroundColor);
            tr.draw(matrix, "-", getX() + getWidth() + 71, getY() + 2 + category.moduleIndex * 12 + 1, primaryColor);

            count = 0;
            for (Module m : modules) {
                tr.drawWithShadow(matrix, m.name, getX() + getWidth() + 3, getY() + 3 + count * 12, -1);
                if (m.isEnabled()) {
                    InGameHud.fill(matrix, getX() + getWidth() + 67, getY() + 2 + count * 12, getX() + getWidth() + 68, getY() + 11 + count * 12, 0xffffffff);
                }
                count++;
            }
        }
    }

    // called in MixinKeyboard.
    public void onKeyPressed(Event e) {
        if(e instanceof EventKeyPress) {
            if(this.hudEnabled) {
                int code = ((EventKeyPress) e).getKey();

                Category category = Module.Category.values()[currentTab];
                List<Module> modules = Bedroom.moduleManager.getModulesByCategory(category);

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
        Main.hudManager.drawIndicator(matrix, getX(), getY(), this.hudEnabled ? 0xff00ff00 : 0xffffffff);

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
