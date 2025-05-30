package me.tr.trDatabase.database.query.additions;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.database.types.DBType;

import java.util.Objects;

public class Limit {
    private int limit;
    private int offset = 0;

    public Limit(int limit) {
        this.limit = limit;
    }

    public Limit(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int limit() {
        return limit;
    }

    public Limit limit(int limit) {
        this.limit = limit;
        return this;
    }

    public int offset() {
        return offset;
    }

    public Limit offset(int offset) {
        this.offset = offset;
        return this;
    }

    public String execute() {
        if (Objects.requireNonNull(TrDatabase.main().databaseType()) == DBType.MYSQL) {
            return " LIMIT " + limit + ", " + offset;
        }
        return " LIMIT " + offset + ", " + limit;
    }
}
