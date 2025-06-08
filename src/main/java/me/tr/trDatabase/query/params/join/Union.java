package me.tr.trDatabase.query.params.join;

import me.tr.trDatabase.query.Select;

public class Union extends Join {
    private Select right;

    public Union right(Select right) {
        this.right = right;
        return this;
    }

    @Override
    public String toSql() {
        return left().toSql() + " UNION " + right.toSql() ;
    }
}
