package me.tr.trDatabase.query.params.where;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.Column;

import java.util.ArrayList;
import java.util.List;

public class Between extends Condition {
    private String column;
    private List<Object> value = new ArrayList<>();

    public Between() {

    }

    public Between column(String column) {
        this.column = column;
        return this;
    }

    public Between column(Column column) {
        this.column = column.toSql();
        return this;
    }

    public Between value(List<Object> value) {
        this.value.addAll(value);
        return this;
    }

    public Between value(Object... value) {
        this.value.addAll(List.of(value));
        return this;
    }

    @Override
    public String toSql() {
        if (Utility.isNull(column)) {
            TrDatabase.instance().logger().error("Column cannot be null in BETWEEN clause.");
            return "";
        }
        return column + " BETWEEN ? AND ?";
    }

    @Override
    public List<Object> parameters() {
        return value;
    }

    @Override
    public String toString() {
        return "Between{" +
                "column='" + column + '\'' +
                ", value=" + value +
                '}';
    }
}
