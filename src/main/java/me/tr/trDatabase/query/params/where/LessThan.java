package me.tr.trDatabase.query.params.where;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.Column;

import java.util.List;

public class LessThan extends Condition {
    private String column;
    private Object value;

    protected LessThan() {

    }

    public LessThan column(String column) {
        this.column = column;
        return this;
    }

    public LessThan column(Column column) {
        this.column = column.toSql();
        return this;
    }

    public LessThan value(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public String toSql() {

        if (Utility.isNull(column)) {
            TrDatabase.instance().logger().error("Column cannot be null in LESS_THAN (<) clause.");
            return "";
        }
        return column + " < ?";
    }

    @Override
    public List<Object> parameters() {
        return List.of(value);
    }

    @Override
    public String toString() {
        return "LessThan{" +
                "column='" + column + '\'' +
                ", value=" + value +
                '}';
    }
}
