package me.srgantmoomoo.beachhouse.backend;

import com.mojang.blaze3d.systems.RenderSystem;
import me.srgantmoomoo.beachhouse.backend.util.Matrix4x4;
import me.srgantmoomoo.beachhouse.backend.util.Vector3D;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.*;
import org.lwjgl.opengl.GL11;

public enum Render2DHelper {
    INSTANCE;

    private final MinecraftClient minecraft = MinecraftClient.getInstance();

    public void setup2DRender(boolean disableDepth) {
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        if (disableDepth)
            RenderSystem.disableDepthTest();
    }

    public void end2DRender() {
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
        RenderSystem.enableDepthTest();
    }

    public double getScaleFactor() {
        return minecraft.getWindow().getScaleFactor();
    }

    public int getScaledWidth() {
        return minecraft.getWindow().getScaledWidth();
    }

    public int getScaledHeight() {
        return minecraft.getWindow().getScaledHeight();
    }

    public void drawTexture(MatrixStack matrices, float x, float y, float u, float v, float width, float height, int textureWidth, int textureHeight) {
        drawTexture(matrices, x, y, width, height, u, v, width, height, textureWidth, textureHeight);
    }

    private void drawTexture(MatrixStack matrices, float x, float y, float width, float height, float u, float v, float regionWidth, float regionHeight, int textureWidth, int textureHeight) {
        drawTexture(matrices, x, x + width, y, y + height, 0, regionWidth, regionHeight, u, v, textureWidth, textureHeight);
    }

    private void drawTexture(MatrixStack matrices, float x0, float y0, float x1, float y1, int z, float regionWidth, float regionHeight, float u, float v, int textureWidth, int textureHeight) {
        drawTexturedQuad(matrices.peek().getModel(), x0, y0, x1, y1, z, (u + 0.0F) / (float)textureWidth, (u + (float)regionWidth) / (float)textureWidth, (v + 0.0F) / (float)textureHeight, (v + (float)regionHeight) / (float)textureHeight);
    }

    public void drawTexturedQuad(Matrix4f matrices, float x0, float x1, float y0, float y1, float z, float u0, float u1, float v0, float v1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        bufferBuilder.vertex(matrices, (float)x0, (float)y1, (float)z).texture(u0, v1).next();
        bufferBuilder.vertex(matrices, (float)x1, (float)y1, (float)z).texture(u1, v1).next();
        bufferBuilder.vertex(matrices, (float)x1, (float)y0, (float)z).texture(u1, v0).next();
        bufferBuilder.vertex(matrices, (float)x0, (float)y0, (float)z).texture(u0, v0).next();
        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
    }

