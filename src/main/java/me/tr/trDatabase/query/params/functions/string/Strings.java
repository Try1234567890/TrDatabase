package me.tr.trDatabase.query.params.functions.string;

import me.tr.trDatabase.query.params.functions.string.cases.Lower;
import me.tr.trDatabase.query.params.functions.string.cases.Upper;
import me.tr.trDatabase.query.params.functions.string.length.Len;
import me.tr.trDatabase.query.params.functions.string.length.Length;
import me.tr.trDatabase.query.params.functions.string.sub.Left;
import me.tr.trDatabase.query.params.functions.string.sub.Position;
import me.tr.trDatabase.query.params.functions.string.sub.Right;
import me.tr.trDatabase.query.params.functions.string.sub.SubString;

public class Strings {
    private static final Strings instance = new Strings();

    public static Strings instance() {
        return instance;
    }

    private Strings() {
    }

    public StringFunc lower(String str) {
        return new Lower().str(str);
    }

    public StringFunc upper(String str) {
        return new Upper().str(str);
    }

    public StringFunc len(String str) {
        return new Len().str(str);
    }

    public StringFunc length(String str) {
        return new Length().str(str);
    }

    public StringFunc left(String str, int length) {
        return new Left().length(length).str(str);
    }

    public StringFunc position(String str, String sub) {
        return new Position().sub(sub).str(str);
    }

    public StringFunc right(String str, int length) {
        return new Right().length(length).str(str);
    }

    public StringFunc subString(String str, int start, int end) {
        return new SubString().start(start).length(end).str(str);
    }

    public StringFunc concat(String... strings) {
        return new Concat().strings(strings);
    }

    public StringFunc replace(String str, String from, String to) {
        return new Replace().from(from).to(to).str(str);
    }

    public StringFunc trim(String str) {
        return new Trim().str(str);
    }

}
