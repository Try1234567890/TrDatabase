package me.tr.trDatabase.query.table.create;

import me.tr.trDatabase.query.Query;
import me.tr.trDatabase.query.Select;
import me.tr.trDatabase.query.table.data.ColumnData;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Create implements Query {
    private String name = "";
    private String notExists = "";
    private ColumnData[] columns;
    private Select select;
    private final List<Object> parameters = new ArrayList<>();

    public String name() {
        return name;
    }

    public Create name(String name) {
        this.name = name;
        return this;
    }

    public Create notExists() {
        this.notExists = "IF NOT EXISTS ";
        return this;
    }

    public ColumnData[] columns() {
        return columns;
    }

    public Create columns(ColumnData... columns) {
        this.columns = columns;
        return this;
    }

    public Create copy(Select select) {
        this.select = select;
        return this;
    }

    public @Nullable Select from() {
        return select;
    }

    public String toSql() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ").append(notExists).append(name);
        if (select != null) {
            parameters.addAll(select.parameters());
            builder.append(" AS ").append(select.toSql());
            return builder.toString();
        } else {
            builder.append(" (");
            for (int i = 0; i < columns.length; i++) {
                builder.append(columns[i].toSql());
                if (i < columns.length - 1) {
                    builder.append(", ");
                }
            }
        }
        builder.append(")");
        return builder.toString();
    }

    public List<Object> parameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "TableBuilder{" +
                "name='" + name + '\'' +
                ", notExists='" + notExists + '\'' +
                ", columns=" + Arrays.toString(columns) +
                ", select=" + select +
                ", parameters=" + parameters +
                '}';
    }
}
