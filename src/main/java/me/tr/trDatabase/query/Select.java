package me.tr.trDatabase.query;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.Column;
import me.tr.trDatabase.query.params.functions.Function;
import me.tr.trDatabase.query.params.groupby.GroupBy;
import me.tr.trDatabase.query.params.having.Having;
import me.tr.trDatabase.query.params.join.Join;
import me.tr.trDatabase.query.params.limit.Limit;
import me.tr.trDatabase.query.params.orderby.OrderBy;
import me.tr.trDatabase.query.params.where.Condition;
import me.tr.trDatabase.query.params.where.Where;
import me.tr.trDatabase.query.table.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Select implements Query {
    private final String select = "SELECT ";
    private final List<Column> columns = new ArrayList<>();
    private Table table;
    private Join[] join;
    private Where where;
    private GroupBy groupBy;
    private Having having;
    private OrderBy orderBy;
    private Limit limit;
    private final List<Object> parameters = new ArrayList<>();

    public List<Column> columns() {
        return columns;
    }

    public Select columns(String... columns) {
        this.columns.addAll(Arrays.stream(columns).map(Column::of).toList());
        return this;
    }

    public Select columns(Column... columns) {
        this.columns.addAll(List.of(columns));
        return this;
    }


    public Select columns(Function... fun) {
        this.columns.addAll(Arrays.stream(fun).map(Column::of).toList());
        return this;
    }

    public Table table() {
        return table;
    }

    public Select table(Table table) {
        this.table = table;
        return this;
    }

    public Select from(Table table) {
        this.table = table;
        return this;
    }

    public Join[] join() {
        return join;
    }

    public Select join(Join... join) {
        this.join = Arrays.stream(join).map(j -> j.left(this)).toArray(Join[]::new);
        return this;
    }

    public Condition[] where() {
        return where.conditions().toArray(new Condition[0]);
    }

    public Select where(Condition... conditions) {
        Where where = new Where();
        for (Condition condition : conditions) {
            where.condition(condition);
        }
        this.where = where;
        return this;
    }

    public GroupBy groupBy() {
        return groupBy;
    }

    public Select groupBy(GroupBy groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    public Having having() {
        return having;
    }

    public Select having(Having having) {
        this.having = having;
        return this;
    }

    public OrderBy orderBy() {
        return orderBy;
    }

    public Select orderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public Limit limit() {
        return limit;
    }

    public Select limit(Limit limit) {
        this.limit = limit;
        return this;
    }

    public String toSql() {
        if (columns.isEmpty()) {
            TrDatabase.instance().logger().error("Columns cannot be null in SELECT query.");
            return "";
        }
        if (table == null) {
            TrDatabase.instance().logger().error("Table cannot be null in SELECT query.");
            return "";
        }
        String sql = select + String.join(", ", columns.stream().map(Column::toSql).toList()) + ' ' + "FROM " + table.toSql() + (join != null ? String.join("", Arrays.stream(join).map(Join::toSql).toList()) : "") + (where != null ? where.toSql() : "") + (groupBy != null ? groupBy.toSql() : "") + (having != null ? having.toSql() : "") + (orderBy != null ? orderBy.toSql() : "") + (limit != null ? limit.toSql() : "") + ';';
        int qMAmount = Utility.countChars(sql, '?');
        int parameterSize = parameters.size();
        if (qMAmount != parameterSize) {
            TrDatabase.instance().logger().error("SQL parameters not correspond.\n Question Marks: " + qMAmount + "\n Parameters: " + parameters + " (" + parameterSize + ')');
            return "";
        }
        return sql;
    }

    public List<Object> parameters() {
        if (where != null) {
            parameters.addAll(where.parameters());
        }
        if (join != null) {
            this.parameters.addAll(Arrays.stream(join).map(Join::parameters).flatMap(List::stream).toList());
        }
        if (having != null) {
            this.parameters.addAll(having.parameters());
        }
        if (orderBy != null) {
            this.parameters.addAll(orderBy.parameters());
        }
        return parameters;
    }


    @Override
    public String toString() {
        return "Select{" +
                "select='" + select + '\'' +
                ", columns='" + columns + '\'' +
                ", table='" + table + '\'' +
                ", where='" + where + '\'' +
                '}';
    }
}
