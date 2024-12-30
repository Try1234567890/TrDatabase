package me.tr.trlibs.api.mangers.database.types;


import me.tr.trlibs.api.mangers.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite implements Database {
    private Connection conn;
    private String path;

    public SQLite(String path) {
        this.path = path;
    }

    @Override
    public void connect() throws SQLException {
        String URL = "jdbc:sqlite:" + path;
        conn = DriverManager.getConnection(URL);
    }

    @Override
    public void disconnect() throws SQLException {
        conn.close();
    }

    @Override
    public boolean isConnected() throws SQLException {
        return conn != null && !conn.isClosed();
    }

    @Override
    public Connection getConnection() {
        return conn;
    }
}
