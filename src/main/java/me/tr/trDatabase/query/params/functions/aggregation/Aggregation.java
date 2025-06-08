package me.tr.trDatabase.query.params.functions.aggregation;

import me.tr.trDatabase.query.params.Column;
import me.tr.trDatabase.query.params.functions.Function;

public abstract class Aggregation implements Function {
    private Column column;

    protected Aggregation() {

    }

    public Column column() {
        return column;
    }

    public Aggregation column(String column) {
        this.column = Column.of(column);
        return this;
    }


    public Aggregation column(Column column) {
        this.column = column;
        return this;
    }
}
