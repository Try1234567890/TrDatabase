package me.tr.trDatabase.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {

    void connect() throws SQLException;
    void disconnect() throws SQLException;
    boolean isConnected() throws SQLException;
    Connection getConnection();

}
