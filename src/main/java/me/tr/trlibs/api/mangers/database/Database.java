package me.tr.trlibs.api.mangers.database;

import me.tr.trlibs.TrLibs;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {
    TrLibs main = TrLibs.getMain();
    void connect() throws SQLException;
    void disconnect() throws SQLException;
    boolean isConnected() throws SQLException;
    Connection getConnection();

}
