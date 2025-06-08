package me.tr.trDatabase.query.params.functions;

import me.tr.trDatabase.query.params.functions.aggregation.Aggregations;
import me.tr.trDatabase.query.params.functions.control.Controls;
import me.tr.trDatabase.query.params.functions.date.Dates;
import me.tr.trDatabase.query.params.functions.math.Maths;
import me.tr.trDatabase.query.params.functions.string.Strings;

public class Functions {


    public static Aggregations aggregations() {
        return Aggregations.instance();
    }

    public static Controls control() {
        return Controls.instance();
    }

    public static Dates date() {
        return Dates.instance();
    }

    public static Maths math() {
        return Maths.instance();
    }

    public static Strings string() {
        return Strings.instance();
    }

}
