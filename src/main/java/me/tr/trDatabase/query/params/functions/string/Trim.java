package me.tr.trDatabase.query.params.functions.string;

public class Trim extends StringFunc {
    @Override
    public String toSql() {
        return "TRIM(" + str() + ")";
    }
}
