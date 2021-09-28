package me.srgantmoomoo.beachhouse.gui.commandline;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// this screen is opened in the CommandLine module.
public class CommandLineScreen extends Screen {

    public CommandLineScreen() {
        super(new LiteralText("commandline"));
    }

    public static String input = "";
    public static List<String> outputs = new ArrayList<>();

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float delta) {
        Reference.art.render(1);

        int screenWidth = Reference.window.getScaledWidth();
        int screenHeight = Reference.window.getScaledHeight();

        InGameHud.fill(matrix, 10, 20, screenWidth - 10, screenHeight - 20, 0x90000000);

        Collections.reverse(outputs);
        int outputY = 0;
        for (String output : outputs) {
            outputY++;
            if(!(outputY >= 41)) {
                Reference.textRenderer.drawWithShadow(matrix, output, 12, screenHeight - 30 - (12 * outputY), 0xffffffff);
            }
        }
        Collections.reverse(outputs);

        Reference.textRenderer.drawWithShadow(matrix, inputLine(), 12, screenHeight - 30, 0xffffffff);
    }

    // called in MixinKeyboard
    public void onKeyPressed(int key) {
        if(Reference.minecraft.currentScreen instanceof CommandLineScreen) {
            if(key == GLFW.GLFW_KEY_ENTER) {
                resetInputLine();
                return;
            }

            String keyPressed = "";

            if (key != GLFW.GLFW_KEY_ESCAPE && key != GLFW.GLFW_KEY_LEFT_SHIFT && key != GLFW.GLFW_KEY_LEFT_ALT
                    && key != GLFW.GLFW_KEY_LEFT_CONTROL && key != GLFW.GLFW_KEY_LEFT_SUPER) {
                if (GLFW.glfwGetKey(Reference.window.getHandle(), key) == GLFW.GLFW_PRESS) {
                    // space
                    if (key == GLFW.GLFW_KEY_SPACE) {
                        keyPressed = " ";
                    }
                    // backspace
                    else if (key == GLFW.GLFW_KEY_BACKSPACE) {
                        if (input.length() > 0)
                            input = input.substring(0, input.length() - 1);
                    }
                    // normal keys
                    else keyPressed = GLFW.glfwGetKeyName(key, GLFW.glfwGetKeyScancode(key));
                }
            }
            input += keyPressed;
        }
    }

    public String inputLine() {
        if(input.isEmpty())
            return Formatting.GRAY + "ily tommy";

        if (Reference.textRenderer.getWidth(Formatting.WHITE + input) < this.width) return input;
        else resetInputLine();

        return "";
    }

    private void executeCommand(String input) {
        if (input.split(" ").length > 0) {
            boolean commandFound = false;
            String commandName = input.split(" ")[0];
            for (Command c : Bedroom.commandManager.commands) {
                if (c.aliases.contains(commandName) || c.name.equalsIgnoreCase(commandName)) {
                    c.onCommand(Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length), input);
                    commandFound = true;
                    break;
                }
            }
            if (!commandFound) {
                //outputs.add(Formatting.RED + "no russian");
            }
        }
    }

    public void resetInputLine() {
        executeCommand(input);
        input = "";
    }

}