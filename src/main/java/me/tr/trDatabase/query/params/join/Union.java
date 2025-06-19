package me.tr.trDatabase.query.params.join;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.query.sql.Select;

public class Union extends Join {
    private Select right;

    public Union right(Select right) {
        this.right = right;
        return this;
    }

    @Override
    public String toSql() {
        if (left() == null || right == null) {
            TrDatabase.instance().logger().error("Left and Right query cannot be null in UNION clause and " + (right == null ? "Right" : "Left") + " is null.");
            return "";
        }
        return left().toSql() + " UNION " + right.toSql() ;
    }
}
