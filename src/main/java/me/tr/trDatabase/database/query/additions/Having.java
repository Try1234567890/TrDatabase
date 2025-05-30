package me.tr.trDatabase.database.query.additions;

import java.util.List;

public class Having {
    private List<String> conditions;

    public Having(String... conditions) {
        this.conditions = List.of(conditions);
    }

    public List<String> conditions() {
        return conditions;
    }

    public Having condition(String... conditions) {
        this.conditions = List.of(conditions);
        return this;
    }

    public String execute() {
        return " HAVING " +
                String.join(" and ", conditions);
    }
}
