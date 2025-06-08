package me.tr.trDatabase.query.params.functions.math;

public class Abs extends Math {
    @Override
    public String toSql() {
        return "ABS(" + number() + ")";
    }
}
