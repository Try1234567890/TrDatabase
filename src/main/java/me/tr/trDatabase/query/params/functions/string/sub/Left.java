package me.tr.trDatabase.query.params.functions.string.sub;

public class Left extends Edge {
    @Override
    public String toSql() {
        return "LEFT(" + str() + ", " + length() + ")";
    }
}
