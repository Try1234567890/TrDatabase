package me.tr.trDatabase.database.query.list;

import me.tr.trDatabase.TrDatabase;

import java.sql.Connection;

public abstract class QueryBuilder {
    protected final TrDatabase main = TrDatabase.main();
    private Connection connection;

    public QueryBuilder(Connection connection) {
        this.connection = connection;
    }

    public Connection connection() {
        return connection;
    }
}
