package me.tr.trDatabase.query.params.functions.string.length;

import me.tr.trDatabase.query.params.functions.string.StringFunc;

public class Len extends StringFunc {
    @Override
    public String toSql() {
        return "LEN(" + str() + ")";
    }
}
