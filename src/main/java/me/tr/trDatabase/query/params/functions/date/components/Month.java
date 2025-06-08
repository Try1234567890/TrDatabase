package me.tr.trDatabase.query.params.functions.date.components;

import me.tr.trDatabase.query.params.functions.date.Date;

public class Month extends Date {

    @Override
    public String toSql() {
        return "MONTH(" + column().name() + ")";
    }
}
