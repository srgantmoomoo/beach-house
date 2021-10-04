package me.srgantmoomoo.beachhouse.feature.module.modules.player;

import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventPacket;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

public class PlayerVelocity extends Module {
    public static PlayerVelocity INSTANCE;

    public BooleanSetting noPush = new BooleanSetting("noPush", this, true);
    public BooleanSetting knockBack = new BooleanSetting("knockBack", this, true);
    public NumberSetting knockBackPercent = new NumberSetting("kb%", this, 0, 0, 1, 0.1);

    public PlayerVelocity() {
        super("player velocity", "playervelocity", "velocity.", 0, Category.PLAYER);
        INSTANCE = this;
        this.addSettings(noPush, knockBack, knockBackPercent);
    }
    // noPush is in MixinEntity for entities, and MixinFluidState for fluids.

    @Override
    public void onEvent(Event e) {

        if(knockBack.isEnabled()) {

            if (e instanceof EventPacket.Send) {
                if (((EventPacket) e).getPacket() instanceof EntityVelocityUpdateS2CPacket p) {
                    if (minecraft.player == null || minecraft.world == null)
                        return;

                    if (p.getId() == minecraft.player.getId()) {
                        p.velocityX = (int) knockBackPercent.getValue(); //TODO this doesn't really work... math has to be done but im to lazy to do it rn and have to go do something else.
                        p.velocityY = (int) knockBackPercent.getValue();
                        p.velocityZ = (int) knockBackPercent.getValue();
                    }

                } else if (((EventPacket.Send) e).getPacket() instanceof ExplosionS2CPacket p_1) {
                    p_1.playerVelocityX = (int) knockBackPercent.getValue();
                    p_1.playerVelocityY = (int) knockBackPercent.getValue();
                    p_1.playerVelocityZ = (int) knockBackPercent.getValue();
                }
            }

        }

    }

}