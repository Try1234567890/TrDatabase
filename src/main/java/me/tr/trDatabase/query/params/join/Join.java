package me.tr.trDatabase.query.params.join;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.query.table.Table;
import me.tr.trDatabase.query.params.where.Condition;
import me.tr.trDatabase.query.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Join {
    private Table table;
    private Condition[] conditions;
    private static Select left;

    public static Join join(Table table, Condition... conditions) {
        return new Join().table(table).conditions(conditions);
    }

    public static Join inner(Table table, Condition... conditions) {
        return new InnerJoin().table(table).conditions(conditions);
    }

    public static Join left(Table table, Condition... conditions) {
        return new LeftJoin().table(table).conditions(conditions);
    }

    public static Join right(Table table, Condition... conditions) {
        return new RightJoin().table(table).conditions(conditions);
    }

    public static Join union(Select right) {
        return new Union().right(right);
    }

    public Table table() {
        return table;
    }

    public Join table(Table column) {
        this.table = column;
        return this;
    }

    public List<Object> parameters() {
        final List<Object> parameters = new ArrayList<>();
        for (Condition condition : conditions) {
            parameters.addAll(condition.parameters());
        }
        return parameters;
    }

    protected Condition[] conditions() {
        return conditions;
    }

    private Join conditions(Condition... conditions) {
        this.conditions = conditions;
        return this;
    }

    public Join left(Select left) {
        Join.left = left;
        return this;
    }

    public static Select left() {
        return left;
    }

    public String toSql() {
        if (table() == null) {
            TrDatabase.instance().logger().error("Table cannot be null in JOIN clause.");
            return "";
        }
        if (conditions() == null) {
            TrDatabase.instance().logger().error("Conditions cannot be null in JOIN clause.");
            return "";
        }
        return " JOIN " + table.toSql() + " ON " + Arrays.stream(conditions()).map(Condition::toSql).collect(Collectors.joining(""));
    }
}
