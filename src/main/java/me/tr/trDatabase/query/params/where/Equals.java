package me.tr.trDatabase.query.params.where;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.Column;

import java.util.List;

public class Equals extends Condition {
    private String column;
    private Object value;

    protected Equals() {
    }

    public Equals column(String column) {
        this.column = column;
        return this;
    }

    public Equals column(Column column) {
        this.column = column.toSql();
        return this;
    }

    public Equals value(Object value) {
        if (value instanceof Column column) {
            this.column += " = " + column.toSql();
            return this;
        }
        this.value = value;
        return this;
    }

    @Override
    public String toSql() {
        if (Utility.isNull(column)) {
            TrDatabase.instance().logger().error("Column cannot be null in EQUALS (=) clause.");
            return "";
        }
        return column + (value != null ? " = ?" : "");
    }

    @Override
    public List<Object> parameters() {
        return value != null ? List.of(value) : List.of();
    }

    @Override
    public String toString() {
        return "Equals{" +
                "column='" + column + '\'' +
                ", value=" + value +
                '}';
    }
}