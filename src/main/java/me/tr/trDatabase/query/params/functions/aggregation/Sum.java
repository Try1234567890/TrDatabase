package me.tr.trDatabase.query.params.functions.aggregation;

public class Sum extends Aggregation {
    @Override
    public String toSql() {
        return "SUM(" + column().name() + ")";
    }
}
