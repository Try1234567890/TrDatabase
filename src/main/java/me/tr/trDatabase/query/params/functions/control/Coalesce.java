package me.tr.trDatabase.query.params.functions.control;

import me.tr.trDatabase.TrDatabase;

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
        if (params == null || params.length == 0) {
            TrDatabase.instance().logger().error("Params cannot be null or empty in coalesce(Obj...) constraint.");
            return "";
        }
        return "COALESCE(" + String.join(", ", Arrays.stream(params).map(String::valueOf).toList()) + ")";
    }
}
