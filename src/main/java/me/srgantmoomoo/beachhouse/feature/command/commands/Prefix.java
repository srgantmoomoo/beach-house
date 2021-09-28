package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;

public class Prefix extends Command {

    public Prefix() {
        super("prefix", "allows you to change the command prefix.", "prefix <key>", "p");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 1) {
            Bedroom.commandManager.correctUsageMsg(name, syntax);
            return;
        }

        String key = args[0];
        Bedroom.commandManager.setCommandPrefix(key);
        Bedroom.commandManager.addChatMessage(String.format(TextFormatting.GREEN + "prefix " + TextFormatting.GRAY + "was set to " + TextFormatting.GREEN + Bedroom.commandManager.prefix));
    }
}
