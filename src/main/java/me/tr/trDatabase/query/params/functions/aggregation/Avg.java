package me.tr.trDatabase.query.params.functions.aggregation;

public class Avg extends Aggregation {
    @Override
    public String toSql() {
        return "AVG(" + column().name() + ")";
    }
}
