package me.srgantmoomoo.beachhouse.backend.events;

import me.srgantmoomoo.bedroom.api.event.Event;
import net.minecraft.client.util.math.MatrixStack;

public class EventRender3D extends Event {

    private float partialTicks;
    private MatrixStack matrixStack;

    public EventRender3D(MatrixStack matrixStack, float partialTicks2) {
        this.partialTicks = partialTicks2;
        this.matrixStack = matrixStack;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public MatrixStack getMatrixStack() {
        return matrixStack;
    }

    public static class EventRender3DNoBob extends Event {

        private float partialTicks;
        private MatrixStack matrixStack;

        public EventRender3DNoBob(MatrixStack matrixStack, float partialTicks2) {
            this.partialTicks = partialTicks2;
            this.matrixStack = matrixStack;
        }

        public float getPartialTicks() {
            return partialTicks;
        }

        public MatrixStack getMatrixStack() {
            return matrixStack;
        }

    }
}