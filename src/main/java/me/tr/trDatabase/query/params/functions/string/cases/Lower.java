package me.tr.trDatabase.query.params.functions.string.cases;

import me.tr.trDatabase.query.params.functions.string.StringFunc;

public class Lower extends StringFunc {
    @Override
    public String toSql() {
        return "LOWER(" + str() + ")";
    }
}
