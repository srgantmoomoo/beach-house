package me.srgantmoomoo.beachhouse.module.modules.render;

import com.google.common.collect.Maps;
import me.srgantmoomoo.beachhouse.backend.ClientMathHelper;
import me.srgantmoomoo.beachhouse.backend.Render2DHelper;
import me.srgantmoomoo.beachhouse.backend.events.EventRender2D;
import me.srgantmoomoo.bedroom.api.event.events.EventWorldRender;
import me.srgantmoomoo.bedroom.module.Module;
import me.srgantmoomoo.bedroom.module.setting.settings.BooleanSetting;
import me.srgantmoomoo.bedroom.module.setting.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;

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
    private HashMap<Entity, Vec3d> headPos = Maps.newHashMap();
    private HashMap<Entity, Vec3d> footPos = Maps.newHashMap();

    /*@EventHandler
    private final Listener<EventWorldRender> worldRenderListener = new Listener<>(e -> {
        headPos.clear();
        footPos.clear();
        for (Entity entity : minecraft.world.getEntities()) {
            headPos.put(entity, Render2DHelper.INSTANCE.getPos(entity, entity.getHeight() + 0.2f, e.partialTicks, e.matrix));
            footPos.put(entity, Render2DHelper.INSTANCE.getPos(entity, -0.2f, e.partialTicks, e.matrixStack()));
        }
    });

    @EventHandler
    private final Listener<EventRender2D> twoDListener = new Listener<>(e -> {

        headPos.keySet().forEach(entity -> {
            Vec3d top = headPos.get(entity);
            Vec3d bottom = footPos.get(entity);
            if (Render2DHelper.INSTANCE.isOnScreen(top) && Render2DHelper.INSTANCE.isOnScreen(bottom)) {
                float x = (float) top.x;
                float y = (float) top.y;
                float x2 = (float) bottom.x;
                float y2 = (float) bottom.y;
                if (y > y2) {
                    float saved = y;
                    y = y2;
                    y2 = saved;
                }
                if (x > x2) {
                    float saved = x;
                    x = x2;
                    x2 = saved;
                }
                float dif = Math.abs(y2 - y);

                if (entity instanceof ItemEntity)
                    dif /= 2;
                else
                    dif /= ClientMathHelper.INSTANCE.clamp(entity.getWidth() * 5f, 1f, 10f);
                drawBox(e.getMatrixStack(), x - dif, y + 1, x2 + dif, y2, entity);
            }
        });
    });

    public void drawBox(MatrixStack matrixStack, float x, float y, float x2, float y2, Entity entity) {
        float f = 1f;

        if(entity instanceof LivingEntity){
            float percent = ((LivingEntity) entity).getHealth() / ((LivingEntity) entity).getMaxHealth();
            int color = 0xffffffff;
            float distance = y - y2;
            float pos = percent * distance;
            Render2DHelper.INSTANCE.fillAndBorder(matrixStack,x2 - 1, y2 + pos, x2 + 2, y2, 0xff000000, color, 1);
        }
        int color = 0xffffffff;
        Render2DHelper.INSTANCE.fillAndBorder(matrixStack, x, y, x2, y2, 0xff000000, color, 1);
    }*/

}