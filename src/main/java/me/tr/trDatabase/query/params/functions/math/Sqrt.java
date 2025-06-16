package me.tr.trDatabase.query.params.functions.math;

import me.tr.trDatabase.TrDatabase;

public class Sqrt extends Math {
    @Override
    public String toSql() {
        if (number() == -1) {
            TrDatabase.instance().logger().error("Number cannot be null in SQRT(int) constraint");
            return "";
        }
        return "SQRT(" + number() + ")";
    }
}
