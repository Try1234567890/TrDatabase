package me.tr.trDatabase.query.params.functions.date.math;

import me.tr.trDatabase.query.params.functions.date.AbstractDate;

public abstract class DateOperation extends AbstractDate {
    private String date;
    private int amount;
    private DateUnit unit;

    public String date() {
        return date;
    }

    public DateOperation date(String date) {
        this.date = date;
        return this;
    }

    public int amount() {
        return amount;
    }

    public DateOperation amount(int amount) {
        this.amount = amount;
        return this;
    }

    public DateUnit unit() {
        return unit;
    }

    public DateOperation unit(DateUnit unit) {
        this.unit = unit;
        return this;
    }
}
