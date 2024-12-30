package me.tr.trlibs.api.mangers.database;


import me.tr.trlibs.TrLibs;
import me.tr.trlibs.api.logger.Logger;

import java.sql.*;
import java.util.*;

public class StorageManager {
    private final TrLibs main = TrLibs.getMain();
    Connection connection;
    Database database;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
        connection = database.getConnection();
    }

    public void disconnect() {
        try {
            if (isConnected()) {
                getDatabase().disconnect();
            }
        } catch (SQLException e) {
            main.getLogger().log("Error while disconnecting from database: " + e.getMessage(), Logger.Levels.ERROR);
        }
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public void executeSQLSchema(String schema) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(schema);
        } catch (SQLException e) {
            main.getLogger().log("Error while executing schema " + schema + ": " + e.getMessage(), Logger.Levels.ERROR);
        }
    }

    public Map<String, Object> selectIndex(String table, String[] columns, String where, Object[] whereValues, int index) {
        List<Map<String, Object>> results = select(table, columns, where, whereValues);
        return results.isEmpty() ? null : results.get(index);
    }


    public Map<String, Object> selectLast(String table, String[] columns, String where, Object[] whereValues) {
        List<Map<String, Object>> results = select(table, columns, where, whereValues);
        return results.isEmpty() ? null : results.get(0);
    }


    public Map<String, Object> selectFirst(String table, String[] columns, String where, Object[] whereValues) {
        List<Map<String, Object>> results = select(table, columns, where, whereValues);
        return results.isEmpty() ? null : results.get(0);
    }

    public Map<String, Object> selectIndex(String table, String[] columns, int index) {
        List<Map<String, Object>> results = select(table, columns);
        return results.isEmpty() ? null : results.get(index);
    }


    public Map<String, Object> selectLast(String table, String[] columns) {
        List<Map<String, Object>> results = select(table, columns);
        return results.isEmpty() ? null : results.get(results.size() - 1);
    }


    public Map<String, Object> selectFirst(String table, String[] columns) {
        List<Map<String, Object>> results = select(table, columns);
        return results.isEmpty() ? null : results.get(0);
    }


    public List<Map<String, Object>> select(String table, String[] columns) {
        List<Map<String, Object>> results = new ArrayList<>();
        String query = "SELECT " + String.join(", ", columns) + " FROM " + table;
        try (PreparedStatement st = connection.prepareStatement(query)) {
            try (ResultSet rs = st.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnName(i), rs.getObject(i));
                    }
                    results.add(row);
                }
            }
        } catch (SQLException e) {
            main.getLogger().log("Error while executing query " + query + ": " + e.getMessage(), Logger.Levels.ERROR);
            return results;
        }
        return results;
    }

    /**
     * Try obtaining values from the database.
     *
     * @param table       Table to modify.
     * @param columns     Columns to modify.
     * @param where       Conditions to follow.
     * @param whereValues Conditions values.
     */
    public List<Map<String, Object>> select(String table, String[] columns, String where, Object[] whereValues) {
        List<Map<String, Object>> results = new ArrayList<>();
        String query = "SELECT " + String.join(", ", columns) + " FROM " + table + " WHERE " + where;
        try (PreparedStatement st = connection.prepareStatement(query)) {
            for (int i = 0; i < whereValues.length; i++) {
                st.setObject(i + 1, whereValues[i]);
            }
            try (ResultSet rs = st.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnName(i), rs.getObject(i));
                    }
                    results.add(row);
                }
            }
        } catch (SQLException e) {
            main.getLogger().log("Error while executing query " + query + ": " + e.getMessage(), Logger.Levels.ERROR);
            return results;
        }
        return results;
    }

    /**
     * Try deleting values in the database.
     *
     * @param table Table to modify.
     * @param where Conditions to follow.
     */
    public boolean delete(String table, String where, Object[] whereValues) {
        String query = "DELETE FROM " + table + " WHERE " + where;
        try (PreparedStatement st = connection.prepareStatement(query)) {
            for (int i = 0; i < whereValues.length; i++) {
                st.setObject(i + 1, whereValues[i]);
            }
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            main.getLogger().log("Error while executing query " + query + ": " + e.getMessage(), Logger.Levels.ERROR);
            return false;
        }
    }

    /**
     * Try updating values in the database;
     *
     * @param table       Table to modify.
     * @param columns     Columns to modify.
     * @param values      New Values to insert.
     * @param where       Conditions to follow.
     * @param whereValues Conditions values.
     */
    public boolean update(String table, String[] columns, Object[] values, String where, Object[] whereValues) {
        if (columns.length == 0) {
            main.getLogger().log("Columns isn't specified and cannot be null!", Logger.Levels.ERROR);
            return false;
        }
        if (columns.length != values.length) {
            main.getLogger().log("Columns length and values length don't match.", Logger.Levels.ERROR);
            return false;
        }
        StringBuilder query = new StringBuilder("UPDATE ")
                .append(table)
                .append(" SET ")
                .append(String.join(", ", Arrays.stream(columns)
                        .map(column -> column + " = ?")
                        .toArray(String[]::new)))
                .append(" WHERE ")
                .append(where);
        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            int index = 1;
            for (Object value : values) {
                st.setObject(index++, value);
            }
            for (Object whereValue : whereValues) {
                st.setObject(index++, whereValue);
            }
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            main.getLogger().log("Error while executing query " + query + ": " + e.getMessage(), Logger.Levels.ERROR);
            return false;
        }
    }

    /**
     * Try inserting new values in the database.
     *
     * @param table   Table to modify.
     * @param columns Columns to modifu.
     * @param values  New Values to Insert.
     */
    public boolean insert(String table, String[] columns, Object[] values) {
        if (columns.length == 0) {
            main.getLogger().log("Columns isn't specified and cannot be null!", Logger.Levels.ERROR);
            return false;
        }
        if (columns.length != values.length) {
            main.getLogger().log("Columns length and values length don't match.", Logger.Levels.ERROR);
            return false;
        }
        StringBuilder query = new StringBuilder("INSERT INTO ")
                .append(table)
                .append(" (")
                .append(String.join(", ", columns))
                .append(") VALUES (")
                .append(String.join(", ", "?".repeat(values.length).split("")))
                .append(")");
        try (PreparedStatement st = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < values.length; i++) {
                st.setObject(i + 1, values[i]);
            }
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            main.getLogger().log("Error while executing query " + query + ": " + e.getMessage(), Logger.Levels.ERROR);
            return false;
        }
    }
}
