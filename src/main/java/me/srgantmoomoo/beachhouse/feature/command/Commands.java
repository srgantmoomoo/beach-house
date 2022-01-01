package me.srgantmoomoo.beachhouse.feature.command;

import me.srgantmoomoo.beachhouse.feature.command.commands.*;
import me.srgantmoomoo.bedroom.Bedroom;

public class Commands {
    public static void init() {
        // these are ordered in the way that they appear through the help command.
        Bedroom.addCommand(new Help());
        Bedroom.addCommand(new Prefix());
        Bedroom.addCommand(new Toggle());
        Bedroom.addCommand(new ModuleList());
        Bedroom.addCommand(new Setting());
        Bedroom.addCommand(new SettingList());
        Bedroom.addCommand(new Notepad());
        Bedroom.addCommand(new CheckModules());
        Bedroom.addCommand(new Clock());
        Bedroom.addCommand(new Clear());
        Bedroom.addCommand(new DoomHud());
        //Bedroom.addCommand(new AntiNick());
        //x and y lock
        // move packet cancel to here.
    }
}
