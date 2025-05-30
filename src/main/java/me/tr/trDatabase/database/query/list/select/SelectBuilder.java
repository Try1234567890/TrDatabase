package me.tr.trDatabase.database.query.list.select;

import me.tr.trDatabase.database.query.additions.*;
import me.tr.trDatabase.database.query.list.QueryBuilder;

import java.sql.*;
import java.util.*;

/*
 * SELECT ...
 * FROM ...
 * JOIN ...
 * WHERE ...
 * GROUP BY ...
 * HAVING ...
 * ORDER BY ...
 * LIMIT ...
 */
public class SelectBuilder extends QueryBuilder {
    private String table;
    private boolean distinct;
    private List<String> columns = new ArrayList<>();
    private Join join;
    private Where where;
    private GroupBy groupBy;
    private Having having;
    private List<OrderBy> orderBy;
    private Limit limit;

    public SelectBuilder(Connection connection) {
        super(connection);
    }

    public SelectBuilder table(String table) {
        this.table = table;
        return this;
    }

    public SelectBuilder columns(String... columns) {
        this.columns.addAll(Arrays.stream(columns).toList());
        return this;
    }

    public SelectBuilder where(String where, Object... value) {
        this.where = new Where(where, value);
        return this;
    }

    public SelectBuilder where(Where where) {
        this.where = where;
        return this;
    }

    public SelectBuilder orderBy(OrderBy... orderBy) {
        this.orderBy = List.of(orderBy);
        return this;
    }

    public SelectBuilder distinct(boolean distinct) {
        this.distinct = distinct;
        return this;
    }

    public SelectBuilder join(Join join) {
        this.join = join;
        return this;
    }

    public SelectBuilder limit(Limit limit) {
        this.limit = limit;
        return this;
    }

    public SelectBuilder groupBy(GroupBy groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    public SelectBuilder having(Having having) {
        this.having = having;
        return this;
    }

    public SelectResult execute() {
        List<Map<String, Object>> results = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        if (distinct)
            query.append("DISTINCT ");
        query.append(String.join(", ", columns))
                .append(" FROM ")
                .append(table);
        if (join != null)
            query.append(join.execute());
        if (where != null)
            query.append(" WHERE ").append(where);
        if (groupBy != null)
            query.append(groupBy.execute());
        if (having != null)
            query.append(having.execute());
        if (orderBy != null)
            query.append(" ORDER BY " + String.join(", ", orderBy.stream().map(OrderBy::execute).toList()));
        if (limit != null)
            query.append(limit.execute());
        query.append(";");
        try (PreparedStatement st = connection().prepareStatement(query.toString())) {
            if (where != null) {
                for (int i = 0; i < where.values().size(); i++) {
                    st.setObject(i + 1, where.values().get(i));
                }
            }
            try (ResultSet rs = st.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnName(i), rs.getObject(i));
                    }
                    results.add(row);
                }
            }
        } catch (SQLException e) {
            main.logger().error("Error while executing query " + query + ": [ExceptionMessage]", e);
        }
        return new SelectResult(results);
    }
}
