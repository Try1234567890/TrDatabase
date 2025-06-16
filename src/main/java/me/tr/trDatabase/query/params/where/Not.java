package me.tr.trDatabase.query.params.where;

import me.tr.trDatabase.TrDatabase;

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
        if (right() == null) {
            TrDatabase.instance().logger().error("Conditions cannot be null in NOT operator");
            return "";
        }
        return "NOT (" + String.join("", right().stream().map(Condition::toSql).toList()) + ")";
    }
}
