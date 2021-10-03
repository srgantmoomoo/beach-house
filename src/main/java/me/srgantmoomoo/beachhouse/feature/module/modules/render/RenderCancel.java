package me.srgantmoomoo.beachhouse.feature.module.modules.render;

import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;

public class RenderCancel extends Module {
    public static RenderCancel INSTANCE;

    public BooleanSetting fog = new BooleanSetting("fog", this, false); //TODO no work???
    public BooleanSetting weather = new BooleanSetting("weather", this, false);
    public BooleanSetting skyLightUpdates = new BooleanSetting("skyLightUpdates", this, false);
    public BooleanSetting enchantTables = new BooleanSetting("enchantTables", this, false);
    public BooleanSetting signText = new BooleanSetting("signText", this, false);
    public BooleanSetting fireworks = new BooleanSetting("fireworks", this, false);

    public BooleanSetting hurtCam = new BooleanSetting("hurtCam", this, false);
    public BooleanSetting portalOverlay = new BooleanSetting("portalOverlay", this, false);
    public BooleanSetting fireOverlay = new BooleanSetting("fireOverlay", this, false);
    public BooleanSetting waterOverlay = new BooleanSetting("waterOverlay", this, false);
    public BooleanSetting armor = new BooleanSetting("armor", this, false);

    public RenderCancel() {
        super("render cancel", "rendercancel", "cancel certain render events.", 0, Category.RENDER);
        INSTANCE = this;
        this.addSettings(fog, weather, skyLightUpdates, enchantTables, signText, fireworks, hurtCam, fireOverlay, waterOverlay, portalOverlay, armor);
    }

    // fog is in MixinBackgroundRenderer.
    // weather is in MixinWorldRenderer.
    // skyLightUpdates is in MixinChunkSkyLightProvider.
    // enchantingTables is in MixinEnchantingTableBlockEntityRenderer.
    // signText is in MixinSignBlockEntityRenderer.
    // fireworks is in MixinFireworksSparkParticle.

    // hurtcam is in MixinGameRenderer.
    // portalOverlay is in MixinInGameHud
    // fire and water overlay are in MixinInGameOverlayRenderer
    // armor is in MixinArmorFeatureRenderer.

    @Override
    public void onEnable() {

    }

    @Override
    public void onEvent(Event e) {

    }

}