package me.tr.trDatabase.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This interface specifies base method for databases work flow.
 */
public interface Database {

    /**
     * Connect to the database
     *
     * @throws SQLException if an error occurs while connecting.
     */
    void connect() throws SQLException;

    /**
     * Disconnect from the database
     *
     * @throws SQLException if an error occurs while disconnecting.
     */
    void disconnect() throws SQLException;

    /**
     * Check if connection to the database is open and work correctly.
     *
     * @return true if connection is OK, otherwise false.
     * @throws SQLException if an error occurs while checking connection status.
     */
    boolean isConnected() throws SQLException;

    /**
     * Get the current connection established with the database.
     *
     * @return The {@link Connection} established with the database.
     */
    Connection getConnection();

}
