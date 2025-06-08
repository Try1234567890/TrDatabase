package me.tr.trDatabase.query.params.where;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Operator extends Condition {
    private List<Condition> right;
    private List<Condition> left;
    private List<Object> values = new ArrayList<>();

    protected Operator(List<Condition> right, @Nullable List<Condition> left) {
        this.right = right;
        this.left = left;
        right.forEach(cond -> values.addAll(cond.parameters()));
        if (left != null) {
            left.forEach(cond -> values.addAll(cond.parameters()));
        }
    }

    public List<Condition> right() {
        return right;
    }

    public Operator right(List<Condition> right) {
        this.right = right;
        return this;
    }

    public Operator right(Condition... right) {
        this.right = Arrays.asList(right);
        return this;
    }

    public List<Condition> left() {
        return left;
    }

    public Operator left(List<Condition> left) {
        this.left = left;
        return this;
    }

    public Operator left(Condition... left) {
        this.left = Arrays.asList(left);
        return this;
    }

    protected List<Object> values() {
        return values;
    }

    protected Operator values(List<Object> values) {
        this.values = values;
        return this;
    }

    @Override
    public List<Object> parameters() {
        return values;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "right=" + right +
                ", left=" + left +
                ", values=" + values +
                '}';
    }
}
