package me.tr.trDatabase.database.query.additions;

import java.util.ArrayList;
import java.util.List;

public class Having {
    private final List<String> conditions = new ArrayList<>();

    public Having(String... conditions) {
        this.conditions.addAll(List.of(conditions));
    }

    public Having() {

    }

    public List<String> conditions() {
        return conditions;
    }

    public Having condition(String... conditions) {
        this.conditions.addAll(List.of(conditions));
        return this;
    }

    public String execute() {
        if (this.conditions.isEmpty()) {
            return "";
        }
        return " HAVING " +
                String.join(" and ", conditions);
    }
}
