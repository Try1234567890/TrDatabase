package me.tr.trDatabase.query.params.functions.aggregation;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;

public class Concat extends Aggregation {


    @Override
    public String toSql() {
        if (Utility.isNull(column())) {
            TrDatabase.instance().logger().error("Column cannot be null or empty in concat(Column) functions");
            return "";
        }
        return "CONCAT(" + column() + ")";
    }
}
