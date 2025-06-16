package me.tr.trDatabase.query.constraints.columns;

import me.tr.trDatabase.query.constraints.ConstraintType;

public class AutoIncrement extends ColumnConstraint {

    protected AutoIncrement() {
        super(ConstraintType.AUTO_INCREMENT);
    }

    @Override
    public String toSql() {
        return " AUTO_INCREMENT PRIMARY KEY";
    }
}
