package me.tr.trDatabase.query.constraints.columns;

import me.tr.trDatabase.query.constraints.ConstraintType;

public class NotNull extends ColumnConstraint {
    protected NotNull() {
        super(ConstraintType.NOT_NULL);
    }

    @Override
    public String toSql() {
        return " NOT NULL";
    }
}
