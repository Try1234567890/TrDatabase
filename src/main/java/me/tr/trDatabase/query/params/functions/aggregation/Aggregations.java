package me.tr.trDatabase.query.params.functions.aggregation;

import me.tr.trDatabase.query.params.Column;

public class Aggregations {
    private static final Aggregations instance = new Aggregations();

    public static Aggregations instance() {
        return instance;
    }

    private Aggregations() {
    }

    public Aggregation avg(Column column) {
        return new Avg().column(column);
    }

    public Aggregation avg(String column) {
        return new Avg().column(column);
    }

    public Aggregation count(Column column) {
        return new Count().column(column);
    }

    public Aggregation count(String column) {
        return new Count().column(column);
    }

    public Aggregation max(Column column) {
        return new Max().column(column);
    }

    public Aggregation max(String column) {
        return new Max().column(column);
    }

    public Aggregation min(Column column) {
        return new Min().column(column);
    }

    public Aggregation min(String column) {
        return new Min().column(column);
    }

    public Aggregation sum(Column column) {
        return new Sum().column(column);
    }

    public Aggregation sum(String column) {
        return new Sum().column(column);
    }
}
