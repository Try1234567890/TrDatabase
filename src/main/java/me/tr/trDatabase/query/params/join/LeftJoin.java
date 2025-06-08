package me.tr.trDatabase.query.params.join;

import me.tr.trDatabase.query.params.where.Condition;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LeftJoin extends Join {

    @Override
    public String toSql() {
        return " LEFT JOIN " + table().toSql() + " ON " + Arrays.stream(conditions()).map(Condition::toSql).collect(Collectors.joining(""));
    }
}
