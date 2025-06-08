package me.tr.trDatabase.query.params.functions.math;

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
        return "ROUND(" + number() + ", " + precision() + ")";
    }
}
