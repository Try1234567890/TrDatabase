package me.tr.trDatabase.query.params.functions.date.current;

import me.tr.trDatabase.query.params.functions.date.AbstractDate;

public class CurTime extends AbstractDate {
    @Override
    public String toSql() {
        return "CURTIME()";
    }
}
