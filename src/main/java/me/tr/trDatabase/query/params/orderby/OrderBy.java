package me.tr.trDatabase.query.params.orderby;

import me.tr.trDatabase.query.params.functions.Function;
import me.tr.trDatabase.query.params.where.Condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderBy {
    private final List<String> columns = new ArrayList<>();
    private final List<Object> parameters = new ArrayList<>();

    private OrderBy(String... columns) {
        this.columns.addAll(List.of(columns));
    }

    public OrderBy(Function... functions) {
        this.columns.addAll(Arrays.stream(functions).map(Function::toSql).toList());
    }

    public OrderBy(Condition... conditions) {
        this.parameters.addAll(Arrays.stream(conditions).flatMap(condition -> condition.parameters().stream()).toList());
        this.columns.addAll(Arrays.stream(conditions).map(Condition::toSql).toList());
    }

    private OrderBy(Order order, String... columns) {
        this.columns.addAll(Arrays.stream(columns).map(column -> column + (order != null ? ' ' + order.name() : "")).toList());
    }

    public OrderBy(Order order, Function... functions) {
        this.columns.addAll(Arrays.stream(functions).map(function -> function.toSql() + (order != null ? ' ' + order.name() : "")).toList());
    }

    public OrderBy(Order order, Condition... conditions) {
        this.parameters.addAll(Arrays.stream(conditions).flatMap(condition -> condition.parameters().stream()).toList());
        this.columns.addAll(Arrays.stream(conditions).map(condition -> condition.toSql() + (order != null ? ' ' + order.name() : "")).toList());
    }

    public static OrderBy of(String... columns) {
        return new OrderBy(columns);
    }

    public static OrderBy of(Function... functions) {
        return new OrderBy(functions);
    }

    public static OrderBy of(Condition... conditions) {
        return new OrderBy(conditions);
    }

    public static OrderBy of(Order order, String... columns) {
        return new OrderBy(order, columns);
    }

    public static OrderBy of(Order order, Function... functions) {
        return new OrderBy(order, functions);
    }

    public static OrderBy of(Order order, Condition... conditions) {
        return new OrderBy(order, conditions);
    }

    public OrderBy and(String... columns) {
        this.columns.addAll(List.of(columns));
        return this;
    }

    public OrderBy and(Function... functions) {
        this.columns.addAll(Arrays.stream(functions).map(Function::toSql).toList());
        return this;
    }

    public OrderBy and(Condition... conditions) {
        this.parameters.addAll(Arrays.stream(conditions).flatMap(condition -> condition.parameters().stream()).toList());
        this.columns.addAll(Arrays.stream(conditions).map(Condition::toSql).toList());
        return this;
    }

    public OrderBy and(Order order, String... columns) {
        this.columns.addAll(Arrays.stream(columns).map(column -> column + (order != null ? ' ' + order.name() : "")).toList());
        return this;
    }

    public OrderBy and(Order order, Function... functions) {
        this.columns.addAll(Arrays.stream(functions).map(function -> function.toSql() + (order != null ? ' ' + order.name() : "")).toList());
        return this;
    }

    public OrderBy and(Order order, Condition... conditions) {
        this.parameters.addAll(Arrays.stream(conditions).flatMap(condition -> condition.parameters().stream()).toList());
        this.columns.addAll(Arrays.stream(conditions).map(condition -> condition.toSql() + (order != null ? ' ' + order.name() : "")).toList());
        return this;
    }

    public String toSql() {
        return " ORDER BY " + String.join(", ", columns);
    }

    public List<Object> parameters() {
        return parameters;
    }
}
