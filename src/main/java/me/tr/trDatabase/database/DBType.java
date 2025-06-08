package me.tr.trDatabase.database;

import me.tr.trDatabase.Utility;
import me.tr.trDatabase.files.SectionProperty;
import me.tr.trDatabase.files.ValueRetriever;
import me.tr.trFiles.configuration.Section;

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

    public Database connect(Section section) {
        final String host = DBKeys.HOST.from(section).asString();
        final int port = DBKeys.PORT.from(section).asInt();
        final String name = DBKeys.NAME.from(section).asString();
        final String user = DBKeys.USER.from(section).asString();
        final String password = DBKeys.PASS.from(section).asString();
        return switch (this) {
            case MYSQL -> new MySQL(host, port, name, user, password);
            case MARIADB -> new MariaDB(host, port, name, user, password);

            default -> new SQLite(DBKeys.PATH.from(section).asString());
        };
    }

    public enum DBKeys {

        HOST("127.0.0.1", "ip", "host", "address"),
        PORT(3306, "port"),
        NAME("database", "name", "db", "database"),
        USER("root", "user", "username"),
        PASS("", "pass", "password"),
        PATH(System.getProperty("user.dir"), "path", "db", "database", "file");


        private final Object def;
        private final String[] keys;
        private final ValueRetriever retriever = new ValueRetriever();

        DBKeys(Object def, String... keys) {
            this.def = def;
            this.keys = keys;
        }

        public Object def() {
            return def;
        }

        public String[] keys() {
            return keys;
        }

        public ValueRetriever from(Section section) {
            retriever.sectionProperty(new SectionProperty(section, keys, def));
            return retriever;
        }
    }

}
