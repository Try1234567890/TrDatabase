package me.tr.trDatabase.query.params.functions.math;

public class Floor extends Math {
    @Override
    public String toSql() {
        return "FLOOR(" + number() + ")";
    }
}
