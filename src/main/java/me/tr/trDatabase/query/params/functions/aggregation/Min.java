package me.tr.trDatabase.query.params.functions.aggregation;

public class Min extends Aggregation {
    @Override
    public String toSql() {
        return "MIN(" + column().name() + ")";
    }
}
