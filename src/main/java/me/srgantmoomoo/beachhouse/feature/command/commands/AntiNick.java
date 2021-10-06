package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;
import net.minecraft.client.network.AbstractClientPlayerEntity;

import java.util.*;

public class AntiNick extends Command {

    public AntiNick() {
        super("antinick", "anti nicholas.", "antinick", "a");
    }
    private List<AbstractClientPlayerEntity> serverPlayers;
    private boolean isNameReal = false;

    @Override
    public void onCommand(String[] args, String command) {
        String serverPlayers = Arrays.toString(Reference.minecraft.player.getServer().getPlayerNames()) + "";

        Bedroom.commandManager.addCustomChatMessage(serverPlayers + " biggy");

        //();
        //execute();
    }

   /* public void checkNames() {
        isNameReal = serverPlayers.contains("SrgantMooMoo");
    }

    public void execute() {
        if(isNameReal)
            Bedroom.commandManager.addCustomChatMessage("SMALLY");
        else
            Bedroom.commandManager.addCustomChatMessage("ONAEWWEEEE");
    }*/

}
