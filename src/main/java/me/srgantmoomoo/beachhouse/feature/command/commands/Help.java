package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.beachhouse.Main;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;

public class Help extends Command {

    public Help() {
        super("help", "helps u penis.", "help | help 1 | help 2", "h");
    }

    TextFormatting LIGHT_PURPLE = TextFormatting.LIGHT_PURPLE;
    TextFormatting GRAY = TextFormatting.GRAY;
    TextFormatting AQUA = TextFormatting.AQUA;
    TextFormatting BOLD = TextFormatting.BOLD;
    TextFormatting ITALIC = TextFormatting.ITALIC;

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length > 1) {
            Bedroom.commandManager.correctUsageMsg(getName(), getSyntax());
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

        Bedroom.commandManager.correctUsageMsg(getName(), getSyntax());
    }

    private void displayPage1() {
        welcomeMessage();
        for(Command c : Bedroom.commandManager.commands.subList(0, 6)) {
            helpMessage(c.getName(), c.getDescription(), c.getSyntax());
        }
        goodbyeMessage();
    }

    private void displayPage2() {
        welcomeMessage();
        for(Command c : Bedroom.commandManager.commands.subList(6, 8)) {
            helpMessage(c.getName(), c.getDescription(), c.getSyntax());
        }
        goodbyeMessage();
    }

    private void helpMessage(String commandName, String commandDesc, String commandSyntax) {
        String starter = LIGHT_PURPLE + commandName + GRAY + " - " + commandDesc;
        String syntaxMessage = " [" + Bedroom.commandManager.prefix + commandSyntax + "]";

        Bedroom.commandManager.addCustomChatMessage(starter);
        Bedroom.commandManager.addCustomChatMessage(syntaxMessage);
    }

    private void welcomeMessage() {
        String welcomeString = GRAY + "" + BOLD + Main.name + " " + Main.version + "!";
        String nothing = " ";
        String atClientName = AQUA + "@" + ITALIC + Main.name;

        Bedroom.commandManager.addCustomChatMessage(welcomeString);
        Bedroom.commandManager.addCustomChatMessage(nothing);
        Bedroom.commandManager.addCustomChatMessage(atClientName);

    }

    private void goodbyeMessage() {
        String uwu = GRAY + "" + BOLD + "uwu" + AQUA + " ~";
        String nothing = " ";

        Bedroom.commandManager.addCustomChatMessage(nothing);
        Bedroom.commandManager.addCustomChatMessage(uwu);
    }

}