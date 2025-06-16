package me.tr.trDatabase.query.params.functions.string.sub;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;

public class Left extends Edge {
    @Override
    public String toSql() {
        if (Utility.isNull(str()) || length() == -1) {
            TrDatabase.instance().logger().error("String and length cannot be null in LEFT(String, int) constraint and " + (length() == -1 ? "Length" : "String") + " is null");
            return "";
        }
        return "LEFT(" + str() + ", " + length() + ")";
    }
}
