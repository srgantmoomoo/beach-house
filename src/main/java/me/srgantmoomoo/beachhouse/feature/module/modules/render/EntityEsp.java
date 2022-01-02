package me.srgantmoomoo.beachhouse.feature.module.modules.render;

import com.google.common.collect.Maps;
import me.srgantmoomoo.beachhouse.backend.events.EventRender2d;
import me.srgantmoomoo.beachhouse.backend.events.EventRender3d;
import me.srgantmoomoo.beachhouse.backend.util.math.ClientMathHelper;
import me.srgantmoomoo.beachhouse.backend.util.render.Render2DHelper;
import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.ColorSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import me.srgantmoomoo.bedroom.util.font.JColor;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;

public class EntityEsp extends Module {
    public BooleanSetting self = new BooleanSetting("self", this, false);
    public BooleanSetting player = new BooleanSetting("player", this, true);
    public BooleanSetting hostile = new BooleanSetting("hostile", this, true);
    public BooleanSetting passive = new BooleanSetting("passive", this, true);
    public BooleanSetting item = new BooleanSetting("item", this, true);
    //public BooleanSetting hole = new BooleanSetting("hole", this, false); *** not adding these to entity esp... probably some other module.
    //public BooleanSetting voidEsp = new BooleanSetting("void", this, false);
    //public BooleanSetting crystal = new BooleanSetting("crystal", this, false);
    public ColorSetting highlightColor = new ColorSetting("highlightColor", this, new JColor(0, 0, 0, 0));
    public ColorSetting outlineColor = new ColorSetting("outlineColor", this, new JColor(0, 255, 0, 255));
    public NumberSetting range = new NumberSetting("range", this, 36, 0, 500, 1);

    public EntityEsp() {
        super("entity esp", "entityesp", "allows you to see certain entities.", 0, Category.RENDER);
        this.addSettings(self, player, hostile, passive, item, highlightColor, outlineColor, range);
    }

    private HashMap<Entity, Vec3d> headPos = Maps.newHashMap();
    private HashMap<Entity, Vec3d> footPos = Maps.newHashMap();

    @SuppressWarnings("rawtypes")
    @Override
    public void onEvent(Event e) {
        if(e instanceof EventRender3d) {
            headPos.clear();
            footPos.clear();
            for (Entity entity : minecraft.world.getEntities()) {
                if (isValid(entity)) {
                    headPos.put(entity, Render2DHelper.INSTANCE.getPos(entity, entity.getHeight() + 0.2f, ((EventRender3d) e).partialTicks, ((EventRender3d) e).matrix));
                    footPos.put(entity, Render2DHelper.INSTANCE.getPos(entity, -0.2f, ((EventRender3d) e).partialTicks, ((EventRender3d) e).matrix));
                }
            }
        }else if(e instanceof EventRender2d) {
            headPos.keySet().forEach(entity -> {
                Vec3d top = headPos.get(entity);
                Vec3d bottom = footPos.get(entity);
                if (Render2DHelper.INSTANCE.isOnScreen(top) && Render2DHelper.INSTANCE.isOnScreen(bottom)) {
                    float x = (float) top.x;
                    float y = (float) top.y;
                    float x2 = (float) bottom.x;
                    float y2 = (float) bottom.y;
                    if(y > y2) {
                        float saved = y;
                        y = y2;
                        y2 = saved;
                    }
                    if(x > x2) {
                        float saved = x;
                        x = x2;
                        x2 = saved;
                    }
                    float dif = Math.abs(y2 - y);

                    if(entity instanceof ItemEntity)
                        dif /= 2;
                    else
                        dif /= ClientMathHelper.INSTANCE.clamp(entity.getWidth() * 5f, 1f, 10f);
                    drawBox(((EventRender2d) e).matrix, x - dif, y + 1, x2 + dif, y2);
                }
            });
        }
    }

    public void drawBox(MatrixStack matrixStack, float x, float y, float x2, float y2) {
        JColor back = highlightColor.getValue();
        JColor outline = outlineColor.getValue();
        Render2DHelper.INSTANCE.fillAndBorder(matrixStack, x, y, x2, y2, outline.getRGB(), back.getRGB(), 1f);
    }

    public boolean isValid(Entity entity) {
        if(minecraft.player.distanceTo(entity) > range.getValue())
            return false;

        if (entity == null)
            return false;
        if (entity instanceof ItemEntity)
            return item.isEnabled();
        if (!(entity instanceof LivingEntity livingEntity))
            return false;
        if (livingEntity == minecraft.player)
            return self.isEnabled();
        if (livingEntity instanceof PlayerEntity)
            return player.isEnabled();
        if(livingEntity instanceof PassiveEntity)
            return passive.isEnabled();
        if (livingEntity instanceof HostileEntity)
            return hostile.isEnabled();
        return false;
    }
}
