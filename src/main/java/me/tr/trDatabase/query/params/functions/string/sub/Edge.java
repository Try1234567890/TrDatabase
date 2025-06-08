package me.tr.trDatabase.query.params.functions.string.sub;

import me.tr.trDatabase.query.params.functions.string.StringFunc;

public abstract class Edge extends StringFunc {
    private int length;

    public int length() {
        return length;
    }

    public Edge length(int length) {
        this.length = length;
        return this;
    }
}
