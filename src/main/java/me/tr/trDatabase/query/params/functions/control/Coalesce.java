package me.tr.trDatabase.query.params.functions.control;

import java.util.Arrays;

public class Coalesce extends Control{
    private Object[] params;

    public Object[] params() {
        return params;
    }

    public Coalesce params(Object... params) {
        this.params = params;
        return this;
    }

    @Override
    public String toSql() {
        return "COALESCE(" + String.join(", ", Arrays.stream(params).map(String::valueOf).toList()) + ")";
    }
}
