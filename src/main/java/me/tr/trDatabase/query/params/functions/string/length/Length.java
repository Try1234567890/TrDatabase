package me.tr.trDatabase.query.params.functions.string.length;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.functions.string.StringFunc;

public class Length extends StringFunc {
    @Override
    public String toSql() {
        if (Utility.isNull(str())) {
            TrDatabase.instance().logger().error("String cannot be null in LENGTH(String) constraint.");
            return "";
        }
        return "LENGTH(" + str() + ")";
    }


}
