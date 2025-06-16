package me.tr.trDatabase.query.params.functions.math;

import me.tr.trDatabase.TrDatabase;

public class Floor extends Math {
    @Override
    public String toSql() {
        if (number() == -1) {
            TrDatabase.instance().logger().error("Number cannot be null in FLOOR(int) constraint");
            return "";
        }
        return "FLOOR(" + number() + ")";
    }
}
