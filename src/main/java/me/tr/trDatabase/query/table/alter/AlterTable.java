package me.tr.trDatabase.query.table.alter;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.Query;
import me.tr.trDatabase.query.params.Column;
import me.tr.trDatabase.query.table.alter.operation.AlterAdd;
import me.tr.trDatabase.query.table.alter.operation.AlterDrop;
import me.tr.trDatabase.query.table.alter.operation.AlterModify;
import me.tr.trDatabase.query.table.data.ColumnData;

public abstract class AlterTable implements Query {
    private String table;
    private final AlterType type;
    private String params;

    protected AlterTable(AlterType type) {
        this.type = type;
    }

    public String table() {
        return table;
    }

    protected AlterTable table(String table) {
        this.table = table;
        return this;
    }

    public AlterType type() {
        return type;
    }

    private String params() {
        return params;
    }

    protected AlterTable params(String params) {
        this.params = params;
        return this;
    }

    public static AlterTable add(String table, ColumnData column) {
        return new AlterAdd().column(column).table(table);
    }

    public static AlterTable drop(String table, String column) {
        return new AlterDrop().column(column).table(table);
    }

    public static AlterTable drop(String table, Column column) {
        return new AlterDrop().column(column).table(table);
    }

    public static AlterTable modify(String table, ColumnData column) {
        return new AlterModify().column(column).table(table);
    }

    @Override
    public String toSql() {
        if (Utility.isNull(table)) {
            TrDatabase.instance().logger().error("Table cannot be null in ALTER TABLE query.");
            return "";
        }
        if (Utility.isNull(get()))
            return "";
        return "ALTER TABLE " + table + ' ' + type.name() + ' ' + get();
    }

    protected abstract String get();
}
