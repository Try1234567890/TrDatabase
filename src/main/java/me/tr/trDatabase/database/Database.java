package me.tr.trDatabase.database;

import me.tr.trDatabase.TrDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {
    TrDatabase main = TrDatabase.main();
    void connect() throws SQLException;
    void disconnect() throws SQLException;
    boolean isConnected() throws SQLException;
    Connection getConnection();

}
