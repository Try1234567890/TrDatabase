package me.tr.trDatabase.query.params.functions.control;

import me.tr.trDatabase.TrDatabase;

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
        if (valueLeft == null || valueRight == null) {
            TrDatabase.instance().logger().error("NullIf(Obj, Obj) constrains cannot contains null values and " + (valueLeft == null ? "Left" : "Right") + " value is null.");
            return "";
        }
        return "NULLIF(" + valueLeft() + ", " + valueRight() + ")";
    }
}
