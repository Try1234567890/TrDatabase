package me.tr.trDatabase.query.table.alter.operation;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.query.table.data.ColumnData;
import me.tr.trDatabase.query.table.alter.AlterTable;
import me.tr.trDatabase.query.table.alter.AlterType;

public class AlterAdd extends AlterTable {
    private ColumnData column;

    public AlterAdd() {
        super(AlterType.ADD);
    }

    public ColumnData column() {
        return column;
    }

    public AlterAdd column(ColumnData column) {
        this.column = column;
        return this;
    }

    @Override
    protected String get() {
        if (column == null) {
            TrDatabase.instance().logger().error("Column cannot be null in ALTER TABLE ADD query.");
            return "";
        }
        return column.toSql();
    }
}
