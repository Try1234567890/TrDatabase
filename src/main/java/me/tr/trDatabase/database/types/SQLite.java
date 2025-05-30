package me.tr.trDatabase.database.types;


import me.tr.trDatabase.database.Database;

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
        main.databaseType(DBType.SQLITE);
        try {
            Class.forName("org.sqlite.JDBC");
            String URL = "jdbc:sqlite:" + path;
            conn = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            main.logger().error("MySQL Driver not found!");
            return;
        }
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
