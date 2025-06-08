package me.tr.trDatabase.query.params.functions.math;

public class Sqrt extends Math {
    @Override
    public String toSql() {
        return "SQRT(" + number() + ")";
    }
}
