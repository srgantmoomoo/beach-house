package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.beachhouse.backend.util.Timer;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;
import net.minecraft.util.Formatting;

//TODO add to help comm.
// add timer
public class Clock extends Command {

    public Clock() {
        super("clock", "does clock things.", "clock | clock start | clock get | clock timer <time>", "clk");
    }

    Formatting LIGHT_PURPLE = Formatting.LIGHT_PURPLE;
    Formatting GRAY = Formatting.GRAY;
    Formatting AQUA = Formatting.AQUA;
    Formatting BOLD = Formatting.BOLD;
    Formatting ITALIC = Formatting.ITALIC;
    Timer timer = new Timer();

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 1) {
            Bedroom.commandManager.correctUsageMsg(getName(), getSyntax());
            return;
        }

        String comm = args[0];

        if(comm.equals("start")) {
            timer.reset();
            timer.update();
        }else if(comm.equals("get")) {
            Bedroom.commandManager.addChatMessage("current timer is at " + Math.round(timer.getPassed() / 1000));
        }else
            Bedroom.commandManager.correctUsageMsg(getName(), getSyntax());
    }
}