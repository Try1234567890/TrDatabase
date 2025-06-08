package me.tr.trDatabase.query.params.functions.string.sub;

import me.tr.trDatabase.query.params.functions.string.StringFunc;

public class Position extends StringFunc {
    private String sub;

    public String sub() {
        return sub;
    }

    public Position sub(String sub) {
        this.sub = sub;
        return this;
    }

    @Override
    public String toSql() {
        return "POSITION(" + sub() + " IN " + str() + ")";
    }
}
