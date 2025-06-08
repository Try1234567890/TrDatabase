package me.tr.trDatabase.query.params.functions.string.sub;

public class Right extends Edge {
    @Override
    public String toSql() {
        return "RIGHT(" + str() + ", " + length() + ")";
    }
}
