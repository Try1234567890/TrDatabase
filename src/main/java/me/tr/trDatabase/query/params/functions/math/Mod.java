package me.tr.trDatabase.query.params.functions.math;

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
        return "MOD(" + number() + ", " + divisor() + ")";
    }
}
