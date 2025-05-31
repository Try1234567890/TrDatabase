package me.tr.trDatabase.database.query.additions;

import me.tr.trDatabase.Utility;

public class GroupBy {
    private String column;

    public GroupBy(String column) {
        this.column = column;
    }

    public GroupBy() {}

    public String column() {
        return column;
    }

    public GroupBy column(String column) {
        this.column = column;
        return this;
    }

    public String get() {
        if (Utility.isNull(this.column)) {
            return "";
        }
        return " GROUP BY " + column;
    }
}

