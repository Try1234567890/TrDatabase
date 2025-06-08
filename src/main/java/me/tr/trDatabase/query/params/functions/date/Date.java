package me.tr.trDatabase.query.params.functions.date;

import me.tr.trDatabase.query.params.Column;

public class Date extends AbstractDate {
    private Column column;

    public Column column() {
        return column;
    }

    public Date column(String column) {
        this.column = Column.of(column);
        return this;
    }

    public Date column(Column column) {
        this.column = column;
        return this;
    }

    @Override
    public String toSql() {
        return "DATE(" + column().name() + ")";
    }
}
