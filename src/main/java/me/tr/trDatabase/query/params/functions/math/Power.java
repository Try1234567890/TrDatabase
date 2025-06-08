package me.tr.trDatabase.query.params.functions.math;

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
        return "POWER(" + number() + ", " + power() + ")";
    }
}
