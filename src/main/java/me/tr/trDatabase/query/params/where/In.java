package me.tr.trDatabase.query.params.where;

import me.tr.trDatabase.query.params.Column;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class In extends Condition {
    private String column;
    private List<Object> values = new ArrayList<>();

    protected In() {

    }

    public In column(String column) {
        this.column = column;
        return this;
    }

    public In column(Column column) {
        this.column = column.toSql();
        return this;
    }

    public In values(Collection<Object> values) {
        this.values.addAll(values);
        return this;
    }

    public In values(Object... values) {
        this.values.addAll(Arrays.asList(values));
        return this;
    }

    @Override
    public String toSql() {
        return column + " IN (" + String.join(", ", "?".repeat(values.size()).split("")) + ")";
    }

    @Override
    public List<Object> parameters() {
        return values;
    }

    @Override
    public String toString() {
        return "In{" +
                "column='" + column + '\'' +
                ", values=" + values +
                '}';
    }
}
