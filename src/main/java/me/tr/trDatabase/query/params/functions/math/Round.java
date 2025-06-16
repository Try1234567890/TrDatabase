package me.tr.trDatabase.query.params.functions.math;

import me.tr.trDatabase.TrDatabase;

public class Round extends Math {
    private int precision;

    public int precision() {
        return precision;
    }

    public Round precision(int precision) {
        this.precision = precision;
        return this;
    }

    @Override
    public String toSql() {
        if (number() == -1 || precision() == -1) {
            TrDatabase.instance().logger().error("Number and precision cannot be null in ROUND(int, int) constraint and " + (number() == -1 ? "Number" : "Precision") + " is null");
            return "";
        }
        return "ROUND(" + number() + ", " + precision() + ")";
    }
}
