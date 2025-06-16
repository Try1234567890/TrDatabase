package me.tr.trDatabase.query.params.functions.string;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.query.params.Column;

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
        if (strings == null || strings.length == 0) {
            TrDatabase.instance().logger().error("String cannot be null in CONCAT(String...) constraint.");
            return "";
        }
        return "CONCAT(" + String.join(", ", strings) + ")";
    }
}
