package me.tr.trDatabase.query.params.functions.control;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.functions.math.operations.Operation;
import me.tr.trDatabase.query.params.where.Condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Case extends Control {
    private Condition[] conditions;
    private List<String> then = new ArrayList<>();
    private String otherwise;
    private String name;

    public Condition[] conditions() {
        return conditions;
    }

    public Case conditions(Condition... conditions) {
        this.conditions = conditions;
        return this;
    }

    public List<String> then() {
        return then;
    }

    public Case then(Object... then) {
        this.then.addAll(Arrays.stream(then).map(Object::toString).toList());
        return this;
    }

    public Case then(String... then) {
        this.then.addAll(Arrays.stream(then).map(Utility::checkQuotes).toList());
        return this;
    }

    public Case then(Operation... then) {
        this.then.addAll(Arrays.stream(then).map(Operation::toSql).toList());
        return this;
    }

    public Object otherwise() {
        return otherwise;
    }

    public Case otherwise(Object otherwise) {
        this.otherwise = otherwise.toString();
        return this;
    }

    public Case otherwise(String otherwise) {
        this.otherwise = Utility.checkQuotes(otherwise);
        return this;
    }

    public Case otherwise(Operation otherwise) {
        this.otherwise = otherwise.toSql();
        return this;
    }

    public String name() {
        return name;
    }

    public Case name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toSql() {
        if (conditions == null || conditions.length == 0) {
            TrDatabase.instance().logger().error("Conditions cannot be null or empty in Case functions.");
            return "";
        }
        if (then == null || then.isEmpty()) {
            TrDatabase.instance().logger().error("\"Then\" cannot be null or empty in Case functions.");
            return "";
        }
        if (conditions.length != then.size()) {
            TrDatabase.instance().logger().error("Conditions anc Then length is not equals");
            return "";
        }
        return "CASE " + String.join(" ", mapWhenAndThen()) + (otherwise != null ? " ELSE " + otherwise : "") + " END" + (name != null ? " AS " + name : "");
    }

    private List<String> mapWhenAndThen() {
        final List<String> result = new ArrayList<>();
        for (int i = 0; i < conditions.length; i++) {
            result.add("WHEN " + conditions[i].toSql() + " THEN " + then.get(i));
        }
        return result;
    }

    public List<Object> parameters() {
        return Arrays.stream(conditions).map(Condition::toSql).collect(Collectors.toList());
    }
}
