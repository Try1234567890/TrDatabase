package me.tr.trDatabase.query.params.functions.math;

import me.tr.trDatabase.TrDatabase;

public class Mod extends Math {
    private int divisor;

    public int divisor() {
        return divisor;
    }

    public Mod divisor(int divisor) {
        this.divisor = divisor;
        return this;
    }

    @Override
    public String toSql() {
        if (number() == -1 || divisor() == -1) {
            TrDatabase.instance().logger().error("Number and divisor cannot be null in MOD(int, int) constraint and " + (number() == -1 ? "Number" : "Divisor") + " is null");
            return "";
        }
        return "MOD(" + number() + ", " + divisor() + ")";
    }
}
