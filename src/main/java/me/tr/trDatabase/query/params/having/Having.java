package me.tr.trDatabase.query.params.having;

import me.tr.trDatabase.query.params.where.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Having {
    private final List<Condition> conditions = new ArrayList<>();

    public static Having of(Condition... conditions) {
        return new Having().conditions(conditions);
    }

    public Having conditions(Condition... conditions) {
        this.conditions.addAll(List.of(conditions));
        return this;
    }

    public List<Object> parameters() {
        final List<Object> parameters = new ArrayList<>();
        for(Condition condition : conditions) {
            parameters.addAll(condition.parameters());
        }
        return parameters;
    }

    public String toSql() {
        return " HAVING " + conditions.stream().map(Condition::toSql).collect(Collectors.joining(""));
    }

}
