package me.tr.trDatabase.query.params.functions.date.components;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.functions.date.Date;

public class Day extends Date {


    @Override
    public String toSql() {
        if (Utility.isNull(column())) {
            TrDatabase.instance().logger().error("Column cannot be null or empty in day(Column) constraint.");
            return "";
        }
        return "DAY(" + column() + ")";
    }
}
