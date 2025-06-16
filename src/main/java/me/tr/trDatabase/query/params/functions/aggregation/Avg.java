package me.tr.trDatabase.query.params.functions.aggregation;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;

public class Avg extends Aggregation {
    @Override
    public String toSql() {
        if (Utility.isNull(column())) {
            TrDatabase.instance().logger().error("Column cannot be null or empty in avg(Column) functions");
            return "";
        }
        return "AVG(" + column() + ")";
    }
}
