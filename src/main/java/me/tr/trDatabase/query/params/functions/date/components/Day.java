package me.tr.trDatabase.query.params.functions.date.components;

import me.tr.trDatabase.query.params.functions.date.Date;

public class Day extends Date {


    @Override
    public String toSql() {
        return "DAY(" + column().name() + ")";
    }
}
