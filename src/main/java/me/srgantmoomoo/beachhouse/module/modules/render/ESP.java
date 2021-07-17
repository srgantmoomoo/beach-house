package me.srgantmoomoo.beachhouse.module.modules.render;

import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;

public class ESP extends Module {
    public BooleanSetting playerEsp = new BooleanSetting("player esp", this, true);
    public BooleanSetting hostileMobEsp = new BooleanSetting("hostile mob esp", this, true);
    public BooleanSetting passiveMobEsp = new BooleanSetting("passive mob esp", this, true);
    public BooleanSetting storageEsp = new BooleanSetting("storage esp", this, true);
    public BooleanSetting holeEsp = new BooleanSetting("hole esp", this, true);
    public BooleanSetting voidEsp = new BooleanSetting("void esp", this, true);
    public BooleanSetting crystalEsp = new BooleanSetting("crystal esp", this, true);

    public ESP() {
        super("esp", "ESP", "allows you to see certain objects.", 0, Category.RENDER);
        this.addSettings(playerEsp, hostileMobEsp, passiveMobEsp, storageEsp, holeEsp, voidEsp, crystalEsp);
    }
}