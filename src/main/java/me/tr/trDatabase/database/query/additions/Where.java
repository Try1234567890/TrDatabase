package me.tr.trDatabase.database.query.additions;

import java.util.List;

public class Where {
    private String where;
    private List<Object> values;

    public Where(String where, Object... values) {
        this.where = where;
        this.values = List.of(values);
    }

    public String where() {
        return where;
    }

    public Where where(String where) {
        this.where = where;
        return this;
    }

    public List<Object> values() {
        return values;
    }

    public Where values(Object... values) {
        this.values = List.of(values);
        return this;
    }

    @Override
    public String toString() {
        return where;
    }
}
