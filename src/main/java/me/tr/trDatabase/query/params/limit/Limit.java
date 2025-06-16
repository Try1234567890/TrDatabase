package me.tr.trDatabase.query.params.limit;

import me.tr.trDatabase.TrDatabase;

public class Limit {
    private int limit;
    private int offset;

    private Limit(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public Limit limit(int limit) {
        this.limit = limit;
        return this;
    }

    public Limit offset(int offset) {
        this.offset = offset;
        return this;
    }

    public static Limit of(int limit, int offset) {
        return new Limit(limit, offset);
    }

    public static Limit of(int limit) {
        return new Limit(limit, 0);
    }

    public String toSql() {
        if (limit == -1) {
            TrDatabase.instance().logger().error("Limit cannot be null in Limit(int, int) clause.");
            return "";
        }
        return " LIMIT " + limit + (offset > 0 ? " OFFSET " + offset : "");
    }
}
