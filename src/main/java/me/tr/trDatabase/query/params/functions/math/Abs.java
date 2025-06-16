package me.tr.trDatabase.query.params.functions.math;

import me.tr.trDatabase.TrDatabase;

public class Abs extends Math {
    @Override
    public String toSql() {
        if (number() == -1) {
            TrDatabase.instance().logger().error("Number cannot be null in ABS(int) constraint");
            return "";
        }
        return "ABS(" + number() + ")";
    }
}
