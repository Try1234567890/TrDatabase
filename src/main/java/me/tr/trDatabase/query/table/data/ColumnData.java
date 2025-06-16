package me.tr.trDatabase.query.table.data;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.constraints.columns.ColumnConstraint;
import me.tr.trDatabase.query.params.Column;

import java.util.Arrays;

public class ColumnData {
    private String column;
    private DataType dataType;
    private int[] parameters;
    private ColumnConstraint[] constraints;

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

    public ColumnConstraint[] constraints() {
        return constraints;
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

    public ColumnData constraints(ColumnConstraint... constraints) {
        this.constraints = constraints;
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
        if (Utility.isNull(column)) {
            TrDatabase.instance().logger().error("Column cannot be null in ColumnData.");
            return "";
        }
        if (dataType == null) {
            TrDatabase.instance().logger().error("DataType cannot be null in ColumnData.");
            return "";
        }
        if (dataType.hasParameters() && parameters.length == 0) {
            TrDatabase.instance().logger().error(dataType.name() + " requires parameters and no parameters has specified.");
            return "";
        }
        return column + ' ' + dataType.toSql(parameters) + (constraints != null ? String.join("", Arrays.stream(constraints).map(ColumnConstraint::toSql).toList()) : "");
    }

}
