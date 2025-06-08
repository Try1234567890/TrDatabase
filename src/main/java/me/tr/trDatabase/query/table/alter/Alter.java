package me.tr.trDatabase.query.table.alter;

import me.tr.trDatabase.query.Query;
import me.tr.trDatabase.query.params.Column;
import me.tr.trDatabase.query.table.data.ColumnData;
import me.tr.trDatabase.query.table.alter.operation.Add;
import me.tr.trDatabase.query.table.alter.operation.Drop;
import me.tr.trDatabase.query.table.alter.operation.Modify;

public abstract class Alter implements Query {
    private String name;
    private AlterType type;
    private String params;

    protected Alter(AlterType type) {
        this.type = type;
    }

    public String name() {
        return name;
    }

    public Alter name(String name) {
        this.name = name;
        return this;
    }

    public AlterType type() {
        return type;
    }

    public Alter type(AlterType type) {
        this.type = type;
        return this;
    }

    private String params() {
        return params;
    }

    protected Alter params(String params) {
        this.params = params;
        return this;
    }

    public static Alter add(String name, ColumnData column) {
        return new Add().column(column).name(name);
    }

    public static Alter drop(String name, String column) {
        return new Drop().column(column).name(name);
    }

    public static Alter drop(String name, Column column) {
        return new Drop().column(column).name(name);
    }

    public static Alter modify(String name, ColumnData column) {
        return new Modify().column(column).name(name);
    }

    @Override
    public String toSql() {
        return "ALTER TABLE " + name + ' ' + type.name() + ' ' + get();
    }

    protected abstract String get();
}
