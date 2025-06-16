package me.tr.trDatabase.query.params.functions.string.sub;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.functions.string.StringFunc;

public class Position extends StringFunc {
    private String sub;

    public String sub() {
        return sub;
    }

    public Position sub(String sub) {
        this.sub = sub;
        return this;
    }

    @Override
    public String toSql() {
        if (Utility.isNull(str()) || Utility.isNull(sub)) {
            TrDatabase.instance().logger().error("String and sub cannot be null in POSITION(String, String) constraint and " + (Utility.isNull(sub) ? "Sub" : "String") + " is null");
            return "";
        }
        return "POSITION(" + sub() + " IN " + str() + ")";
    }
}
