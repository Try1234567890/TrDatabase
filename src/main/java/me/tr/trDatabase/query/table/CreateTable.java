package me.tr.trDatabase.query.table;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.Query;
import me.tr.trDatabase.query.Select;
import me.tr.trDatabase.query.constraints.Constraint;
import me.tr.trDatabase.query.table.data.ColumnData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateTable implements Query {
    private String name = "";
    private String notExists = "";
    private ColumnData[] columns;
    private Select select;
    private Constraint[] constraints;

    public String name() {
        return name;
    }

    public CreateTable name(String name) {
        this.name = name;
        return this;
    }

    public CreateTable ifNotExists() {
        this.notExists = "IF NOT EXISTS ";
        return this;
    }

    public CreateTable columns(ColumnData... columns) {
        this.columns = columns;
        return this;
    }

    public CreateTable copy(Select select) {
        this.select = select;
        return this;
    }

    public CreateTable constraints(Constraint... constraints) {
        this.constraints = constraints;
        return this;
    }


    public String toSql() {
        if (Utility.isNull(name)) {
            TrDatabase.instance().logger().error("Table name cannot be null in CREATE TABLE query.");
            return "";
        }
        return "CREATE TABLE " + notExists + name + " (" +
                (columns != null ? String.join(", ", Arrays.stream(columns).map(ColumnData::toSql).toList()) : "") +
                (constraints != null ? ',' + String.join(", ", Arrays.stream(constraints).map(Constraint::toSql).toList())  + ")" : ")");
    }

    public List<Object> parameters() {
        final List<Object> parameters = new ArrayList<>();
        for (Constraint constraint : constraints) {
            parameters.addAll(constraint.parameters());
        }
        return parameters;
    }

    @Override
    public String toString() {
        return "CreateTable{" +
                "name='" + name + '\'' +
                ", notExists='" + notExists + '\'' +
                ", columns=" + Arrays.toString(columns) +
                ", select=" + select +
                ", constraints=" + Arrays.toString(constraints) +
                '}';
    }
}
