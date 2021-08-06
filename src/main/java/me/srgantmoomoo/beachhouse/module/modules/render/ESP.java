package me.srgantmoomoo.beachhouse.module.modules.render;

import me.srgantmoomoo.beachhouse.backend.ClientMathHelper;
import me.srgantmoomoo.beachhouse.backend.Render2DHelper;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import net.minecraft.client.MinecraftClient;

public class ESP extends Module {
    public BooleanSetting playerEsp = new BooleanSetting("player", this, true);
    public BooleanSetting hostileMobEsp = new BooleanSetting("hostileMob", this, true);
    public BooleanSetting passiveMobEsp = new BooleanSetting("passiveMob", this, true);
    public BooleanSetting storageEsp = new BooleanSetting("storage ", this, true);
    public BooleanSetting holeEsp = new BooleanSetting("hole", this, true);
    public BooleanSetting voidEsp = new BooleanSetting("void", this, true);
    public BooleanSetting crystalEsp = new BooleanSetting("crystal", this, true);
    public NumberSetting range = new NumberSetting("range", this, 1, 0, 100, 1);

    public ESP() {
        super("esp", "esp", "allows you to see certain objects.", 0, Category.RENDER);
        this.addSettings(playerEsp, hostileMobEsp, passiveMobEsp, storageEsp, holeEsp, voidEsp, crystalEsp, range);
    }

    MinecraftClient minecraft = MinecraftClient.getInstance();

}