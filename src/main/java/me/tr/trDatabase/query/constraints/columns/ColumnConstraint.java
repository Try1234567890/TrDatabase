package me.tr.trDatabase.query.constraints.columns;

import me.tr.trDatabase.query.Query;
import me.tr.trDatabase.query.constraints.ConstraintType;
import me.tr.trDatabase.query.params.functions.Function;

public abstract class ColumnConstraint implements Query {
    private final ConstraintType type;

    protected ColumnConstraint(ConstraintType type) {
        this.type = type;
    }


    public static ColumnConstraint notNull() {
        return new NotNull();
    }

    public static ColumnConstraint autoIncrement() {
        return new AutoIncrement();
    }

    public static ColumnConstraint def(String value) {
        return new Default().value(value);
    }

    public static ColumnConstraint def(Function value) {
        return new Default().value(value);
    }

    public ConstraintType type() {
        return type;
    }
}
