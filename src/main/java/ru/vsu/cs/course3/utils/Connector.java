package ru.vsu.cs.course3.utils;

import lombok.RequiredArgsConstructor;
import ru.vsu.cs.course3.exception.ApplicationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RequiredArgsConstructor
public class Connector {
    private Connection connection;
    private final String url;
    private final String login;
    private final String password;

    public Connection getConnection() {
        try {
            if (connection == null || !connection.isClosed()) {
                connection = DriverManager.getConnection(url, login, password);
            }
        } catch (SQLException e) {
            throw new ApplicationException("Произошла ошибка при создании подключения", e);
        }
        return connection;
    }
}
