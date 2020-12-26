package ru.vsu.cs.course3.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MockDatabaseProvider {
    private static Map<Class<?>, Map> tables = new HashMap<>();
    private static Map<Class<?>, AtomicInteger> ids = new HashMap<>();

    public static <T> void addTable(Class<T> type) {
        tables.put(type, new HashMap<Integer, T>());
        ids.put(type, new AtomicInteger(12345));
    }

    public static <T> Map<Integer, T> get(Class<T> type) {
        return (Map<Integer, T>) tables.get(type);
    }

    public static <T> int getNextId(Class<T> type) {
        return ids.get(type).incrementAndGet();
    }

}
