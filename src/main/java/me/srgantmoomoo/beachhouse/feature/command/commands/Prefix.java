package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;
import net.minecraft.util.Formatting;

public class Prefix extends Command {

    public Prefix() {
        super("prefix", "allows you to change the command prefix.", "prefix <key>", "p");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 1) {
            Bedroom.INSTANCE.commandManager.correctUsageMsg(name, syntax);
            return;
        }

        String key = args[0];
        Bedroom.INSTANCE.commandManager.setCommandPrefix(key);
        Bedroom.INSTANCE.commandManager.addChatMessage(String.format(Formatting.GREEN + "prefix " + Formatting.GRAY + "was set to " + Formatting.GREEN + Bedroom.INSTANCE.commandManager.prefix));
    }
}
