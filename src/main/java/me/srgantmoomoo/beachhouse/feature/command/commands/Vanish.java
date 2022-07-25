package me.srgantmoomoo.beachhouse.feature.command.commands;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.Bedroom;
import me.srgantmoomoo.bedroom.command.Command;
import net.minecraft.entity.Entity;
import net.minecraft.util.Formatting;

public class Vanish extends Command {

    public Vanish() {
        super("vanish", "vanish a ridden entity.", "vanish", "v");
    }
    private Entity vehicle;

    @Override
    public void onCommand(String[] args, String command) {
        if(args.length > 0) {
            Bedroom.INSTANCE.commandManager.correctUsageMsg(getName(), getSyntax());
            return;
        }

        if(Reference.minecraft.player.getVehicle() != null && vehicle == null) {
            vehicle = Reference.minecraft.player.getVehicle();

            Reference.minecraft.player.dismountVehicle();
            Reference.minecraft.world.removeEntity(vehicle.getId(), Entity.RemovalReason.DISCARDED);
            Bedroom.INSTANCE.commandManager.addChatMessage("entity " + Formatting.WHITE + vehicle.getEntityName() + Formatting.GRAY + " was removed.");
        }else if(vehicle != null) {
            //vehicle.isAlive();

            Reference.minecraft.world.addEntity(vehicle.getId(), vehicle);
            Reference.minecraft.player.startRiding(vehicle, false);
            vehicle = null;
        }else Bedroom.INSTANCE.commandManager.addChatMessage("no vehicle is being ridden.");
    }

}