    public void fill(MatrixStack matrixStack, float x1, float y1, float x2, float y2, int color) {
        Matrix4f matrix = matrixStack.peek().getModel();
        float j;
        if (x1 < x2) {
            j = x1;
            x1 = x2;
            x2 = j;
        }

        if (y1 < y2) {
            j = y1;
            y1 = y2;
            y2 = j;
        }

        float f = (float)(color >> 24 & 255) / 255.0F;
        float g = (float)(color >> 16 & 255) / 255.0F;
        float h = (float)(color >> 8 & 255) / 255.0F;
        float k = (float)(color & 255) / 255.0F;
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(matrix, (float)x1, (float)y2, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float)x2, (float)y2, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float)x2, (float)y1, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float)x1, (float)y1, 0.0F).color(g, h, k, f).next();
        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public void fillAndBorder(MatrixStack matrixStack, float left, float top, float right, float bottom, int bcolor, int icolor, float f) {
        fill(matrixStack, left + f, top + f, right - f, bottom - f, icolor);
        fill(matrixStack, left, top, left + f, bottom, bcolor);
        fill(matrixStack, left + f, top, right, top + f, bcolor);
        fill(matrixStack, left + f, bottom - f, right, bottom, bcolor);
        fill(matrixStack, right - f, top + f, right, bottom - f, bcolor);
    }

    public Vec3d to2D(Vec3d worldPos, MatrixStack matrixStack) {
        Vec3d bound = getRenderPosition(worldPos, matrixStack);
        Vec3d twoD = to2D(bound.x, bound.y, bound.z);
        return new Vec3d(twoD.x, twoD.y, twoD.z);
    }

    private Vec3d to2D(double x, double y, double z) {
        int displayHeight = minecraft.getWindow().getHeight();
        Vector3D screenCoords = new Vector3D();
        int[] viewport = new int[4];
        GL11.glGetIntegerv(GL11.GL_VIEWPORT, viewport);
        Matrix4x4 matrix4x4Proj = Matrix4x4.copyFromColumnMajor(RenderSystem.getProjectionMatrix());//no more joml :)
        Matrix4x4 matrix4x4Model = Matrix4x4.copyFromColumnMajor(RenderSystem.getModelViewMatrix());//but I do the math myself now :( (heck math)
        matrix4x4Proj.mul(matrix4x4Model).project((float) x, (float) y, (float) z, viewport, screenCoords);

        return new Vec3d(screenCoords.x / Render2DHelper.INSTANCE.getScaleFactor(), (displayHeight - screenCoords.y) / Render2DHelper.INSTANCE.getScaleFactor(), screenCoords.z);
    }

    public Vec3d getHeadPos(Entity entity, float partialTicks, MatrixStack matrixStack) {
        Vec3d bound = getEntityRenderPosition(entity, partialTicks).add(0, entity.getHeight() + 0.2, 0);
        Vector4f vector4f = new Vector4f((float)bound.x, (float)bound.y, (float)bound.z, 1.f);
        vector4f.transform(matrixStack.peek().getModel());
        Vec3d twoD = to2D(vector4f.getX(), vector4f.getY(), vector4f.getZ());
        return new Vec3d(twoD.x, twoD.y, twoD.z);
    }

    public Vec3d getFootPos(Entity entity, float partialTicks, MatrixStack matrixStack) {
        Vec3d bound = getEntityRenderPosition(entity, partialTicks, matrixStack);
        Vec3d twoD = to2D(bound.x, bound.y, bound.z);
        return new Vec3d(twoD.x, twoD.y, twoD.z);
    }

    public Vec3d getPos(Entity entity, float yOffset, float partialTicks, MatrixStack matrixStack) {
        Vec3d bound = getEntityRenderPosition(entity, partialTicks).add(0, yOffset, 0);
        Vector4f vector4f = new Vector4f((float)bound.x, (float)bound.y, (float)bound.z, 1.f);
        vector4f.transform(matrixStack.peek().getModel());
        Vec3d twoD = to2D(vector4f.getX(), vector4f.getY(), vector4f.getZ());
        return new Vec3d(twoD.x, twoD.y, twoD.z);
    }

    public boolean isOnScreen(Vec3d pos) {
        if (pos.getZ() > -1 && pos.getZ() < 1) {
            return true;
        }
        return false;
    }

    // 3d
    public Vec3d getEntityRenderPosition(Entity entity, double partial, MatrixStack matrixStack) {
        Matrix4f matrix = matrixStack.peek().getModel();
        double x = entity.prevX + ((entity.getX() - entity.prevX) * partial) - minecraft.getEntityRenderDispatcher().camera.getPos().x;
        double y = entity.prevY + ((entity.getY() - entity.prevY) * partial) - minecraft.getEntityRenderDispatcher().camera.getPos().y;
        double z = entity.prevZ + ((entity.getZ() - entity.prevZ) * partial) - minecraft.getEntityRenderDispatcher().camera.getPos().z;
        Vector4f vector4f = new Vector4f((float)x, (float)y, (float)z, 1.f);
        vector4f.transform(matrix);
        return new Vec3d(vector4f.getX(), vector4f.getY(), vector4f.getZ());
    }

    public Vec3d getEntityRenderPosition(Entity entity, double partial) {
        double x = entity.prevX + ((entity.getX() - entity.prevX) * partial) - minecraft.getEntityRenderDispatcher().camera.getPos().x;
        double y = entity.prevY + ((entity.getY() - entity.prevY) * partial) - minecraft.getEntityRenderDispatcher().camera.getPos().y;
        double z = entity.prevZ + ((entity.getZ() - entity.prevZ) * partial) - minecraft.getEntityRenderDispatcher().camera.getPos().z;
        return new Vec3d(x, y, z);
    }

    public Vec3d getRenderPosition(BlockPos blockPos, MatrixStack matrixStack) {
        Matrix4f matrix = matrixStack.peek().getModel();
        double minX = blockPos.getX() - minecraft.getEntityRenderDispatcher().camera.getPos().x;
        double minY = blockPos.getY() - minecraft.getEntityRenderDispatcher().camera.getPos().y;
        double minZ = blockPos.getZ() - minecraft.getEntityRenderDispatcher().camera.getPos().z;
        Vector4f vector4f = new Vector4f((float)minX, (float)minY, (float)minZ, 1.f);
        vector4f.transform(matrix);
        return new Vec3d(vector4f.getX(), vector4f.getY(), vector4f.getZ());
    }

    public Vec3d getRenderPosition(Vec3d vec3d, MatrixStack matrixStack) {
        Matrix4f matrix = matrixStack.peek().getModel();
        double minX = vec3d.getX() - minecraft.getEntityRenderDispatcher().camera.getPos().x;
        double minY = vec3d.getY() - minecraft.getEntityRenderDispatcher().camera.getPos().y;
        double minZ = vec3d.getZ() - minecraft.getEntityRenderDispatcher().camera.getPos().z;
        Vector4f vector4f = new Vector4f((float)minX, (float)minY, (float)minZ, 1.f);
        vector4f.transform(matrix);
        return new Vec3d(vector4f.getX(), vector4f.getY(), vector4f.getZ());
    }
}