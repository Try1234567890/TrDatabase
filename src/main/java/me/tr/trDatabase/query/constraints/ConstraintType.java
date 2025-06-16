package me.tr.trDatabase.query.constraints;

public enum ConstraintType {
    NOT_NULL(true),
    AUTO_INCREMENT(true),
    UNIQUE,
    PRIMARY_KEY,
    FOREIGN_KEY,
    CHECK,
    DEFAULT(true);

    private boolean forEachColum = false;

    ConstraintType() {

    }

    ConstraintType(boolean forEachColum) {
        this.forEachColum = forEachColum;
    }

    public boolean isForEachColum() {
        return forEachColum;
    }
}
