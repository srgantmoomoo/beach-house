package me.srgantmoomoo.beachhouse.backend.util.render;

import net.minecraft.client.gl.GlUniform;

public interface IShader {

    GlUniform getCustomUniform(String name);

}