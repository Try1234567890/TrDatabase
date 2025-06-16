package me.tr.trDatabase.query.params.functions.math;

import me.tr.trDatabase.TrDatabase;

public class Power extends Math {
    private int power;

    public int power() {
        return power;
    }

    public Power power(int power) {
        this.power = power;
        return this;
    }


    @Override
    public String toSql() {

        if (number() == -1 || power() == -1) {
            TrDatabase.instance().logger().error("Number and power cannot be null in POWER(int, int) constraint and " + (number() == -1 ? "Number" : "Power") + " is null");
            return "";
        }
        return "POWER(" + number() + ", " + power() + ")";
    }
}
