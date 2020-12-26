package ru.vsu.cs.course3.provider;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    private static Map<Class<?>, Object> services = new HashMap<>();

    public static <T> void register(Class<T> type, T service) {
        if (!type.isInstance(service)) {
            throw new IllegalArgumentException();
        }
        services.put(type, service);
    }

    public static <T> T get(Class<T> type) {
        return (T)services.get(type);
    }



}
