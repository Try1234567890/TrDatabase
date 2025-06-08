package me.tr.trDatabase.query.table.data;

import me.tr.trDatabase.query.params.Column;

public class ColumnData {
    private String column;
    private DataType dataType;
    private int[] parameters;

    private ColumnData() {
    }

    public String column() {
        return column;
    }

    public DataType dataType() {
        return dataType;
    }

    public int[] parameters() {
        return parameters;
    }

    private ColumnData column(Column column) {
        this.column = column.name();
        return this;
    }

    private ColumnData column(String column) {
        this.column = column;
        return this;
    }

    private ColumnData dataType(DataType dataType) {
        this.dataType = dataType;
        return this;
    }

    private ColumnData parameters(int... parameters) {
        this.parameters = parameters;
        return this;
    }


    public static ColumnData of(Column column, DataType dataType, int... parameters) {
        return new ColumnData()
                .column(column)
                .dataType(dataType)
                .parameters(parameters);
    }

    public static ColumnData of(String column, DataType dataType, int... parameters) {
        return new ColumnData()
                .column(column)
                .dataType(dataType)
                .parameters(parameters);
    }

    public String toSql() {
        return column + " " + dataType.toSql(parameters);
    }

}
