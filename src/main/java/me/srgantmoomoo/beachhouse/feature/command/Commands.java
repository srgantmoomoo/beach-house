package me.srgantmoomoo.beachhouse.feature.command;

import me.srgantmoomoo.beachhouse.feature.command.commands.*;
import me.srgantmoomoo.bedroom.Bedroom;

public class Commands {
    public static void init() {
        // these are ordered in the way that they appear through the help command.
        Bedroom.INSTANCE.addCommand(new Help());
        Bedroom.INSTANCE.addCommand(new Prefix());
        Bedroom.INSTANCE.addCommand(new Toggle());
        Bedroom.INSTANCE.addCommand(new ModuleList());
        Bedroom.INSTANCE.addCommand(new Setting());
        Bedroom.INSTANCE.addCommand(new SettingList());
        Bedroom.INSTANCE.addCommand(new Notepad());
        Bedroom.INSTANCE.addCommand(new CheckModules());
        Bedroom.INSTANCE.addCommand(new Clock());
        Bedroom.INSTANCE.addCommand(new Clear());
        Bedroom.INSTANCE.addCommand(new DoomHud());
        Bedroom.INSTANCE.addCommand(new Vanish());
        //Bedroom.addCommand(new AntiNick());
        //x and y lock
        // move packet cancel to here.
    }
}
