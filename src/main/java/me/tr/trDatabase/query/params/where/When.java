package me.tr.trDatabase.query.params.where;

import me.tr.trDatabase.TrDatabase;

import java.util.stream.Collectors;

public class When extends Where {
    
    public When condition(Condition condition) {
        super.condition(condition);
        return this;
    }

    public When and(Condition condition) {
        super.and(condition);
        return this;
    }

    public When or(Condition condition) {
        super.or(condition);
        return this;
    }

    public When not() {
        super.not();
        return this;
    }

    @Override
    public String toSql() {
        if (conditions() == null) {
            TrDatabase.instance().logger().error("Conditions cannot be null in WHEN clause.");
            return "";
        }
        return "WHEN " + conditions().stream().map(Condition::toSql).collect(Collectors.joining(""));
    }
}
