package me.srgantmoomoo.beachhouse.command.commands;

import me.srgantmoomoo.bedroom.api.util.font.TextFormatting;
import me.srgantmoomoo.bedroom.command.Command;
import me.srgantmoomoo.bedroom.command.CommandManager;

public class Prefix extends Command {

    public Prefix() {
        super("prefix", "allows you to change the command prefix.", "prefix <key>", "p");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 1) {
            CommandManager.correctUsageMsg(name, syntax);
            return;
        }

        String key = args[0];
        CommandManager.setCommandPrefix(key);
        CommandManager.addChatMessage(String.format(TextFormatting.GREEN + "prefix " + TextFormatting.GRAY + "was set to " + TextFormatting.GREEN + CommandManager.prefix));
    }
}
