package me.srgantmoomoo.beachhouse.feature.module.modules.miscellaneous;

import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventPacket;
import me.srgantmoomoo.bedroom.module.Module;

public class PacketCancel extends Module {

    public PacketCancel() {
        super("packet cancel", "packetcancel", "cancels the packets u send to the server.", 0, Category.MISCELLANEOUS);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventPacket.Send) {
            e.setCancelled(true);
        }
    }
}
