package me.tr.trDatabase.query.params.functions.math;

import me.tr.trDatabase.TrDatabase;

public class Ceil extends Math {
    @Override
    public String toSql() {
        if (number() == -1) {
            TrDatabase.instance().logger().error("Number cannot be null in CEIL(int) constraint");
            return "";
        }
        return "CEIL(" + number() + ")";
    }
}
