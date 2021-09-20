package me.srgantmoomoo.beachhouse.gui.commandline;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

// this screen is opened in the CommandLine module.
public class CommandLineScreen extends Screen {

    public CommandLineScreen() {
        super(new LiteralText("commandline"));
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        Reference.blur.render(1);

        int screenWidth = Reference.window.getScaledWidth();
        int screenHeight = Reference.window.getScaledHeight();

        InGameHud.fill(matrix, 10, 20, screenWidth - 10, screenHeight - 20, 0x90000000);
    }
}