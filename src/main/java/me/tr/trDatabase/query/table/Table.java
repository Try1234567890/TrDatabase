package me.tr.trDatabase.query.table;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;

public class Table {
    private String name;
    private String as;

    private Table() {
    }

    private Table(String name, String as) {
        this.name = name;
        this.as = as;
    }

    public String name() {
        return name;
    }

    public Table name(String name) {
        this.name = name;
        return this;
    }

    public String as() {
        return as;
    }

    public Table as(String as) {
        this.as = as;
        return this;
    }

    public static Table of(String name) {
        return new Table(name, "");
    }

    public static Table of(String name, String as) {
        return new Table(name, as);
    }

    public String toSql() {
        if (Utility.isNull(name)) {
            TrDatabase.instance().logger().error("Name cannot be null in Tables.");
            return "";
        }
        return name + (!Utility.isNull(as) ? ' ' + as : "");
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", as='" + as + '\'' +
                '}';
    }

}
