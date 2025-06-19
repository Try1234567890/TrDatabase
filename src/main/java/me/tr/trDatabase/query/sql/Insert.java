package me.tr.trDatabase.query.sql;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.Query;

import java.util.Arrays;

public class Insert implements Query {
    private String table;
    private String[] columns;
    private Object[] values;

    public String table() {
        return table;
    }

    public String[] columns() {
        return columns;
    }

    public Object[] values() {
        return values;
    }

    public Insert table(String table) {
        this.table = table;
        return this;
    }

    public Insert into(String table) {
        this.table = table;
        return this;
    }

    public Insert columns(String... columns) {
        this.columns = columns;
        return this;
    }

    public Insert values(Object... values) {
        this.values = Arrays.stream(values).map(value -> {
            if (value instanceof String str) {
                return Utility.checkQuotes(str);
            }
            return value;
        }).toArray(Object[]::new);
        return this;
    }


    @Override
    public String toSql() {
        if (Utility.isNull(table)) {
            TrDatabase.instance().logger().error("Table cannot be null in INSERT INTO query.");
            return "";
        }
        if ((columns == null || columns.length == 0) || (values == null || values.length == 0)) {
            TrDatabase.instance().logger().error("Columns or values cannot be null in INSERT INTO query and " + (columns == null ? "Columns" : "Values") + " is null.");
            return "";
        }
        if (columns.length != values.length) {
            TrDatabase.instance().logger().error("Columns and values length is not equals.");
            return "";
        }
        return "INSERT INTO " + table + " (" + String.join(", ", columns) + ')' + " VALUES (" + String.join(", ", Arrays.stream(values).map(String::valueOf).toList()) + ')';
    }
}
