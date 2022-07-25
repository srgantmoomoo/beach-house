package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.beachhouse.backend.util.Timer;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;
import net.minecraft.util.Formatting;

//TODO add to help comm.
// add timer
public class Clock extends Command {
    Timer timer = new Timer();

    public Clock() {
        super("clock", "does clock things.", "clock | clock start | clock get | clock timer <time>", "clk");
    }

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length != 1) {
            Bedroom.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
            return;
        }
        String comm = args[0];

        if(comm.equals("start")) {
            timer.reset();
            timer.update();
            Bedroom.INSTANCE.commandManager.addChatMessage("started a new clock.");
        }else if(comm.equals("get")) {
            Bedroom.INSTANCE.commandManager.addChatMessage("current timer is at " + Formatting.WHITE + Math.round(timer.getPassed() / 1000) + Formatting.GRAY +  ".");
        }else
            Bedroom.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
    }
}