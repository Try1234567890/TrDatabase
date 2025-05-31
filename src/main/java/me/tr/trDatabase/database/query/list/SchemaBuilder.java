package me.tr.trDatabase.database.query.list;

import me.tr.trDatabase.Utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaBuilder extends QueryBuilder {
    private String schema;

    public SchemaBuilder(Connection connection) {
        super(connection);
    }

    public SchemaBuilder(Connection connection, String schema) {
        super(connection);
        this.schema = schema;
    }

    public String schema() {
        return schema;
    }

    public SchemaBuilder schema(String schema) {
        this.schema = schema;
        return this;
    }


    public void execute() {
        if (!Utility.isNull(schema)) {
            try (Statement st = connection().createStatement()) {
                st.executeUpdate(schema);
            } catch (SQLException e) {
                main.logger().error("Error while executing schema: " + schema + " [ExceptionMessage]", e);
            }
        }
    }
}
