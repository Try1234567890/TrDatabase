package me.tr.trDatabase.query.params.join;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.query.params.where.Condition;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RightJoin extends Join {

    @Override
    public String toSql() {
        if (table() == null) {
            TrDatabase.instance().logger().error("Table cannot be null in RIGHT JOIN clause.");
            return "";
        }
        if (conditions() == null) {
            TrDatabase.instance().logger().error("Conditions cannot be null in RIGHT JOIN clause.");
            return "";
        }
        return " RIGHT JOIN " + table().toSql() + " ON " + Arrays.stream(conditions()).map(Condition::toSql).collect(Collectors.joining(""));
    }
}
