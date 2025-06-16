package me.tr.trDatabase.database;


import me.tr.trDatabase.TrDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This class represents an SQLite database.
 */
public class SQLite implements Database {
    private final TrDatabase main = TrDatabase.instance();
    private Connection conn;
    private final String path;

    /**
     * Create a new instance of {@link SQLite}
     *
     * @param path The path to the database.
     */
    public SQLite(String path) {
        this.path = path;
    }

    @Override
    public void connect() throws SQLException {
        main.dbType(DBType.SQLITE);
        try {
            Class.forName("org.sqlite.JDBC");
            if (!isSQLite()) {
                throw new SQLException("Object found at provided path is not an SQLite database: " + path);
            }
            String url = "jdbc:sqlite:" + path;
            conn = DriverManager.getConnection(url);
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

    private boolean isSQLite() {
        String ext = path.substring(path.lastIndexOf('.') + 1);
        return ext.equalsIgnoreCase("sqlite") ||
                ext.equalsIgnoreCase("db") ||
                ext.equalsIgnoreCase("sqlite3") ||
                ext.equalsIgnoreCase("db3") ||
                ext.equalsIgnoreCase("s3db");
    }
}
