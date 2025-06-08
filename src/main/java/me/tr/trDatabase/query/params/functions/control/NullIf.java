package me.tr.trDatabase.query.params.functions.control;

public class NullIf extends Control {
    private Object valueLeft;
    private Object valueRight;


    public Object valueLeft() {
        return valueLeft;
    }

    public NullIf valueLeft(Object valueLeft) {
        this.valueLeft = valueLeft;
        return this;
    }

    public Object valueRight() {
        return valueRight;
    }

    public NullIf valueRight(Object valueRight) {
        this.valueRight = valueRight;
        return this;
    }

    @Override
    public String toSql() {
        return "NULLIF(" + valueLeft() + ", " + valueRight() + ")";
    }
}
