package me.srgantmoomoo.beachhouse.feature.module.modules.miscellaneous;

import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.event.events.EventTick;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;
import net.minecraft.text.LiteralText;

import java.util.Objects;

public class AutoLog extends Module {
    public NumberSetting health = new NumberSetting("health", this, 4, 0, 36, 1);

    public AutoLog() {
        super("auto log", "autolog", "log out at certain health.", 0, Category.PLAYER);
        this.addSettings(health);
    }

}
