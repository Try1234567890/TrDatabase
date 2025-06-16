package me.tr.trDatabase.query.params.functions.control;

import me.tr.trDatabase.TrDatabase;
import me.tr.trDatabase.Utility;
import me.tr.trDatabase.query.params.where.When;

public class Case extends Control {
    private When when;
    private String then;
    private String otherwise;
    private String end;

    public When when() {
        return when;
    }

    public Case when(When when) {
        this.when = when;
        return this;
    }

    public String then() {
        return then;
    }

    public Case then(String then) {
        this.then = Utility.checkQuotes(then);
        return this;
    }

    public String otherwise() {
        return otherwise;
    }

    public Case otherwise(String otherwise) {
        this.otherwise = Utility.checkQuotes(otherwise);
        return this;
    }

    public String end() {
        return end;
    }

    public Case end(String end) {
        this.end = Utility.checkQuotes(end);
        return this;
    }

    @Override
    public String toSql() {
        if (when == null) {
            TrDatabase.instance().logger().error("\"When\" condition cannot be null in case functions");
            return "";
        }
        if (Utility.isNull(then)) {
            TrDatabase.instance().logger().error("\"Then\" cannot be null in case functions");
            return "";
        }
        if (Utility.isNull(otherwise)) {
            TrDatabase.instance().logger().error("\"Otherwise\" cannot be null in case functions");
            return "";
        }
        if (Utility.isNull(end)) {
            TrDatabase.instance().logger().error("\"End\" cannot be null in case functions");
            return "";
        }
        return "CASE " + when.toSql() + ' ' + " THEN " + then + " ELSE " + otherwise + " END AS " + end;
    }
}
