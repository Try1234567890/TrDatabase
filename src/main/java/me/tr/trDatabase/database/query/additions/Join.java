package me.tr.trDatabase.database.query.additions;

import me.tr.trDatabase.Utility;

public class Join {
    private String table;
    private String where;
    private String using;
    private JoinType type = JoinType.INNER;

    public Join(String table, String where, JoinType type, String using) {
        this.table = table;
        this.where = where;
        this.type = type;
        this.using = using;
    }
    public Join(String table, String where, JoinType type) {
        this.table = table;
        this.where = where;
        this.type = type;
    }

    public Join(String table, String where) {
        this.table = table;
        this.where = where;
    }

    public Join() {

    }

    public JoinType type() {
        return type;
    }

    public Join type(JoinType type) {
        this.type = type;
        return this;
    }

    public String table() {
        return table;
    }

    public Join table(String table) {
        this.table = table;
        return this;
    }

    public String where() {
        return where;
    }

    public Join where(String where) {
        this.where = where;
        return this;
    }

    public String execute() {
        if (Utility.isNull(table) || type == null) {
            return "";
        }
        StringBuilder query = new StringBuilder();
        query.append(" ")
                .append(type.name())
                .append(" JOIN ")
                .append(table);
        if (using != null) {
            query.append(" USING (")
                    .append(using)
                    .append(')');
        }
        if (type != JoinType.CROSS && where != null) {
            query.append(" ON ")
                    .append(where);
        }
        return query.toString();
    }

    public enum JoinType {
        INNER,
        LEFT,
        RIGHT,
        CROSS
    }
}
