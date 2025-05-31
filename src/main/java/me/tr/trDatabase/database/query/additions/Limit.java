package me.tr.trDatabase.database.query.additions;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.database.types.DBType;

public class Limit {
    private int limit = 0;
    private int offset = 0;

    public Limit() {

    }

    public Limit(int limit) {
        this.limit = limit;
    }

    public Limit(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int limit() {
        return limit;
    }

    public Limit limit(int limit) {
        this.limit = limit;
        return this;
    }

    public int offset() {
        return offset;
    }

    public Limit offset(int offset) {
        this.offset = offset;
        return this;
    }

    public String execute() {
        DBType dbType = TrDatabase.main().databaseType();
        if (dbType == null) {
            return "";
        }
        int limitValue = Math.max(0, limit);
        int offsetValue = Math.max(0, offset);
        if (dbType == DBType.MYSQL) {
            // MySQL: LIMIT [offset], [limit]
            return offsetValue == 0
                    ? " LIMIT " + limitValue
                    : " LIMIT " + limitValue + ", " + offsetValue;
        } else {
            // SQLite & MARIADB: LIMIT [limit] OFFSET [offset]
            return " LIMIT " + limitValue + (offsetValue > 0 ? " OFFSET " + offsetValue : "");
        }
    }
}
