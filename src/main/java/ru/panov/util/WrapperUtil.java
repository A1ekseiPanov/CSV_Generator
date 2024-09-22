package ru.panov.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Утилитный класс для работы с типами-обертками.
 */
public final class WrapperUtil {
    private WrapperUtil() {
    }

    private static final Map<Class<?>, Class<?>> WRAPPER_TYPE_MAP;

    static {
        WRAPPER_TYPE_MAP = new HashMap<>(16);
        WRAPPER_TYPE_MAP.put(Integer.class, int.class);
        WRAPPER_TYPE_MAP.put(Byte.class, byte.class);
        WRAPPER_TYPE_MAP.put(Character.class, char.class);
        WRAPPER_TYPE_MAP.put(Boolean.class, boolean.class);
        WRAPPER_TYPE_MAP.put(Double.class, double.class);
        WRAPPER_TYPE_MAP.put(Float.class, float.class);
        WRAPPER_TYPE_MAP.put(Long.class, long.class);
        WRAPPER_TYPE_MAP.put(Short.class, short.class);
        WRAPPER_TYPE_MAP.put(Void.class, void.class);
    }

    /**
     * Проверяет, является ли переданный объект типом-оберткой примитивного типа.
     *
     * @param source объект, который необходимо проверить
     * @return {@code true}, если объект является типом-оберткой примитивного типа, {@code false} в противном случае
     */
    public static boolean isPrimitiveType(Object source) {
        return WRAPPER_TYPE_MAP.containsKey(source.getClass());
    }
}