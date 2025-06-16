package me.tr.trDatabase.query.params.where;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.Column;

import java.util.List;

public class GreaterThanOrEquals extends Condition {
    private String column;
    private Object value;


    protected GreaterThanOrEquals() {

    }

    public GreaterThanOrEquals column(String column) {
        this.column = column;
        return this;
    }

    public GreaterThanOrEquals column(Column column) {
        this.column = column.toSql();
        return this;
    }

    public GreaterThanOrEquals value(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public String toSql() {
        if (Utility.isNull(column)) {
            TrDatabase.instance().logger().error("Column cannot be null in GREATER_THAN_OR_EQUALS (>=) clause.");
            return "";
        }
        return column + " >= ?";
    }

    @Override
    public List<Object> parameters() {
        return List.of(value);
    }

    @Override
    public String toString() {
        return "GreaterThanOrEquals{" +
                "column='" + column + '\'' +
                ", value=" + value +
                '}';
    }
}
