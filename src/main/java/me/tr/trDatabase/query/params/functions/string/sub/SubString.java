package me.tr.trDatabase.query.params.functions.string.sub;

import me.tr.trDatabase.query.params.functions.string.StringFunc;

public class SubString extends StringFunc {
    private int start;
    private int length;

    public int start() {
        return start;
    }

    public SubString start(int start) {
        this.start = start;
        return this;
    }

    public int length() {
        return length;
    }

    public SubString length(int length) {
        this.length = length;
        return this;
    }

    @Override
    public String toSql() {
        return "SUBSTRING(" + str() + ", " + start() + ", " + length() + ")";
    }
}
