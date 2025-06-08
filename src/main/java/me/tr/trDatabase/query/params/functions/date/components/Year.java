package me.tr.trDatabase.query.params.functions.date.components;

import me.tr.trDatabase.query.params.functions.date.Date;

public class Year extends Date {


    @Override
    public String toSql() {
        return "YEAR(" + column().name() + ")";
    }
}
