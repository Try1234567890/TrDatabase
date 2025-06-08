package me.tr.trDatabase.query.params.functions.date.math;

public class DateSub extends DateOperation {


    @Override
    public String toSql() {
        return "DATE_SUB('" + date() + "', INTERVAL " + amount() + " " + unit() + ")";
    }
}
