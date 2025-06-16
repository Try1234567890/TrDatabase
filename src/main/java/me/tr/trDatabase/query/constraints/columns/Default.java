package me.tr.trDatabase.query.constraints.columns;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.constraints.ConstraintType;
import me.tr.trDatabase.query.params.functions.Function;

public class Default extends ColumnConstraint {
    private String value;

    protected Default() {
        super(ConstraintType.DEFAULT);
    }

    public String value() {
        return value;
    }

    public Default value(String value) {
        this.value = Utility.checkQuotes(value);
        return this;
    }

    public Default value(Function value) {
        if (value == null) {
            return this;
        }
        this.value = value.toSql();
        return this;
    }

    @Override
    public String toSql() {
        if (Utility.isNull(value)) {
            TrDatabase.instance().logger().error("Default value cannot be null.");
            return "";
        }
        return " DEFAULT " + value;
    }
}
