package me.tr.trDatabase.query.params.functions.aggregation;

public class Max extends Aggregation {
    @Override
    public String toSql() {
        return "MAX(" + column().name() + ")";
    }
}
