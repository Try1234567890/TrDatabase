package me.tr.trDatabase.query.params.functions.math;

public class Ceil extends Math {
    @Override
    public String toSql() {
        return "CEIL(" + number() + ")";
    }
}
