package me.tr.trDatabase.database;


import me.tr.trDatabase.TrDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite implements Database {
    private final TrDatabase main = TrDatabase.instance();
    private Connection conn;
    private final String path;

    public SQLite(String path) {
        this.path = path;
    }

    @Override
    public void connect() throws SQLException {
        main.dbType(DBType.SQLITE);
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
