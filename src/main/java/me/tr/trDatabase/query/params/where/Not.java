package me.tr.trDatabase.query.params.where;

import java.util.List;

public class Not extends Operator {

    protected Not(List<Condition> right) {
        super(right, null);
    }

    protected Not(Condition... right) {
        super(List.of(right), null);
    }

    @Override
    public String toSql() {
        return "NOT (" + String.join("", right().stream().map(Condition::toSql).toList()) + ")";
    }
}
