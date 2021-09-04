package me.srgantmoomoo.beachhouse.module.modules.render;

import me.srgantmoomoo.beachhouse.backend.events.EventRender3d;
import me.srgantmoomoo.beachhouse.backend.util.Reference;
import me.srgantmoomoo.bedroom.api.event.Event;
import me.srgantmoomoo.bedroom.module.Module;

public class VibrantShader extends Module {

    public VibrantShader() {
        super("vibrant shader", "vibrantshader", "this is a description", 0, Category.RENDER);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventRender3d) {
            Reference.vibrant.render(1);
        }
    }

}
