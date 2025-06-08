package me.tr.trDatabase.query.params.where;

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
        return "WHEN " + conditions().stream().map(Condition::toSql).collect(Collectors.joining(""));
    }
}
