package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.beachhouse.backend.util.Timer;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;
import net.minecraft.util.Formatting;

// i got this idea from kfc lol :)
public class DoomHud extends Command {

    public DoomHud() {
        super("doomhud", "makes doom hud.", "doomhud | doomhud mc", "dh");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length > 1) {
            Bedroom.commandManager.correctUsageMsg(getName(), getSyntax());
            return;
        }

        if(args.length == 1) {
            if(args[0].equals("mc")) {
                //display doomhud mc
            }else
                Bedroom.commandManager.correctUsageMsg(getName(), getSyntax());
        }else {
            //display doomhud
        }
    }
}
