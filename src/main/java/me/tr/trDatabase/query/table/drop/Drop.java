package me.tr.trDatabase.query.table.drop;

import me.tr.trDatabase.query.Query;

public class Drop implements Query {
    private String drop = "DROP";
    private String name;

    public Drop truncate() {
        this.drop = "TRUNCATE";
        return this;
    }

    public String name() {
        return name;
    }

    public Drop name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toSql() {
        return drop + " TABLE " + name;
    }
}
