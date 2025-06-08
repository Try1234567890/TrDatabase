package me.tr.trDatabase.query.params.functions.date.math;

public class DateAdd extends DateOperation {


    @Override
    public String toSql() {
        return "DATE_ADD('" + date() + "', INTERVAL " + amount() + " " + unit() + ")";
    }
}
