package me.tr.trDatabase.query.table.alter.operation;

import me.tr.trDatabase.query.params.Column;
import me.tr.trDatabase.query.table.alter.Alter;
import me.tr.trDatabase.query.table.alter.AlterType;

public class Drop extends Alter {
    private String column;

    public Drop() {
        super(AlterType.DROP);
    }

    public String column() {
        return column;
    }

    public Drop column(String column) {
        this.column = column;
        return this;
    }

    public Drop column(Column column) {
        this.column = column.name();
        return this;
    }

    @Override
    protected String get() {
        return "COLUMN " + column;
    }

}
