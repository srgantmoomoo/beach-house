package me.srgantmoomoo.beachhouse.backend.util.math;

public enum ClientMathHelper {
    INSTANCE;

    public <T extends Comparable<T>> T clamp(T val, T min, T max) {
        return val.compareTo(min) < 0 ? min : val.compareTo(max) > 0 ? max : val;
    }
}