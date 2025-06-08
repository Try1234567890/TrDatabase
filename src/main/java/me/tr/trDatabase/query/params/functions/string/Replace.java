package me.tr.trDatabase.query.params.functions.string;

import me.tr.trDatabase.Utility;

public class Replace extends StringFunc {
    private String from;
    private String to;

    public String from() {
        return from;
    }

    public Replace from(String from) {
        this.from = Utility.checkQuotes(from);
        return this;
    }

    public String to() {
        return to;
    }

    public Replace to(String to) {
        this.to = Utility.checkQuotes(to);
        return this;
    }

    @Override
    public String toSql() {
        return "REPLACE(" + str() + ", " + from() + ", " + to() + ")";
    }
}
