package me.tr.trDatabase.query.params.functions.control;

import me.tr.trDatabase.query.params.where.When;

import java.util.List;

public class Controls {
    private static final Controls instance = new Controls();

    public static Controls instance() {
        return instance;
    }

    private Controls() {
    }

    public Control casE(When when, String then, String otherwise, String end) {
        return new Case().when(when).then(then).otherwise(otherwise).end(end);
    }

    public Control coalesce(Object... params) {
        return new Coalesce().params(params);
    }

    public Control nullIf(Object valueLeft, Object valueRight) {
        return new NullIf().valueLeft(valueLeft).valueRight(valueRight);
    }

}
