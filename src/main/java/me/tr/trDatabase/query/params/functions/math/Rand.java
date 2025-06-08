package me.tr.trDatabase.query.params.functions.math;

public class Rand extends Math {
    @Override
    public String toSql() {
        return "RAND()";
    }
}
