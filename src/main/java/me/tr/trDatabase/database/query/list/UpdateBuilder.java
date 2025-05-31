package me.tr.trDatabase.database.query.list;

import me.tr.trDatabase.Utility;
import me.tr.trDatabase.database.query.additions.Where;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateBuilder extends QueryBuilder {
    private String table;
    private List<String> columns = new ArrayList<>();
    private List<Object> values = new ArrayList<>();
    private Where where;

    public UpdateBuilder(Connection connection) {
        super(connection);
    }

    public UpdateBuilder table(String table) {
        this.table = table;
        return this;
    }

    public UpdateBuilder columns(String... columns) {
        this.columns.addAll(Arrays.stream(columns).toList());
        return this;
    }

    public UpdateBuilder values(Object... values) {
        this.values.addAll(List.of(values));
        return this;
    }

    public UpdateBuilder where(String where, Object... value) {
        this.where = new Where(where, value);
        return this;
    }

    public UpdateBuilder where(Where where) {
        this.where = where;
        return this;
    }

    public boolean execute() {
        if (Utility.isNull(table)) {
            main.logger().error("Table name cannot be null!");
            return false;
        }
        if (columns.isEmpty() || values.isEmpty()) {
            main.logger().error("Columns or values isn't specified and cannot be null!");
            return false;
        }
        if (columns.size() != values.size()) {
            main.logger().error("Columns length and values length don't match.");
            return false;
        }
        StringBuilder query = new StringBuilder("UPDATE ")
                .append(table)
                .append(" SET ")
                .append(String.join(", ", columns.stream()
                        .map(column -> column + " = ?")
                        .toList()));
        if (where != null)
            query.append(where.execute());
        try (PreparedStatement st = connection().prepareStatement(query.toString())) {
            int index = 1;
            for (Object value : values) {
                st.setObject(index++, value);
            }
            for (Object whereValue : where.values()) {
                st.setObject(index++, whereValue);
            }
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            main.logger().error("Error while executing query " + query + ": [ExceptionMessage]", e);
            return false;
        }
    }
}
