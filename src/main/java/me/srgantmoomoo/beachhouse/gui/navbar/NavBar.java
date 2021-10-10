package me.srgantmoomoo.beachhouse.gui.navbar;

import me.srgantmoomoo.beachhouse.gui.navbar.buttons.ClickGuiButton;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;

public class NavBar {
    public ArrayList<Button> buttons;
    public static NavBar INSTANCE;

    public NavBar() {
        this.buttons = new ArrayList<>();

        ClickGuiButton clickGuiButton = new ClickGuiButton();
        buttons.add(clickGuiButton);

        INSTANCE = this;
    }

    public void draw(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
        for(Button button : buttons) {
            button.drawButton(matrix);
        }
    }

}
