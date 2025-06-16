package me.tr.trDatabase.query.params.functions.string;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;

public class Replace extends StringFunc {
    private String from;
    private String to;

    public String from() {
        return from;
    }

    public Replace from(String from) {
        this.from = Utility.checkQuotes(from);
        return this;
    }

    public String to() {
        return to;
    }

    public Replace to(String to) {
        this.to = Utility.checkQuotes(to);
        return this;
    }

    @Override
    public String toSql() {
        if (Utility.isNull(str())) {
            TrDatabase.instance().logger().error("String cannot be null in REPLACE(String, String, String) constraint.");
            return "";
        }
        if (Utility.isNull(from())) {
            TrDatabase.instance().logger().error("String to replace cannot be null in REPLACE(String, String, String) constraint.");
            return "";
        }
        if (Utility.isNull(to())) {
            TrDatabase.instance().logger().error("String replace cannot be null in REPLACE(String, String, String) constraint.");
            return "";
        }
        return "REPLACE(" + str() + ", " + from() + ", " + to() + ")";
    }
}
