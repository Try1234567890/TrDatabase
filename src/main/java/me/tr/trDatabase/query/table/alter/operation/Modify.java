package me.tr.trDatabase.query.table.alter.operation;

import me.tr.trDatabase.query.table.data.ColumnData;
import me.tr.trDatabase.query.table.alter.Alter;
import me.tr.trDatabase.query.table.alter.AlterType;

public class Modify extends Alter {
    private ColumnData column;

    public Modify() {
        super(AlterType.MODIFY);
    }

    public ColumnData column() {
        return column;
    }

    public Modify column(ColumnData column) {
        this.column = column;
        return this;
    }

    @Override
    protected String get() {
        return "COLUMN " + column.toSql();
    }
}
