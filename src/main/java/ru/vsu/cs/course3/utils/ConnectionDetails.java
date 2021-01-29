package ru.vsu.cs.course3.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionDetails {
    public static final String DB_URL = System.getenv("DB_URL");
    public static final String DB_LOGIN = System.getenv("DB_LOGIN");
    public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
}
