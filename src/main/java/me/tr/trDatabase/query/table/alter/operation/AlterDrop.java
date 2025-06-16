package me.tr.trDatabase.query.table.alter.operation;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.Column;
import me.tr.trDatabase.query.table.alter.AlterTable;
import me.tr.trDatabase.query.table.alter.AlterType;

public class AlterDrop extends AlterTable {
    private String column;

    public AlterDrop() {
        super(AlterType.DROP);
    }

    public String column() {
        return column;
    }

    public AlterDrop column(String column) {
        this.column = column;
        return this;
    }

    public AlterDrop column(Column column) {
        this.column = column.name();
        return this;
    }

    @Override
    protected String get() {
        if (Utility.isNull(column)) {
            TrDatabase.instance().logger().error("Column cannot be null in ALTER TABLE DROP query.");
            return "";
        }
        return "COLUMN " + column;
    }

}
