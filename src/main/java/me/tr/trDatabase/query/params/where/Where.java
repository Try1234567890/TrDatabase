package me.tr.trDatabase.query.params.where;

import me.tr.trDatabase.TrDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Where extends Condition{
    private final List<Condition> conditions = new ArrayList<>();

    public Where() {
    }

    private Where(Condition... condition) {
        this.conditions.addAll(List.of(condition));
    }

    public Where condition(Condition condition) {
        this.conditions.add(condition);
        return this;
    }

    public Where and(Condition condition) {
        And and = new And(this.conditions, List.of(condition));
        return new Where(and);
    }

    public Where or(Condition condition) {
        Or or = new Or(this.conditions, List.of(condition));
        return new Where(or);
    }

    public Where not() {
        Not not = new Not(this.conditions);
        return new Where(not);
    }

    @Override
    public String toSql() {
        if (conditions() == null) {
            TrDatabase.instance().logger().error("Conditions cannot be null in WHERE clause.");
            return "";
        }
        return " WHERE " + conditions.stream().map(Condition::toSql).collect(Collectors.joining(""));
    }

    @Override
    public List<Object> parameters() {
        final List<Object> parameters = new ArrayList<>();
        for(Condition condition : conditions) {
            parameters.addAll(condition.parameters());
        }
        return parameters;
    }

    public List<Condition> conditions() {
        return conditions;
    }

    @Override
    public String toString() {
        return "Where{" +
                "conditions=" + conditions +
                '}';
    }
}
