package me.srgantmoomoo.beachhouse.gui.options;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.gui.options.buttons.GuiButton;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class OptionsScreen extends Screen {
    public ArrayList<Button> buttons;

    public OptionsScreen() {
        super(new LiteralText("options"));

        this.buttons = new ArrayList<>();

        GuiButton guiButton = new GuiButton();

        buttons.add(guiButton);
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        Reference.blur.render(1);

        int screenWidth = Reference.window.getScaledWidth();
        int screenHeight = Reference.window.getScaledHeight();

        fill(matrix, 300, 80, screenWidth - 300, screenHeight - 80, 0x90000000);

        if(GuiButton.selected) {
            fill(matrix, 300, 80 + 2 + 12, 300 + 2, 80 + 2 + 12 + 1, 0xff11c1e8);
            fill(matrix, 300 + 24 + 2, 80 + 2 + 12, screenWidth - 300, 80 + 2 + 12 + 1, 0xff11c1e8);
        }else
            fill(matrix, 300, 80 + 2 + 12, screenWidth - 300, 80 + 2 + 12 + 1, 0xff11c1e8);

        // call methods
        draw(matrix, mouseX, mouseY, delta);
        for(Button button : buttons) {
            button.mouseClicked(mouseX, mouseY);
        }
    }

    public void draw(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
        for(Button button : buttons) {
            button.drawButton(matrix);
        }
    }

}
