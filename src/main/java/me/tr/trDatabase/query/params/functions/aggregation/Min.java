package me.tr.trDatabase.query.params.functions.aggregation;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;

public class Min extends Aggregation {
    @Override
    public String toSql() {
        if (Utility.isNull(column())) {
            TrDatabase.instance().logger().error("Column cannot be null or empty in min(Column) functions");
            return "";
        }
        return "MIN(" + column() + ")";
    }
}
