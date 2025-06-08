package me.tr.trDatabase.query.params.functions.date;

import me.tr.trDatabase.query.params.Column;
import me.tr.trDatabase.query.params.functions.date.components.Day;
import me.tr.trDatabase.query.params.functions.date.components.Month;
import me.tr.trDatabase.query.params.functions.date.components.Year;
import me.tr.trDatabase.query.params.functions.date.current.CurDate;
import me.tr.trDatabase.query.params.functions.date.current.CurTime;
import me.tr.trDatabase.query.params.functions.date.current.Now;
import me.tr.trDatabase.query.params.functions.date.math.*;

public class Dates {
    private static final Dates instance = new Dates();

    public static Dates instance() {
        return instance;
    }

    private Dates() {
    }

    public Date date(Column column) {
        return new Date().column(column);
    }

    public Date date(String column) {
        return new Date().column(column);
    }

    public Date day(Column column) {
        return new Day().column(column);
    }

    public Date day(String column) {
        return new Day().column(column);
    }

    public Date month(Column column) {
        return new Month().column(column);
    }

    public Date month(String column) {
        return new Month().column(column);
    }

    public Date year(Column column) {
        return new Year().column(column);
    }

    public Date year(String column) {
        return new Year().column(column);
    }

    public CurDate curDate() {
        return new CurDate();
    }

    public CurTime curTime() {
        return new CurTime();
    }

    public Now now() {
        return new Now();
    }

    public DateDiff dateDiff(String dateLeft, String dateRight) {
        return new DateDiff().dateLeft(dateLeft).dateRight(dateRight);
    }

    public DateOperation dateAdd(String date, int amount, DateUnit unit) {
        return new DateAdd().date(date).amount(amount).unit(unit);
    }

    public DateOperation dateSub(String date, int amount, DateUnit unit) {
        return new DateSub().date(date).amount(amount).unit(unit);
    }

}
