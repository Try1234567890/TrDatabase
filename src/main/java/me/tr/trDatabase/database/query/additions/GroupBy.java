package me.tr.trDatabase.database.query.additions;

public class GroupBy {
    private String column;

    public GroupBy(String column) {
        this.column = column;
    }

    public String column() {
        return column;
    }

    public GroupBy column(String column) {
        this.column = column;
        return this;
    }

    public String execute() {
        return " GROUP BY " + column;
    }
}

