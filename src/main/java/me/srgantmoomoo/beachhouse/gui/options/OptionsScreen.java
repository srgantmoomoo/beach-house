package me.srgantmoomoo.beachhouse.gui.options;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class OptionsScreen extends Screen {

    public OptionsScreen() {
        super(new LiteralText("options"));
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        Reference.blur.render(1);

        int screenWidth = Reference.window.getScaledWidth();
        int screenHeight = Reference.window.getScaledHeight();

        InGameHud.fill(matrix, 70, 60, screenWidth - 70, screenHeight - 60, 0x90000000);
    }

}
