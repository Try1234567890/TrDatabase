package me.tr.trDatabase.query.table;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.Query;

public class DropTable implements Query {
    private String drop = "DROP";
    private String name;

    public DropTable truncate() {
        this.drop = "TRUNCATE";
        return this;
    }

    public String name() {
        return name;
    }

    public DropTable name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toSql() {
        if (Utility.isNull(name)) {
            TrDatabase.instance().logger().error("Table name cannot be null in " + drop + " TABLE query.");
            return "";
        }
        return drop + " TABLE " + name;
    }
}
