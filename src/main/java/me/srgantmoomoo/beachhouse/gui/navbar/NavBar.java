package me.srgantmoomoo.beachhouse.gui.navbar;

import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.navbar.buttons.ClickGuiButton;
import me.srgantmoomoo.beachhouse.gui.navbar.buttons.CommandLineButton;
import me.srgantmoomoo.beachhouse.gui.navbar.buttons.HudEditorButton;
import me.srgantmoomoo.beachhouse.gui.navbar.buttons.OptionsButton;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;

public class NavBar {
    public ArrayList<Button> buttons;
    public static NavBar INSTANCE;

    public NavBar() {
        this.buttons = new ArrayList<>();

        HudEditorButton hudEditorButton = new HudEditorButton();
        ClickGuiButton clickGuiButton = new ClickGuiButton();
        CommandLineButton commandLineButton = new CommandLineButton();
        OptionsButton options = new OptionsButton();

        buttons.add(hudEditorButton);
        buttons.add(clickGuiButton);
        buttons.add(commandLineButton);
        buttons.add(options);

        INSTANCE = this;
    }

    public void draw(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
        for(Button button : buttons) {
            button.drawButton(matrix);
        }
    }

    public void mouseClicked(int mouseX, int mouseY) {
        for(Button button : buttons) {
            button.mouseClicked(mouseX, mouseY);
        }
    }

}
