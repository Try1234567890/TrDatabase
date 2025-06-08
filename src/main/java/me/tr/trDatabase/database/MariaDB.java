package me.tr.trDatabase.database;


import me.tr.trDatabase.TrDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDB implements Database {
    private final TrDatabase main = TrDatabase.instance();
    private Connection conn;
    private final String host;
    private final int port;
    private final String name;
    private final String user;
    private final String password;

    public MariaDB(String host, int port, String name, String user, String password) {
        this.host = host;
        this.port = port;
        this.name = name;
        this.user = user;
        this.password = password;
    }

    @Override
    public void connect() throws SQLException {
        main.dbType(DBType.MARIADB);
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String URL = "jdbc:mariadb://" + host + ":" + port + "/" + name;
            conn = DriverManager.getConnection(URL, user, password);
        } catch (ClassNotFoundException e) {
            main.logger().error("MariaDB Driver not found!");
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
