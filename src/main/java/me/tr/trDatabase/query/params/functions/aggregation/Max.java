package me.tr.trDatabase.query.params.functions.aggregation;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;

public class Max extends Aggregation {
    @Override
    public String toSql() {
        if (Utility.isNull(column())) {
            TrDatabase.instance().logger().error("Column cannot be null or empty in max(Column) functions");
            return "";
        }
        return "MAX(" + column() + ")";
    }
}
