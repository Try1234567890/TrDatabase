package me.tr.trDatabase.query.params.functions.date.math;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.functions.date.AbstractDate;

public class DateDiff extends AbstractDate {
    private String dateLeft;
    private String dateRight;

    public String dateLeft() {
        return dateLeft;
    }

    public DateDiff dateLeft(String dateLeft) {
        this.dateLeft = dateLeft;
        return this;
    }

    public String dateRight() {
        return dateRight;
    }

    public DateDiff dateRight(String dateRight) {
        this.dateRight = dateRight;
        return this;
    }

    @Override
    public String toSql() {
        if (Utility.isNull(dateLeft) ||  Utility.isNull(dateRight)) {
            TrDatabase.instance().logger().error("Dates cannot be null in DATE_DIFF() constraint and " + (dateLeft == null ? "Left" : "Right") + " date is null.");
            return "";
        }
        return "DATEDIFF('" + dateLeft() + "', '" + dateRight() + "')";
    }
}
