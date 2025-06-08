package me.tr.trDatabase.query.params;

import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.functions.Function;

public class Column {
    private String name;
    private String distinct = "";
    private String as;
    private Function function;

    private Column(String name, String as) {
        this.name = name;
        this.as = as;
    }

    private Column(String as) {
        this.as = as;
    }

    public String name() {
        return name;
    }

    public Column name(String name) {
        this.name = name;
        return this;
    }

    public String as() {
        return as;
    }

    public Column as(String as) {
        this.as = as;
        return this;
    }

    public Column distinct() {
        this.distinct = "DISTINCT ";
        return this;
    }

    public static Column of(Function fun) {
        return new Column(fun.toSql(), "").function(fun);
    }

    public static Column of(Function fun, String as) {
        return new Column(fun.toSql(), as).function(fun);
    }

    public static Column of(String name) {
        return new Column(name, "");
    }

    public static Column of(String name, String as) {
        return new Column(name, as);
    }

    private Column function(Function function) {
        this.function = function;
        return this;
    }

    private boolean isFunction() {
        return function != null;
    }

    private String sql() {
        return distinct + name + (!Utility.isNull(as) ? " AS " + as : "");
    }

    public String toSql() {
        if (isFunction()) {
            return function.toSql().replace(sql(), name()) + (!Utility.isNull(as) ? " AS " + as : "");
        }
        return sql();
    }

    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", as='" + as + '\'' +
                ", distinct=" + Utility.isNull(distinct) +
                '}';
    }
}
