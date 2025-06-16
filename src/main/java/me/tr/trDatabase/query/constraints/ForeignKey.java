package me.tr.trDatabase.query.constraints;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;

public class ForeignKey extends Constraint {
    private String referenceName;

    protected ForeignKey() {
        super(ConstraintType.FOREIGN_KEY);
    }

    public ForeignKey referenceName(String referenceName) {
        this.referenceName = referenceName;
        return this;
    }

    @Override
    public String toSql() {
        if (Utility.isNull(referenceName)) {
            TrDatabase.instance().logger().error("Reference name cannot be null for constraint " + name() + (type() != null ? ": " + type().name() : ""));
            return "";
        }
        return super.toSql() + " REFERENCES " + referenceName + " (" + String.join(", ", columns()) + ")";
    }
}
