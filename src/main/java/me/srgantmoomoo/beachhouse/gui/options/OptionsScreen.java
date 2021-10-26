package me.srgantmoomoo.beachhouse.gui.options;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.navbar.NavBar;
import me.srgantmoomoo.beachhouse.gui.options.buttons.GuiButton;
import me.srgantmoomoo.beachhouse.gui.options.buttons.HudButton;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class OptionsScreen extends Screen {
    public ArrayList<Button> buttons;
    public NavBar navBar = new NavBar();

    public OptionsScreen() {
        super(new LiteralText("options"));

        this.buttons = new ArrayList<>();

        GuiButton guiButton = new GuiButton();
        HudButton hudButton = new HudButton();

        buttons.add(guiButton);
        buttons.add(hudButton);
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        Reference.blur.render(1);

        int screenWidth = Reference.window.getScaledWidth();
        int screenHeight = Reference.window.getScaledHeight();

        fill(matrix, 300, 80, screenWidth - 300, screenHeight - 80, 0x90000000);

        if(GuiButton.selected) {
            fill(matrix, 300, 80 + 2 + 12, 300 + 2, 80 + 2 + 12 + 1, 0xfff868fB);
            fill(matrix, 300 + 22, 80 + 2 + 12, screenWidth - 300, 80 + 2 + 12 + 1, 0xfff868fB);
        }else if(HudButton.selected) {
            fill(matrix, 300, 80 + 2 + 12, 300 + 24, 80 + 2 + 12 + 1, 0xfff868fB);
            fill(matrix, 300 + 24 + 23, 80 + 2 + 12, screenWidth - 300, 80 + 2 + 12 + 1, 0xfff868fB);
        }else
            fill(matrix, 300, 80 + 2 + 12, screenWidth - 300, 80 + 2 + 12 + 1, 0xfff868fB);

        // call methods for drawing and clicking
        for(Button button : buttons) {
            button.drawButton(matrix);
        }

        for(Button button : buttons) {
            button.mouseClicked(mouseX, mouseY);
        }

        // NAVBAR
        navBar.draw(matrix, mouseX, mouseY, delta);
        navBar.mouseClicked(mouseX, mouseY);
    }

}
