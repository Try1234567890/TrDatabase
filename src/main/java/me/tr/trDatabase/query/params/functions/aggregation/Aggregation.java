package me.tr.trDatabase.query.params.functions.aggregation;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.Column;
import me.tr.trDatabase.query.params.functions.Function;

public abstract class Aggregation implements Function {
    private String column;

    protected Aggregation() {

    }

    public String column() {
        return column;
    }

    public Aggregation column(String column) {
        this.column = column;
        return this;
    }


    public Aggregation column(Column column) {
        this.column = column.name();
        return this;
    }
}
