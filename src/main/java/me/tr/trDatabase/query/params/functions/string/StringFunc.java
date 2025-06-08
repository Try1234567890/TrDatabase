package me.tr.trDatabase.query.params.functions.string;

import me.tr.trDatabase.query.params.functions.Function;

public abstract class StringFunc implements Function {
    private String str;


    public String str() {
        return str;
    }

    public StringFunc str(String str) {
        this.str = str;
        return this;
    }
}
