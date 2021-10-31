package me.srgantmoomoo.beachhouse.gui.options;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.navbar.NavBar;
import me.srgantmoomoo.beachhouse.gui.options.buttons.gui.GuiButton;
import me.srgantmoomoo.beachhouse.gui.options.buttons.hud.HudButton;
import me.srgantmoomoo.beachhouse.gui.options.buttons.utilities.UtilitiesButton;
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
        UtilitiesButton utilitiesButton = new UtilitiesButton();

        buttons.add(guiButton);
        buttons.add(hudButton);
        buttons.add(utilitiesButton);
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        Reference.blur.render(1);

        int screenWidth = Reference.window.getScaledWidth();
        int screenHeight = Reference.window.getScaledHeight();

        fill(matrix, 300, 92, screenWidth - 300, screenHeight - 80, 0x90000000);

        //TODO this code is disgusting, fix it.
        if(GuiButton.selected) {
            fill(matrix, 300, 80 + 12, 300 + 1, 80 + 12 + 1, 0xfff868fb);
            fill(matrix, 300 + 20 + 1, 80 + 12, screenWidth - 300, 80 + 12 + 1, 0xfff868fb);
        }else if(HudButton.selected) {
            fill(matrix, 300, 80 + 12, 300 + 1 + 20 + 1, 80 + 12 + 1, 0xfff868fb);
            fill(matrix, 300 + 1 + 20 + 1 + 23, 80 + 12, screenWidth - 300, 80 + 12 + 1, 0xfff868fb);
        }else if(UtilitiesButton.selected) {
            int xOld =  300 + 1 + 20 + 1;
            int xNew = xOld + 23 + 1; // +23 is the length of hudbutton, +1 to cover the distance between to two buttons....   really should clean up this code but im monkeying it rn.
            fill(matrix, 300, 80 + 12, xNew, 80 + 12 + 1, 0xfff868fb);
            fill(matrix, xNew + 40, 80 + 12, screenWidth - 300, 80 + 12 + 1, 0xfff868fb);
        }else
            fill(matrix, 300, 80 + 12, screenWidth - 300, 80 + 12 + 1, 0xfff868fb);

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
