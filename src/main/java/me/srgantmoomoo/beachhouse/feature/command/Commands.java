package me.srgantmoomoo.beachhouse.feature.command;

import me.srgantmoomoo.beachhouse.feature.command.commands.*;
import me.srgantmoomoo.bedroom.Bedroom;

public class Commands {
    public static void init() {
        Bedroom.addCommand(new Help());
        Bedroom.addCommand(new Prefix());
        Bedroom.addCommand(new Toggle());
        Bedroom.addCommand(new ModuleList());
        Bedroom.addCommand(new Setting());
        Bedroom.addCommand(new SettingList());
        Bedroom.addCommand(new Clear());
        Bedroom.addCommand(new Notepad());
    }
}
