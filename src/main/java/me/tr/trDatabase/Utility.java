package me.tr.trDatabase;

public class Utility {


    public static boolean isNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static int countChars(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public static String checkQuotes(String str) {
        if (str == null) return "";
        if (!str.startsWith("'")) {
            str = '\'' + str;
        }
        if (!str.endsWith("'")) {
            str = str + '\'';
        }
        return str;
    }

}
