package me.tr.trDatabase.database.types;

import me.tr.trDatabase.Utility;
import me.tr.trDatabase.database.Database;

public enum DBType {

    MYSQL(),
    MARIADB(),
    SQLITE();


    public static DBType fromString(String input) {
        if (Utility.isNull(input)) return null;
        for (DBType dbType : DBType.values()) {
            if (dbType.name().equalsIgnoreCase(input))
                return dbType;
        }
        return null;
    }

}
