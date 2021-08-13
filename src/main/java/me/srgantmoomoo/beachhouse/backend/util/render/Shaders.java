package me.srgantmoomoo.beachhouse.backend.util.render;

import me.srgantmoomoo.beachhouse.backend.util.Reference;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.ShaderEffect;
import net.minecraft.client.render.Shader;
import net.minecraft.client.render.VertexFormats;

import net.minecraft.util.Identifier;

public enum Shaders {
    INSTANCE;
    public Framebuffer storageFBO;
    public ShaderEffect storageShader;
    public Framebuffer boxOutlineFBO;
    public ShaderEffect boxOutlineShader;
    public Identifier identifier_1 = new Identifier("jex", "shaders/entity_outline.json");

    private static Shader rainbowEnchantShader;
    private static Shader translucentShader;
    private static Shader testShader;

    //private OutlineShader outlineShader = new OutlineShader();

    public static void loadCustomMCShaders() {
        try {
            rainbowEnchantShader = new Shader(Reference.minecraft.getResourcePackProvider().getPack(), "bh:rainbow_enchant", VertexFormats.POSITION_TEXTURE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Shader getRainbowEnchantShader() {
        return rainbowEnchantShader;
    }

}