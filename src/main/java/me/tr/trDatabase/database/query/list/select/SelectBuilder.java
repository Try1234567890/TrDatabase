package me.tr.trDatabase.database.query.list.select;

import me.tr.trDatabase.Utility;
import me.tr.trDatabase.database.query.additions.*;
import me.tr.trDatabase.database.query.list.QueryBuilder;

import java.sql.*;
import java.util.*;

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
        if (!Utility.isNull(table) && !columns.isEmpty()) {
            // --> Open Query. Query: "SELECT "
            query.append("SELECT ");
            if (distinct) {
                /*
                Add Distinct if is true. Distinct = "DISTINCT "
                Query: "SELECT DISTINCT "
                */
                query.append("DISTINCT ");
            }
            query.append(String.join(", ", columns))
                    /*
                    Add Columns to get and table. From = "users.ID, users.Name FROM users"
                    Query: "SELECT DISTINCT users.ID, users.Name FROM users"
                    */
                    .append(" FROM ")
                    .append(table);

            if (join != null) {
                /*
                Add Join if not null. Join = " INNER JOIN ordini USING (UserID) ON users.UserID = ordini.UserID"
                Query: "SELECT DISTINCT users.UserID, users.Name FROM users INNER JOIN ordini USING (UserID) ON users.UserID = ordini.UserID"
                */
                query.append(join.execute());
            }
            if (where != null) {
                /*
                Add Where if not null. Where = " WHERE ordini.Amount > 3"
                Query: "SELECT DISTINCT users.UserID, users.Name FROM users INNER JOIN ordini USING (UserID) ON users.UserID = ordini.UserID WHERE ordini.Amount > 3"
                */
                query.append(where.execute());
            }
            if (groupBy != null) {
                /*
                Add GroupBy if not null. GroupBy = " GROUP BY users.Name"
                Query: "SELECT DISTINCT users.UserID, users.Name FROM users INNER JOIN ordini USING (UserID) ON users.UserID = ordini.UserID WHERE ordini.Amount > 3 GROUP BY users.Name"
                */
                query.append(groupBy.execute());
            }
            if (having != null) {
                /*
                Add Having if not null. Having = " HAVING COUNT(ordini.ID) <= 15 and COUNT(ordini.LargeOrders) >= 15"
                Query: "SELECT DISTINCT users.UserID, users.Name FROM users INNER JOIN ordini USING (UserID) ON users.UserID = ordini.UserID WHERE ordini.Amount > 3 GROUP BY users.Name HAVING COUNT(ordini.ID) <= 15 and COUNT(ordini.LargeOrders) >= 15"
                */
                query.append(having.execute());
            }
            if (orderBy != null) {
                /*
                Add OrderBy if not null. OrderBy = " ORDER BY users.ID ASC, ordini.ID DESC"
                Query: "SELECT DISTINCT users.UserID, users.Name FROM users INNER JOIN ordini USING (UserID) ON users.UserID = ordini.UserID WHERE ordini.Amount > 3 GROUP BY users.Name HAVING COUNT(ordini.ID) <= 15 and COUNT(ordini.LargeOrders) >= 15 ORDER BY users.ID ASC, ordini.ID DESC"
                */
                query.append(" ORDER BY " + String.join(", ", orderBy.stream().map(OrderBy::execute).toList()));
            }
            if (limit != null) {
                /*
                Add Limit if not null. SQLite | MariaDB Limit = " LIMIT 10 OFFSET 5" || MySQL Limit = " LIMIT 10, 5"
                SQLite | MariaDB Query: "SELECT DISTINCT users.UserID, users.Name FROM users INNER JOIN ordini USING (UserID) ON users.UserID = ordini.UserID WHERE ordini.Amount > 3 GROUP BY users.Name HAVING COUNT(ordini.ID) <= 15 and COUNT(ordini.LargeOrders) >= 15 ORDER BY users.ID ASC, ordini.ID DESC  LIMIT 10 OFFSET 5"
                MySQL Query: "SELECT DISTINCT users.UserID, users.Name FROM users INNER JOIN ordini USING (UserID) ON users.UserID = ordini.UserID WHERE ordini.Amount > 3 GROUP BY users.Name HAVING COUNT(ordini.ID) <= 15 and COUNT(ordini.LargeOrders) >= 15 ORDER BY users.ID ASC, ordini.ID DESC  LIMIT 10, 5"
                */
                query.append(limit.execute());
            }
            query.append(";");
            /*
            Close Query.
            SQLite | MariaDB Query: "SELECT DISTINCT users.UserID, users.Name FROM users INNER JOIN ordini USING (UserID) ON users.UserID = ordini.UserID WHERE ordini.Amount > 3 GROUP BY users.Name HAVING COUNT(ordini.ID) <= 15 and COUNT(ordini.LargeOrders) >= 15 ORDER BY users.ID ASC, ordini.ID DESC LIMIT 10 OFFSET 5;"
            MySQL Query: "SELECT DISTINCT users.UserID, users.Name FROM users INNER JOIN ordini USING (UserID) ON users.UserID = ordini.UserID WHERE ordini.Amount > 3 GROUP BY users.Name HAVING COUNT(ordini.ID) <= 15 and COUNT(ordini.LargeOrders) >= 15 ORDER BY users.ID ASC, ordini.ID DESC LIMIT 10, 5;"
            */
        }
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
