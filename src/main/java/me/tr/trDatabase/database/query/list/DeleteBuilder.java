package me.tr.trDatabase.database.query.list;

import me.tr.trDatabase.Utility;
import me.tr.trDatabase.database.query.additions.Where;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBuilder extends QueryBuilder {
    private String table;
    private Where where;


    public DeleteBuilder(Connection connection) {
        super(connection);
    }

    public DeleteBuilder table(String table) {
        this.table = table;
        return this;
    }

    public DeleteBuilder where(String where, Object... value) {
        this.where = new Where(where, value);
        return this;
    }

    public DeleteBuilder where(Where where) {
        this.where = where;
        return this;
    }

    public boolean execute() {
        if (Utility.isNull(table)) {
            main.logger().error("Table name cannot be null!");
            return false;
        }
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ")
                .append(table);
        if (where != null) query.append(where.get());
        try (PreparedStatement st = connection().prepareStatement(query.toString())) {
            for (int i = 0; i < where.values().size(); i++) {
                st.setObject(i + 1, where.values().get(i));
            }
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            main.logger().error("Error while executing query " + query + ": [ExceptionMessage]", e);
            return false;
        }
    }
}
