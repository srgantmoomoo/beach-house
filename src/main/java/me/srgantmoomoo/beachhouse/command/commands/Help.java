package me.srgantmoomoo.beachhouse.command.commands;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.api.util.font.TextFormatting;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;
import net.minecraft.text.LiteralText;

public class Help extends Command {

    public Help() {
        super("help", "helps u penis.", "help", "h");
    }

    TextFormatting LIGHT_PURPLE = TextFormatting.LIGHT_PURPLE;
    TextFormatting GRAY = TextFormatting.GRAY;
    TextFormatting AQUA = TextFormatting.AQUA;
    TextFormatting BOLD = TextFormatting.BOLD;
    TextFormatting ITALIC = TextFormatting.ITALIC;

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 0) {
            CommandManager.correctUsageMsg(getName(), getSyntax());
            return;
        }

        welcomeMessage();
        Bedroom.commandManager.commands.forEach(c -> {
            helpMessage(c.getName(), c.getDescription(), c.getSyntax());
        });
        goodbyeMessage();
    }

    private void helpMessage(String commandName, String commandDesc, String commandSyntax) {
        String starter = LIGHT_PURPLE + commandName + GRAY + " - " + commandDesc;
        String syntaxMessage = " [" + CommandManager.prefix + commandSyntax + "]";

        CommandManager.addCustomChatMessage(starter);
        CommandManager.addCustomChatMessage(syntaxMessage);
    }

    private void welcomeMessage() {
        String welcomeString = GRAY + "" + BOLD + Main.name + " " + Main.version + "!";
        String nothing = " ";
        String atClientName = AQUA + "@" + ITALIC + Main.name;

        CommandManager.addCustomChatMessage(welcomeString);
        CommandManager.addCustomChatMessage(nothing);
        CommandManager.addCustomChatMessage(atClientName);

    }

    private void goodbyeMessage() {
        String uwu = GRAY + "" + BOLD + "uwu" + AQUA + " ~";
        String nothing = " ";

        CommandManager.addCustomChatMessage(nothing);
        CommandManager.addCustomChatMessage(uwu);
    }

}