package me.srgantmoomoo.beachhouse.feature.module.modules.render;

import me.srgantmoomoo.beachhouse.backend.events.EventRender3d;
import me.srgantmoomoo.bedroom.event.Event;
import me.srgantmoomoo.bedroom.module.Module;

public class BlockHighlight extends Module {

    public BlockHighlight() {
        super("block highlight", "blockhighlight", "gadfhsjk", 0, Category.RENDER);
    }

    @Override
    public void onEvent(Event e) {
        if(e instanceof EventRender3d) {

        }
    }

}
