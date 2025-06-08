package me.tr.trDatabase.query.params.functions.aggregation;

public class Count extends Aggregation {


    @Override
    public String toSql() {
        return "COUNT(" + column().name() + ")";
    }
}
