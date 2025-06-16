package me.tr.trDatabase.query.params.functions.string.cases;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.functions.string.StringFunc;

public class Lower extends StringFunc {
    @Override
    public String toSql() {
        if (Utility.isNull(str())) {
            TrDatabase.instance().logger().error("String cannot be null in LOWER(String) constraint.");
            return "";
        }
        return "LOWER(" + str() + ")";
    }
}
