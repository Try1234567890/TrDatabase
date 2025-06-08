package me.tr.trDatabase;

import me.tr.trDatabase.database.DBType;
import me.tr.trLogger.logger.ConsoleLogger;

public class TrDatabase {
    private static TrDatabase instance;
    private ConsoleLogger logger;
    private DBType dbType;

    private TrDatabase() {
        instance = this;
    }


    public static TrDatabase instance() {
        if (instance == null) {
            instance = new TrDatabase();
        }
        return instance;
    }

    public ConsoleLogger logger() {
        if (logger == null) {
            logger = new ConsoleLogger();
        }
        return logger;
    }

    public TrDatabase dbType(DBType dbType) {
        this.dbType = dbType;
        return this;
    }

    public DBType dbType() {
        return dbType;
    }
}
