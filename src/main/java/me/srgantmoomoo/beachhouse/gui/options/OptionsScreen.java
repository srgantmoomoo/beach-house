package me.srgantmoomoo.beachhouse.gui.options;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.Options;
import me.srgantmoomoo.beachhouse.gui.Button;
import me.srgantmoomoo.beachhouse.gui.clickgui.Panel;
import me.srgantmoomoo.beachhouse.gui.navbar.NavBar;
import me.srgantmoomoo.beachhouse.gui.options.buttons.GuiButton;
import me.srgantmoomoo.beachhouse.gui.options.buttons.HudButton;
import me.srgantmoomoo.beachhouse.gui.options.buttons.UtilitiesButton;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class OptionsScreen extends Screen {
    public ArrayList<Button> panels;
    public NavBar navBar = new NavBar();

    public OptionsScreen() {
        super(new LiteralText("options"));

        this.panels = new ArrayList<>();

        GuiButton guiButton = new GuiButton();
        HudButton hudButton = new HudButton();
        UtilitiesButton utilitiesButton = new UtilitiesButton();

        panels.add(guiButton);
        panels.add(hudButton);
        panels.add(utilitiesButton);
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        if(Options.INSTANCE.background.is("beach")) {
            Reference.art.render(1);
            Reference.blur.render(1);
        }

        if(Options.INSTANCE.background.is("art"))
            Reference.art.render(1);

        if(Options.INSTANCE.background.is("blur"))
            Reference.blur.render(1);

        if(Options.INSTANCE.background.is("dim"))
            this.renderBackground(matrix);

        int screenWidth = Reference.window.getScaledWidth();
        int screenHeight = Reference.window.getScaledHeight();

        fill(matrix, 300, 92, screenWidth - 300, screenHeight - 80, 0x90000000);
        fill(matrix, screenWidth - 300 - 230, 92, screenWidth - 300, screenHeight - 80, 0x80000000);
        //fill(matrix, 300, 92, screenWidth - 300 - 220, screenHeight - 80, 0x90000000);

        //TODO this code is disgusting, fix it.
        if(GuiButton.selected) {
            fill(matrix, 300, 80 + 12, 300 + 1, 80 + 12 + 1, 0xffe6ab17);
            fill(matrix, 300 + 20 + 1, 80 + 12, screenWidth - 300, 80 + 12 + 1, 0xffe6ab17);
        }else if(HudButton.selected) {
            fill(matrix, 300, 80 + 12, 300 + 1 + 20 + 1, 80 + 12 + 1, 0xffe6ab17);
            fill(matrix, 300 + 1 + 20 + 1 + 23, 80 + 12, screenWidth - 300, 80 + 12 + 1, 0xffe6ab17);
        }else if(UtilitiesButton.selected) {
            int xOld =  300 + 1 + 20 + 1;
            int xNew = xOld + 23 + 1; // +23 is the length of hudbutton, +1 to cover the distance between to two buttons....   really should clean up this code but im monkeying it rn.
            fill(matrix, 300, 80 + 12, xNew, 80 + 12 + 1, 0xffe6ab17);
            fill(matrix, xNew + 40, 80 + 12, screenWidth - 300, 80 + 12 + 1, 0xffe6ab17);
        }else
            fill(matrix, 300, 80 + 12, screenWidth - 300, 80 + 12 + 1, 0xffe6ab17);

        // call methods for drawing and clicking
        for(Button button : panels) {
            button.drawButton(matrix);
        }

        for(Button button : panels) {
            button.mouseClicked(mouseX, mouseY);
        }

        // NAVBAR
        navBar.draw(matrix, mouseX, mouseY, delta);
        navBar.mouseClicked(mouseX, mouseY);
    }

    // called in MixinKeyboard
    public void onKeyPressed(int key) {
        for (Button button : panels) {
            button.keyTyped(key);
        }
    }

}
