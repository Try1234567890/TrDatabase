package me.tr.trDatabase.query.table.alter.operation;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.query.table.data.ColumnData;
import me.tr.trDatabase.query.table.alter.AlterTable;
import me.tr.trDatabase.query.table.alter.AlterType;

public class AlterModify extends AlterTable {
    private ColumnData column;

    public AlterModify() {
        super(AlterType.MODIFY);
    }

    public ColumnData column() {
        return column;
    }

    public AlterModify column(ColumnData column) {
        this.column = column;
        return this;
    }

    @Override
    protected String get() {
        if (column == null) {
            TrDatabase.instance().logger().error("Column cannot be null in ALTER TABLE MODIFY query.");
            return "";
        }
        return "COLUMN " + column.toSql();
    }
}
