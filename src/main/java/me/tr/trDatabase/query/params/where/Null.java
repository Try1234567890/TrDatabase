package me.tr.trDatabase.query.params.where;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.Column;

import java.util.List;

public class Null extends Condition {
    private String column;

    protected Null() {

    }

    public Null column(String column) {
        this.column = column;
        return this;
    }

    public Null column(Column column) {
        this.column = column.toSql();
        return this;
    }

    @Override
    public String toSql() {
        if (Utility.isNull(column)) {
            TrDatabase.instance().logger().error("Column cannot be null in IS NULL clause.");
            return "";
        }
        return column + " IS NULL";
    }

    @Override
    public List<Object> parameters() {
        return List.of(column);
    }

    @Override
    public String toString() {
        return "Null{" +
                "column='" + column + '\'' +
                '}';
    }
}
