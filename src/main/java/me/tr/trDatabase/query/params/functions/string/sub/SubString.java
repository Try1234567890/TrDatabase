package me.tr.trDatabase.query.params.functions.string.sub;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.functions.string.StringFunc;

public class SubString extends StringFunc {
    private int start;
    private int length;

    public int start() {
        return start;
    }

    public SubString start(int start) {
        this.start = start;
        return this;
    }

    public int length() {
        return length;
    }

    public SubString length(int length) {
        this.length = length;
        return this;
    }

    @Override
    public String toSql() {
        if (Utility.isNull(str())) {
            TrDatabase.instance().logger().error("String cannot be null in SUBSTRING(String, int, int) constraint.");
            return "";
        }
        if (length == -1) {
            TrDatabase.instance().logger().error("Length cannot be null in SUBSTRING(String, int, int) constraint.");
            return "";
        }
        if (start == -1) {
            TrDatabase.instance().logger().error("Start cannot be null in SUBSTRING(String, int, int) constraint.");
            return "";
        }
        return "SUBSTRING(" + str() + ", " + start() + ", " + length() + ")";
    }
}
