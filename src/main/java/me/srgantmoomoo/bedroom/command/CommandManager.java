package me.srgantmoomoo.bedroom.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.beachhouse.feature.module.modules.beachhouse.CommandLine;
import me.srgantmoomoo.beachhouse.gui.commandline.CommandLineScreen;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.util.font.TextFormatting;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

/**
 * @author SrgantMooMoo
 * @since 5/16/2021
 */

public class CommandManager {

    public List<Command> commands;
    public String prefix = ",";

    public CommandManager() {
        commands = new ArrayList<Command>();
    }

    public void callCommandReturn(String input) {
        String message = input;

        if(!message.startsWith(prefix))
            return;

        message = message.substring(prefix.length());
        if(message.split(" ").length > 0) {
            boolean commandFound = false;
            String commandName = message.split(" ")[0];
            for(Command c : Bedroom.commandManager.commands) {
                if(c.aliases.contains(commandName) || c.name.equalsIgnoreCase(commandName)) {
                    c.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length), message);
                    commandFound = true;
                    break;
                }
            }
            if(!commandFound) {
                addChatMessage(TextFormatting.DARK_RED + "command does not exist, use " + TextFormatting.ITALIC + prefix + "help " + TextFormatting.RESET + "" + TextFormatting.DARK_RED + "for help.");
            }
        }
    }

    // opens chat when prefix is clicked (called in MixinKeyboard).
    public void openChatScreen() {
        if(InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), prefix.charAt(0)))
            if (prefix.length() == 1) {
                MinecraftClient.getInstance().openScreen(new ChatScreen(""));
            }
    }

    public void setCommandPrefix(String pre) {
        this.prefix = pre;

        if (Main.save != null) {
            try {
                Main.save.savePrefix();
            } catch (Exception e) {}
        }
    }

    public Command getCommand(String name) {
        for (Command c : this.commands) {
            if(c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * send a client side chat message without a prefix to the minecraft chat.
     * @param message
     */
    public void addCustomChatMessage(String message) {
        if(CommandLine.INSTANCE.isInCommandLine)
            CommandLineScreen.outputs.add(message);
        else
            Reference.minecraft.inGameHud.getChatHud().addMessage(new LiteralText(message));
    }

    /**
     * send a client side chat message with a prefix to the minecraft chat.
     * @param message
     */
    @SuppressWarnings("resource")
    public void addChatMessage(String message) {
        String messageWithPre = TextFormatting.AQUA + "@" + TextFormatting.ITALIC + Bedroom.modname + TextFormatting.GRAY + ": " + message;
        Text textComponentString = new LiteralText(messageWithPre);

        if(CommandLine.INSTANCE.isInCommandLine)
            CommandLineScreen.outputs.add(messageWithPre);
        else
           Reference.minecraft.inGameHud.getChatHud().addMessage(textComponentString);
    }

    /**
     * send a client side message to the minecraft chat telling the user how to correctly use a certain command.
     * @param name
     * @param syntax
     */
    @SuppressWarnings("resource")
    public void correctUsageMsg(String name, String syntax) {
        String usage = TextFormatting.RED + "correct usage of " + name + " command -> " + TextFormatting.GRAY + prefix + syntax;
        String message = TextFormatting.AQUA + "@" + TextFormatting.ITALIC + Bedroom.modname + TextFormatting.GRAY + ": " + usage;
        Text textComponentString = new LiteralText(message);

        if(CommandLine.INSTANCE.isInCommandLine)
            CommandLineScreen.outputs.add(message);
        else
            Reference.minecraft.inGameHud.getChatHud().addMessage(textComponentString);
    }

}