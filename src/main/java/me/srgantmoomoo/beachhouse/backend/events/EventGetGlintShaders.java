package me.srgantmoomoo.beachhouse.backend.events;

import me.srgantmoomoo.bedroom.api.event.Event;
import net.minecraft.client.render.Shader;

public class EventGetGlintShaders extends Event<EventGetGlintShaders> {

    private Shader shader;

    public EventGetGlintShaders(Shader shader) {
        this.shader = shader;
    }

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }
}