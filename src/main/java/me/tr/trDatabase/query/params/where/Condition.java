package me.tr.trDatabase.query.params.where;

import me.tr.trDatabase.query.params.Column;
import me.tr.trDatabase.query.params.functions.Function;

import java.util.List;

public abstract class Condition {

    public static Condition equals(String col, Object val) {
        return new Equals().column(col).value(val);
    }

    public static Condition equals(String col, Column val) {
        return new Equals().column(col).value(val);
    }

    public static Condition equals(String col) {
        return new Equals().column(col).value(null);
    }

    public static Condition greaterThan(String col, Object val) {
        return new GreaterThan().column(col).value(val);
    }

    public static Condition greaterThanOrEqual(String col, Object val) {
        return new GreaterThanOrEquals().column(col).value(val);
    }

    public static Condition lessThan(String col, Object val) {
        return new LessThan().column(col).value(val);
    }

    public static Condition lessThanOrEqual(String col, Object val) {
        return new LessThanOrEquals().column(col).value(val);
    }

    public static Condition like(String col, String pattern) {
        return new Like().column(col).value(pattern);
    }

    public static Condition in(String col, List<Object> values) {
        return new In().column(col).values(values);
    }

    public static Condition between(String col, Object start, Object end) {
        return new Between().column(col).value(start, end);
    }

    public static Condition isNull(String col) {
        return new Null().column(col);
    }


    public static Condition equals(Column col, Object val) {
        return new Equals().column(col).value(val);
    }

    public static Condition equals(Column col, Column val) {
        return new Equals().column(col).value(val.toSql());
    }

    public static Condition equals(Column col) {
        return new Equals().column(col).value(null);
    }

    public static Condition greaterThan(Column col, Object val) {
        return new GreaterThan().column(col).value(val);
    }

    public static Condition greaterThanOrEqual(Column col, Object val) {
        return new GreaterThanOrEquals().column(col).value(val);
    }

    public static Condition lessThan(Column col, Object val) {
        return new LessThan().column(col).value(val);
    }

    public static Condition lessThanOrEqual(Column col, Object val) {
        return new LessThanOrEquals().column(col).value(val);
    }

    public static Condition like(Column col, String pattern) {
        return new Like().column(col).value(pattern);
    }

    public static Condition in(Column col, List<Object> values) {
        return new In().column(col).values(values);
    }

    public static Condition between(Column col, Object start, Object end) {
        return new Between().column(col).value(start, end);
    }

    public static Condition isNull(Column col) {
        return new Null().column(col);
    }

    public static Condition equals(Function col, Column val) {
        return new Equals().column(col.toSql()).value(val.toSql());
    }

    public static Condition equals(Function col, Object val) {
        return new Equals().column(col.toSql()).value(val);
    }

    public static Condition equals(Function col) {
        return new Equals().column(col.toSql()).value(null);
    }

    public static Condition greaterThan(Function col, Object val) {
        return new GreaterThan().column(col.toSql()).value(val);
    }

    public static Condition greaterThanOrEqual(Function col, Object val) {
        return new GreaterThanOrEquals().column(col.toSql()).value(val);
    }

    public static Condition lessThan(Function col, Object val) {
        return new LessThan().column(col.toSql()).value(val);
    }

    public static Condition lessThanOrEqual(Function col, Object val) {
        return new LessThanOrEquals().column(col.toSql()).value(val);
    }

    public static Condition like(Function col, String pattern) {
        return new Like().column(col.toSql()).value(pattern);
    }

    public static Condition in(Function col, List<Object> values) {
        return new In().column(col.toSql()).values(values);
    }

    public static Condition between(Function col, Object start, Object end) {
        return new Between().column(col.toSql()).value(start, end);
    }

    public static Condition isNull(Function col) {
        return new Null().column(col.toSql());
    }

    public Condition not() {
        return new Not(this);
    }

    public Condition and(Condition other) {
        return new And(this, other);
    }

    public Condition or(Condition other) {
        return new Or(this, other);
    }

    public abstract String toSql();

    public abstract List<Object> parameters();
}
