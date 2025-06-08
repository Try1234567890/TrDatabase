package me.tr.trDatabase.query.params.where;

import java.util.List;

public class And extends Operator {

    protected And(List<Condition> right, List<Condition> left) {
        super(right, left);
    }

    protected And(Condition right, Condition left) {
        super(List.of(right), List.of(left));
    }

    @Override
    public String toSql() {
        return '(' + String.join("", right().stream().map(Condition::toSql).toList()) + " AND " + String.join("", left().stream().map(Condition::toSql).toList()) + ')';
    }
}
