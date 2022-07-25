package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;
import net.minecraft.util.Formatting;

public class Help extends Command {

    public Help() {
        super("help", "helps u penis.", "help | help 1 | help 2", "h");
    }

    Formatting LIGHT_PURPLE = Formatting.LIGHT_PURPLE;
    Formatting GRAY = Formatting.GRAY;
    Formatting AQUA = Formatting.AQUA;
    Formatting BOLD = Formatting.BOLD;
    Formatting ITALIC = Formatting.ITALIC;

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length > 1) {
            Bedroom.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
            return;
        }

        if(args.length == 0) {
            displayPage1();
            return;
        }

        String page = args[0];
        if(page.equals("1")) {
            displayPage1();
            return;
        }

        if(page.equals("2")) {
            displayPage2();
            return;
        }

        Bedroom.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
    }

    private void displayPage1() {
        welcomeMessage();
        for(Command c : Bedroom.INSTANCE.commandManager.commands.subList(0, 6)) {
            helpMessage(c.getName(), c.getDescription(), c.getSyntax());
        }
        goodbyeMessage();
    }

    private void displayPage2() {
        welcomeMessage();
        for(Command c : Bedroom.INSTANCE.commandManager.commands.subList(6, 11)) {
            helpMessage(c.getName(), c.getDescription(), c.getSyntax());
        }
        goodbyeMessage();
    }

    private void helpMessage(String commandName, String commandDesc, String commandSyntax) {
        String starter = LIGHT_PURPLE + commandName + GRAY + " - " + commandDesc;
        String syntaxMessage = " [" + Bedroom.INSTANCE.commandManager.prefix + commandSyntax + "]";

        Bedroom.INSTANCE.commandManager.addCustomChatMessage(starter);
        Bedroom.INSTANCE.commandManager.addCustomChatMessage(syntaxMessage);
    }

    private void welcomeMessage() {
        String welcomeString = GRAY + "" + BOLD + Main.INSTANCE.name + " " + Main.INSTANCE.version + "!";
        String nothing = " ";
        String atClientName = AQUA + "@" + ITALIC + Main.INSTANCE.name;

        Bedroom.INSTANCE.commandManager.addCustomChatMessage(welcomeString);
        Bedroom.INSTANCE.commandManager.addCustomChatMessage(nothing);
        Bedroom.INSTANCE.commandManager.addCustomChatMessage(atClientName);

    }

    private void goodbyeMessage() {
        String uwu = GRAY + "" + BOLD + "uwu" + AQUA + " ~";
        String nothing = " ";

        Bedroom.INSTANCE.commandManager.addCustomChatMessage(nothing);
        Bedroom.INSTANCE.commandManager.addCustomChatMessage(uwu);
    }

}