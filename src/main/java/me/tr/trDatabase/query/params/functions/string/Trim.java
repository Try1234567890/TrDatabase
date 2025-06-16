package me.tr.trDatabase.query.params.functions.string;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;

public class Trim extends StringFunc {
    @Override
    public String toSql() {
        if (Utility.isNull(str())) {
            TrDatabase.instance().logger().error("String cannot be null in TRIM(String) constraint.");
            return "";
        }
        return "TRIM(" + str() + ")";
    }
}
