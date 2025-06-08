package me.tr.trDatabase.query.params.groupby;

import me.tr.trDatabase.query.params.functions.Function;
import me.tr.trDatabase.query.params.functions.control.Control;
import me.tr.trDatabase.query.params.functions.date.Date;
import me.tr.trDatabase.query.params.functions.math.Math;
import me.tr.trDatabase.query.params.functions.string.StringFunc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupBy {
    private final List<String> columns = new ArrayList<>();

    private GroupBy(String... columns) {
        this.columns.addAll(List.of(columns));
    }

    private GroupBy(Control... function) {
        this.columns.addAll(Arrays.stream(function).map(Function::toSql).toList());
    }

    private GroupBy(Date... function) {
        this.columns.addAll(Arrays.stream(function).map(Function::toSql).toList());
    }

    private GroupBy(Math... function) {
        this.columns.addAll(Arrays.stream(function).map(Function::toSql).toList());
    }

    private GroupBy(StringFunc... function) {
        this.columns.addAll(Arrays.stream(function).map(Function::toSql).toList());
    }

    public static GroupBy of(String... columns) {
        return new GroupBy(columns);
    }

    public static GroupBy of(Control... function) {
        return new GroupBy(function);
    }

    public static GroupBy of(Date... function) {
        return new GroupBy(function);
    }

    public static GroupBy of(Math... function) {
        return new GroupBy(function);
    }

    public static GroupBy of(StringFunc... function) {
        return new GroupBy(function);
    }

    public GroupBy add(String... columns) {
        this.columns.addAll(List.of(columns));
        return this;
    }

    public GroupBy add(Control... function) {
        this.columns.addAll(Arrays.stream(function).map(Function::toSql).toList());
        return this;
    }

    public GroupBy add(Date... function) {
        this.columns.addAll(Arrays.stream(function).map(Function::toSql).toList());
        return this;
    }

    public GroupBy add(Math... function) {
        this.columns.addAll(Arrays.stream(function).map(Function::toSql).toList());
        return this;
    }

    public GroupBy add(StringFunc... function) {
        this.columns.addAll(Arrays.stream(function).map(Function::toSql).toList());
        return this;
    }


    public String toSql() {
        return " GROUP BY " + String.join(", ", columns);
    }

}
