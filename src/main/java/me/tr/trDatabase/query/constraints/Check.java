package me.tr.trDatabase.query.constraints;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.where.Condition;

import java.util.Arrays;

public class Check extends Constraint {
    private Condition[] conditions;

    protected Check() {
        super(ConstraintType.CHECK);
    }

    public Condition[] conditions() {
        return conditions;
    }

    public Check conditions(Condition[] conditions) {
        if (conditions == null) {
            return this;
        }
        this.conditions = conditions;
        return this;
    }

    @Override
    public String toSql() {
        if (conditions == null || conditions.length == 0) {
            TrDatabase.instance().logger().error("No conditions specified for constraint " +  name() + (type() != null ? ": " + type().name() : ""));
            return "";
        }
        if (Utility.isNull(name())) {
            return "CHECK " + String.join(" AND ", Arrays.stream(conditions).map(Condition::toSql).toList());
        }
        return "CONSTRAINT " + name() + " CHECK " + "(" + String.join(" AND ", Arrays.stream(conditions).map(Condition::toSql).toList()) + ')';

    }
}
