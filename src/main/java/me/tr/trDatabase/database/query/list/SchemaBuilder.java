package me.tr.trDatabase.database.query.list;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static me.tr.trDatabase.database.Database.main;

public class SchemaBuilder extends QueryBuilder {
    private String schema;

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
        try (Statement st = connection().createStatement()) {
            st.executeUpdate(schema);
        } catch (SQLException e) {
            main.logger().error("Error while executing schema: " + schema + " [ExceptionMessage]", e);
        }
    }
}
