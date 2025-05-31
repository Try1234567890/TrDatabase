package me.tr.trDatabase.database.query.list;

import me.tr.trDatabase.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertBuilder extends QueryBuilder {
    private String table;
    private List<String> columns = new ArrayList<>();
    private List<Object> values = new ArrayList<>();

    public InsertBuilder(Connection connection) {
        super(connection);
    }

    public InsertBuilder table(String table) {
        this.table = table;
        return this;
    }

    public InsertBuilder columns(String... columns) {
        this.columns.addAll(List.of(columns));
        return this;
    }

    public InsertBuilder values(Object... values) {
        this.values.addAll(List.of(values));
        return this;
    }

    public boolean execute() {
        if (Utility.isNull(table)) {
            main.logger().error("Table name cannot be null!");
            return false;
        }
        if (columns.isEmpty() || values.isEmpty()) {
            main.logger().error("Columns or values isn't specified and them cannot be null!");
            return false;
        }
        if (columns.size() != values.size()) {
            main.logger().error("Columns length and values length don't match.");
            return false;
        }
        StringBuilder query = new StringBuilder("INSERT INTO ")
                .append(table)
                .append(" (")
                .append(String.join(", ", columns))
                .append(") VALUES (")
                .append(String.join(", ", "?".repeat(values.size()).split("")))
                .append(")");
        try (PreparedStatement st = connection().prepareStatement(query.toString())) {
            for (int i = 0; i < values.size(); i++) {
                st.setObject(i + 1, values.get(i));
            }
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            main.logger().error("Error while executing query " + query + ": [ExceptionMessage]", e);
            return false;
        }
    }
}
