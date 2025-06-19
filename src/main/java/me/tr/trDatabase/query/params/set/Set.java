package me.tr.trDatabase.query.params.set;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.Query;
import me.tr.trDatabase.query.params.functions.control.Case;
import me.tr.trDatabase.query.sql.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Set implements Query {
    private List<String> variables = new ArrayList<>();
    private List<String> values = new ArrayList<>();

    public List<String> variables() {
        return variables;
    }

    public Set variables(List<String> variables) {
        this.variables.addAll(variables);
        return this;
    }


    public Set variables(String... variables) {
        this.variables.addAll(List.of(variables));
        return this;
    }

    public List<String> values() {
        return values;
    }

    public Set values(Object... values) {
        this.values.addAll(Arrays.stream(values).map(Object::toString).toList());
        return this;
    }

    public Set values(String... values) {
        this.values.addAll(Arrays.stream(values).map(Utility::checkQuotes).toList());
        return this;
    }

    public Set values(Case... values) {
        this.values.addAll(Arrays.stream(values).map(Case::toSql).toList());
        return this;
    }

    public Set values(Select... values) {
        this.values.addAll(Arrays.stream(values).map(Select::toSql).toList());
        return this;
    }

    public static Set of() {
        return new Set();
    }

    public static Set of(List<String> variables, Object... values) {
        return new Set().variables(variables).values(values);
    }

    public static Set of(List<String> variables, String... values) {
        return new Set().variables(variables).values(values);
    }

    public static Set of(List<String> variables, Case... values) {
        return new Set().variables(variables).values(values);
    }

    public static Set of(List<String> variables, Select... values) {
        return new Set().variables(variables).values(values);
    }

    @Override
    public String toSql() {
        if (variables.isEmpty() || values.isEmpty()) {
            TrDatabase.instance().logger().error("Variables and Values cannot be null or empty and " + (variables.isEmpty() ? "Variables" : "Values") + " is empty.");
            return "";
        }
        if (variables.size() != values.size()) {
            TrDatabase.instance().logger().error("Variables and Values length is not equals");
            return "";
        }
        return "SET " + String.join(", ", mapVariablesAndValues());
    }


    private List<String> mapVariablesAndValues() {
        final List<String> result = new ArrayList<>();
        for (String variable : variables) {
            for (String value : values) {
                result.add(variable + " = " + value);
            }
        }
        return result;
    }
}
