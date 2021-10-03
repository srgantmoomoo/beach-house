package me.srgantmoomoo.beachhouse.backend.events;

import me.srgantmoomoo.bedroom.event.Event;
import net.minecraft.util.math.Vec3d;

// called in MixinEntity
public class EventPlayerPushed extends Event<EventPlayerPushed> {

    private Vec3d push;

    public EventPlayerPushed(Vec3d push) {
        this.push = push;
    }

    public Vec3d getPush() {
        return push;
    }

    public void setPush(Vec3d push) {
        this.push = push;
    }
}
