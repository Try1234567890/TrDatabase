package me.tr.trDatabase.database;

import me.tr.trDatabase.Utility;
import org.jetbrains.annotations.Nullable;

/**
 * This enum contains all database supported.
 */
public enum DBType {

    MYSQL(),
    MARIADB(),
    SQLITE();

    /**
     * Get {@link DBType} from a String, if possible.
     *
     * @param input String to get {@link DBType} from.
     * @return an {@link DBType} if is found, otherwise <code>null</code>
     */
    public static @Nullable DBType fromString(String input) {
        if (Utility.isNull(input)) return null;
        for (DBType dbType : DBType.values()) {
            if (dbType.name().equalsIgnoreCase(input))
                return dbType;
        }
        return null;
    }
}
