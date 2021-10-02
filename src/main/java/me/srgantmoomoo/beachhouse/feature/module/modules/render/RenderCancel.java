package me.srgantmoomoo.beachhouse.feature.module.modules.render;

import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;

public class RenderCancel extends Module {
    public static RenderCancel INSTANCE;

    public BooleanSetting fog = new BooleanSetting("fog", this, false); //TODO no work???
    public BooleanSetting weather = new BooleanSetting("weather", this, false); //TODO
    public BooleanSetting skyLightUpdates = new BooleanSetting("skyLightUpdates", this, false);
    public BooleanSetting enchantingTables = new BooleanSetting("enchantingTables", this, false);
    public BooleanSetting signText = new BooleanSetting("signText", this, false); //TODO
    public BooleanSetting fireworks = new BooleanSetting("fireworks", this, false);
    public BooleanSetting hurtCam = new BooleanSetting("hurtCam", this, false); //TODO
    public BooleanSetting armor = new BooleanSetting("armor", this, false);

    public RenderCancel() {
        super("render cancel", "rendercancel", "cancel certain render events.", 0, Category.RENDER);
        INSTANCE = this;
        this.addSettings(fog, weather, skyLightUpdates, enchantingTables, fireworks, hurtCam, armor);
    }

    // fog is in MixinBackgroundRenderer.
    // armor is in MixinArmorFeatureRenderer.
    // fireworks is in MixinFireworksSparkParticle.
    // skyLightUpdates is in MixinChunkSkyLightProvider.
    // enchantingTables is in MixinEnchantingTableBlockEntityRenderer.

    @Override
    public void onEnable() {

    }

    @Override
    public void onEvent(Event e) {

    }

}