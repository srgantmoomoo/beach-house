package me.srgantmoomoo.beachhouse.backend.util.math;

import net.minecraft.util.math.Matrix4f;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

public class Matrix4x4 {

    public float a00;
    public float a01;
    public float a02;
    public float a03;
    public float a10;
    public float a11;
    public float a12;
    public float a13;
    public float a20;
    public float a21;
    public float a22;
    public float a23;
    public float a30;
    public float a31;
    public float a32;
    public float a33;

    public Matrix4x4(FloatBuffer floatBuffer) {
        int offset = floatBuffer.position();
        this.a00 = floatBuffer.get(offset);
        this.a01 = floatBuffer.get(offset+1);
        this.a02 = floatBuffer.get(offset+2);
        this.a03 = floatBuffer.get(offset+3);
        this.a10 = floatBuffer.get(offset+4);
        this.a11 = floatBuffer.get(offset+5);
        this.a12 = floatBuffer.get(offset+6);
        this.a13 = floatBuffer.get(offset+7);
        this.a20 = floatBuffer.get(offset+8);
        this.a21 = floatBuffer.get(offset+9);
        this.a22 = floatBuffer.get(offset+10);
        this.a23 = floatBuffer.get(offset+11);
        this.a30 = floatBuffer.get(offset+12);
        this.a31 = floatBuffer.get(offset+13);
        this.a32 = floatBuffer.get(offset+14);
        this.a33 = floatBuffer.get(offset+15);
    }

    public Matrix4x4(float[] floats) {
        this.a00 = floats[0];
        this.a01 = floats[1];
        this.a02 = floats[2];
        this.a03 = floats[3];
        this.a10 = floats[4];
        this.a11 = floats[5];
        this.a12 = floats[6];
        this.a13 = floats[7];
        this.a20 = floats[8];
        this.a21 = floats[9];
        this.a22 = floats[10];
        this.a23 = floats[11];
        this.a30 = floats[12];
        this.a31 = floats[13];
        this.a32 = floats[14];
        this.a33 = floats[15];
    }

    public static Matrix4x4 copyFromColumnMajor(Matrix4f matrix4f) {
        try (MemoryStack memoryStack = MemoryStack.stackPush()) {
            FloatBuffer floatBuffer = memoryStack.mallocFloat(16);
            matrix4f.write(floatBuffer, false);
            return new Matrix4x4(floatBuffer);
        }
    }

    public Vector3D project(float x, float y, float z, int[] viewport, Vector3D winCoordsDest) {
        float invW = 1.0f / Math.fma(a03, x, Math.fma(a13, y, Math.fma(a23, z, a33)));
        float nx = Math.fma(a00, x, Math.fma(a10, y, Math.fma(a20, z, a30))) * invW;
        float ny = Math.fma(a01, x, Math.fma(a11, y, Math.fma(a21, z, a31))) * invW;
        float nz = Math.fma(a02, x, Math.fma(a12, y, Math.fma(a22, z, a32))) * invW;
        winCoordsDest.setX(Math.fma(Math.fma(nx, 0.5f, 0.5f), viewport[2], viewport[0]));
        winCoordsDest.setY(Math.fma(Math.fma(ny, 0.5f, 0.5f), viewport[3], viewport[1]));
        winCoordsDest.setZ(Math.fma(0.5f, nz, 0.5f));
        return winCoordsDest;
    }

    public Matrix4x4 mul(Matrix4x4 matrix4x4) {
        float nm00 = Math.fma(a00, matrix4x4.a00, Math.fma(a10, matrix4x4.a01, Math.fma(a20, matrix4x4.a02, a30 * matrix4x4.a03)));
        float nm01 = Math.fma(a01, matrix4x4.a00, Math.fma(a11, matrix4x4.a01, Math.fma(a21, matrix4x4.a02, a31 * matrix4x4.a03)));
        float nm02 = Math.fma(a02, matrix4x4.a00, Math.fma(a12, matrix4x4.a01, Math.fma(a22, matrix4x4.a02, a32 * matrix4x4.a03)));
        float nm03 = Math.fma(a03, matrix4x4.a00, Math.fma(a13, matrix4x4.a01, Math.fma(a23, matrix4x4.a02, a33 * matrix4x4.a03)));
        float nm10 = Math.fma(a00, matrix4x4.a10, Math.fma(a10, matrix4x4.a11, Math.fma(a20, matrix4x4.a12, a30 * matrix4x4.a13)));
        float nm11 = Math.fma(a01, matrix4x4.a10, Math.fma(a11, matrix4x4.a11, Math.fma(a21, matrix4x4.a12, a31 * matrix4x4.a13)));
        float nm12 = Math.fma(a02, matrix4x4.a10, Math.fma(a12, matrix4x4.a11, Math.fma(a22, matrix4x4.a12, a32 * matrix4x4.a13)));
        float nm13 = Math.fma(a03, matrix4x4.a10, Math.fma(a13, matrix4x4.a11, Math.fma(a23, matrix4x4.a12, a33 * matrix4x4.a13)));
        float nm20 = Math.fma(a00, matrix4x4.a20, Math.fma(a10, matrix4x4.a21, Math.fma(a20, matrix4x4.a22, a30 * matrix4x4.a23)));
        float nm21 = Math.fma(a01, matrix4x4.a20, Math.fma(a11, matrix4x4.a21, Math.fma(a21, matrix4x4.a22, a31 * matrix4x4.a23)));
        float nm22 = Math.fma(a02, matrix4x4.a20, Math.fma(a12, matrix4x4.a21, Math.fma(a22, matrix4x4.a22, a32 * matrix4x4.a23)));
        float nm23 = Math.fma(a03, matrix4x4.a20, Math.fma(a13, matrix4x4.a21, Math.fma(a23, matrix4x4.a22, a33 * matrix4x4.a23)));
        float nm30 = Math.fma(a00, matrix4x4.a30, Math.fma(a10, matrix4x4.a31, Math.fma(a20, matrix4x4.a32, a30 * matrix4x4.a33)));
        float nm31 = Math.fma(a01, matrix4x4.a30, Math.fma(a11, matrix4x4.a31, Math.fma(a21, matrix4x4.a32, a31 * matrix4x4.a33)));
        float nm32 = Math.fma(a02, matrix4x4.a30, Math.fma(a12, matrix4x4.a31, Math.fma(a22, matrix4x4.a32, a32 * matrix4x4.a33)));
        float nm33 = Math.fma(a03, matrix4x4.a30, Math.fma(a13, matrix4x4.a31, Math.fma(a23, matrix4x4.a32, a33 * matrix4x4.a33)));
        return new Matrix4x4(new float[]{nm00, nm01, nm02, nm03, nm10, nm11, nm12, nm13, nm20, nm21, nm22, nm23, nm30, nm31, nm32, nm33});
    }
}