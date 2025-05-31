package me.tr.trDatabase.database.query.additions;

public class OrderBy {
    private String column;
    private OrderByType orderByType;

    public OrderBy() {}

    public OrderBy(String column) {
        this.column = column;
    }

    public OrderBy(String column, OrderByType orderByType) {
        this.column = column;
        this.orderByType = orderByType;
    }

    public String column() {
        return column;
    }

    public OrderBy column(String column) {
        this.column = column;
        return this;
    }

    public OrderByType orderByType() {
        return orderByType;
    }

    public OrderBy orderByType(OrderByType orderByType) {
        this.orderByType = orderByType;
        return this;
    }

    public String execute() {
        switch (orderByType) {
            case ASC:
                return orderByAsc();
            case DESC:
                return orderByDesc();
            default:
                return orderBy();
        }
    }

    public String orderBy() {
        return column;
    }

    public String orderByDesc() {
        return column + " DESC";
    }

    public String orderByAsc() {
        return column + " ASC";
    }

    public enum OrderByType {
        ASC,
        DESC,
        NOTHING;
    }

}
