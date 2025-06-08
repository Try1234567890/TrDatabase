package me.tr.trDatabase.query.params.functions.math;

import me.tr.trDatabase.query.params.functions.Function;

public abstract class Math implements Function {
    private int number;

    public int number() {
        return number;
    }

    public Math number(int number) {
        this.number = number;
        return this;
    }
}
