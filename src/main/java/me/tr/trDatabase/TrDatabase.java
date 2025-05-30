package me.tr.trDatabase;

import me.tr.trDatabase.database.types.DBType;
import me.tr.trLogger.logger.ConsoleLogger;

public class TrDatabase {
    private static TrDatabase main;
    private ConsoleLogger logger;
    private DBType databaseType;

    public TrDatabase() {
        main = this;
        logger = new ConsoleLogger();
    }


    public static TrDatabase main() {
        return main;
    }

    public ConsoleLogger logger() {
        return logger;
    }

    public TrDatabase databaseType(DBType databaseType) {
        this.databaseType = databaseType;
        return this;
    }

    public DBType databaseType() {
        return databaseType;
    }
}
