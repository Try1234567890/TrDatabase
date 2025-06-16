package me.tr.trDatabase.query.params.functions.date;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.Column;

public class Date extends AbstractDate {
    private String column;

    public String column() {
        return column;
    }

    public Date column(String column) {
        this.column = column;
        return this;
    }

    public Date column(Column column) {
        this.column = column.name();
        return this;
    }

    @Override
    public String toSql() {
        if (Utility.isNull(column)) {
            TrDatabase.instance().logger().error("Column cannot be null in DATE(Column) constraint");
            return "";
        }
        return "DATE(" + column() + ")";
    }
}
