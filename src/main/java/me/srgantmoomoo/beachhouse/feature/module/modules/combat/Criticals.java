package me.srgantmoomoo.beachhouse.feature.module.modules.combat;

import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventPacket;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Criticals extends Module {

	public Criticals() {
		super("criticals", "criticals", "cccriticals", 0, Category.COMBAT);
	}

	@Override
	public void onEvent(Event e) {
		if(e instanceof EventPacket.Send) {

		}
	}
	
}
