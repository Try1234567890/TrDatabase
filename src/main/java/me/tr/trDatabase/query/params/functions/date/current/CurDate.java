package me.tr.trDatabase.query.params.functions.date.current;

import me.tr.trDatabase.query.params.functions.date.AbstractDate;

public class CurDate extends AbstractDate {
    @Override
    public String toSql() {
        return "CURDATE()";
    }
}
