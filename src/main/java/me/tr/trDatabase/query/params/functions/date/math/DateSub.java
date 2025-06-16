package me.tr.trDatabase.query.params.functions.date.math;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;

public class DateSub extends DateOperation {


    @Override
    public String toSql() {
        if (Utility.isNull(date())) {
            TrDatabase.instance().logger().error("Date cannot be null in DATE_ADD() constraint");
            return "";
        }
        if (amount() == -1) {
            TrDatabase.instance().logger().error("Interval cannot be null in DATE_ADD() constraint");
            return "";
        }
        if (unit() == null) {
            TrDatabase.instance().logger().error("Unit cannot be null in DATE_ADD() constraint");
            return "";
        }
        return "DATE_SUB('" + date() + "', INTERVAL " + amount() + " " + unit() + ")";
    }
}
