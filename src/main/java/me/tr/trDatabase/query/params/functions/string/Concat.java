package me.tr.trDatabase.query.params.functions.string;

public class Concat extends StringFunc {
    private String[] strings;


    public String[] strings() {
        return strings;
    }

    public Concat strings(String... strings) {
        this.strings = strings;
        return this;
    }

    @Override
    public java.lang.String toSql() {
        return "CONCAT(" + String.join(", ", strings) + ")";
    }
}
