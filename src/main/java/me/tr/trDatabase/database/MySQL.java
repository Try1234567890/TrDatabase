package me.tr.trDatabase.database;


import me.tr.trDatabase.TrDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class represents a MySQL database.
 */
public class MySQL implements Database {
    private final TrDatabase main = TrDatabase.instance();
    private Connection conn;
    private final String host;
    private final int port;
    private final String name;
    private final String user;
    private final String pass;

    /**
     * Create a new instance of {@link MySQL}
     *
     * @param host Address to use for connection (127.0.0.1)
     * @param port Port to use for connection (3306)
     * @param name Database name to use for connection (test)
     * @param user User to use for connection (root)
     * @param pass User's password to log-in. (ASecurePassword)
     */
    public MySQL(String host, int port, String name, String user, String pass) {
        this.host = host;
        this.port = port;
        this.name = name;
        this.user = user;
        this.pass = pass;
    }

    @Override
    public void connect() throws SQLException {
        main.dbType(DBType.MYSQL);
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://" + host + ":" + port + "/" + name;
            conn = DriverManager.getConnection(url, user, pass);
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
