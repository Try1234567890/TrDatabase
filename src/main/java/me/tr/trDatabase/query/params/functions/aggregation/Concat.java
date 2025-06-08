package me.tr.trDatabase.query.params.functions.aggregation;

import me.tr.trDatabase.query.params.Column;

public class Concat extends Aggregation {

    public Concat column(String column) {
        super.column(Column.of(column));
        return this;
    }

    public Concat column(Column column) {
        super.column(column);
        return this;
    }


    @Override
    public String toSql() {
        return "CONCAT(" + column().name() + ")";
    }
}
