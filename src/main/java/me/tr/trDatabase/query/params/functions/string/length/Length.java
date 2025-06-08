package me.tr.trDatabase.query.params.functions.string.length;

import me.tr.trDatabase.query.params.functions.string.StringFunc;

public class Length extends StringFunc {
    @Override
    public String toSql() {
        return "LENGTH(" + str() + ")";
    }


}
