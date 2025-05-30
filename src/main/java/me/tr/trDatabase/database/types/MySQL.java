package me.tr.trDatabase.database.types;


import me.tr.trDatabase.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL implements Database {
    private Connection conn;
    String host;
    int port;
    String name;
    String user;
    String password;

    public MySQL(String host, int port, String name, String user, String password) {
        this.host = host;
        this.port = port;
        this.name = name;
        this.user = user;
        this.password = password;
    }

    @Override
    public void connect() throws SQLException {
        main.databaseType(DBType.MYSQL);
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String URL = "jdbc:mariadb://" + host + ":" + port + "/" + name;
            conn = DriverManager.getConnection(URL, user, password);
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
