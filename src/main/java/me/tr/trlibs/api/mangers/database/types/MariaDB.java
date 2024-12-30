package me.tr.trlibs.api.mangers.database.types;


import me.tr.trlibs.api.logger.Logger;
import me.tr.trlibs.api.mangers.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDB implements Database {
    private Connection conn;
    String host;
    int port;
    String name;
    String user;
    String password;

    public MariaDB(String host, int port, String name, String user, String password) {
        this.host = host;
        this.port = port;
        this.name = name;
        this.user = user;
        this.password = password;
    }

    @Override
    public void connect() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            main.getLogger().log("MariaDB Driver not found!", Logger.Levels.ERROR);
            return;
        }
        String URL = "jdbc:mariadb://" + host + ":" + port + "/" + name;
        conn = DriverManager.getConnection(URL, user, password);
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
