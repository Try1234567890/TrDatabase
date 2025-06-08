package me.tr.trDatabase.query.params.where;

import me.tr.trDatabase.query.params.Column;

import java.util.List;

public class LessThanOrEquals extends Condition {
    private String column;
    private Object value;

    protected LessThanOrEquals() {

    }

    public LessThanOrEquals column(String column) {
        this.column = column;
        return this;
    }

    public LessThanOrEquals column(Column column) {
        this.column = column.toSql();
        return this;
    }

    public LessThanOrEquals value(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public String toSql() {
        return column + " <= ?";
    }

    @Override
    public List<Object> parameters() {
        return List.of(value);
    }

    @Override
    public String toString() {
        return "LessThanOrEquals{" +
                "column='" + column + '\'' +
                ", value=" + value +
                '}';
    }
}
