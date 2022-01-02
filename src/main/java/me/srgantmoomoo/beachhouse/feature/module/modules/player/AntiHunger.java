package me.srgantmoomoo.beachhouse.feature.module.modules.player;

import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventPacket;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class AntiHunger extends Module {

    public AntiHunger() {
        super("anti hunger", "antihunger", "lose hunger less often.", 0, Category.PLAYER);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventPacket.Send) {
            if(((EventPacket.Send) e).getPacket() instanceof PlayerMoveC2SPacket) {
                ((PlayerMoveC2SPacket) ((EventPacket.Send) e).getPacket()).onGround = false;
            }
        }
    }

}
