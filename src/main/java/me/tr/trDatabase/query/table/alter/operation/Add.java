package me.tr.trDatabase.query.table.alter.operation;

import me.tr.trDatabase.query.table.data.ColumnData;
import me.tr.trDatabase.query.table.alter.Alter;
import me.tr.trDatabase.query.table.alter.AlterType;

public class Add extends Alter {
    private ColumnData column;

    public Add() {
        super(AlterType.ADD);
    }

    public ColumnData column() {
        return column;
    }

    public Add column(ColumnData column) {
        this.column = column;
        return this;
    }

    @Override
    protected String get() {
        return column.toSql();
    }
}
