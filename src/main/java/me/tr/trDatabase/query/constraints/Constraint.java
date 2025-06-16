package me.tr.trDatabase.query.constraints;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.Query;
import me.tr.trDatabase.query.params.where.Condition;

import java.util.ArrayList;
import java.util.List;

public abstract class Constraint implements Query {
    private String name;
    private ConstraintType type;
    private String[] columns;
    private List<Object> parameters = new ArrayList<>();

    protected Constraint() {}

    protected Constraint(ConstraintType type) {
        this.type = type;
    }

    public String name() {
        return name;
    }

    public Constraint name(String name) {
        this.name = name;
        return this;
    }

    public ConstraintType type() {
        return type;
    }

    public Constraint type(ConstraintType type) {
        this.type = type;
        return this;
    }

    public String[] columns() {
        return columns;
    }

    public Constraint columns(String[] columns) {
        this.columns = columns;
        return this;
    }

    public static Constraint unique(String name, String... columns) {
        return new Unique().name(name).columns(columns);
    }

    public static Constraint primaryKey(String name, String... columns) {
        return new PrimaryKey().name(name).columns(columns);
    }

    public static Constraint foreignKey(String name, String referenceName, String... columns) {
        return new ForeignKey().referenceName(name).columns(columns).name(referenceName);
    }

    protected Constraint parameters(List<Object> parameters) {
        this.parameters = parameters;
        return this;
    }

    public List<Object> parameters() {
        return parameters;
    }

    public static Constraint check(String name, Condition... conditions) {
        final List<Object> parameters = new ArrayList<>();
        for (Condition condition : conditions) {
            parameters.addAll(condition.parameters());
        }
        return new Check().conditions(conditions).name(name).parameters(parameters);
    }

    @Override
    public String toSql() {
        if (columns == null || columns.length == 0) {
            TrDatabase.instance().logger().error("No columns specified for constraint " + name() + (type() != null ? ": " + type().name() : ""));
            return "";
        }
        if (Utility.isNull(name)) {
            return type.name() + " (" + String.join(", ", columns) + ')';
        }
        return "CONSTRAINT " + name + ' ' + type.name().replace('_', ' ') + " (" + String.join(", ", columns) + ')';
    }
}
