package me.srgantmoomoo.beachhouse.feature.module.modules.combat;

import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventPacket;
import me.srgantmoomoo.bedroom.module.Module;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;

public class FootExp extends Module {

    public FootExp() {
        super("foot exp", "footexp", "log.", 0, Category.COMBAT);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventPacket.Send) {
            if(minecraft.player.isUsingItem() && minecraft.player.getMainHandStack().getItem().equals(Items.EXPERIENCE_BOTTLE)) {

            }
        }
    }

}
