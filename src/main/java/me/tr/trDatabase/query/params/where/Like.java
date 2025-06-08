package me.tr.trDatabase.query.params.where;

import me.tr.trDatabase.query.params.Column;

import java.util.List;

public class Like extends Condition {
    private String column;
    private Object value;

    protected Like() {
    }

    public Like column(String column) {
        this.column = column;
        return this;
    }

    public Like column(Column column) {
        this.column = column.toSql();
        return this;
    }


    public Like value(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public String toSql() {
        return column + " LIKE ?";
    }

    @Override
    public List<Object> parameters() {
        return List.of(value);
    }


    @Override
    public String toString() {
        return "Like{" +
                "column='" + column + '\'' +
                ", value=" + value +
                '}';
    }
}
