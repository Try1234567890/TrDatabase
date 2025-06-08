package me.tr.trDatabase.query.params.limit;

public class Limit {
    private int limit = 0;
    private int offset = 0;

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
        return " LIMIT " + limit + (offset > 0 ? " OFFSET " + offset : "");
    }
}
