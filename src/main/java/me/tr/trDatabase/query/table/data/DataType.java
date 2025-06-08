package me.tr.trDatabase.query.table.data;

import java.util.Arrays;

public enum DataType {

    // Numbers
    TINYINT,
    SMALLINT,
    MEDIUMINT, // MySQL
    INT,
    INTEGER,
    BIGINT,
    DECIMAL(true),
    NUMERIC(true),
    FLOAT(true),
    REAL(true),
    DOUBLE(true),

    // Text
    CHAR(true),
    VARCHAR(true),
    TEXT,
    TINYTEXT,
    MEDIUMTEXT,
    LONGTEXT,
    NCHAR(true),
    NVARCHAR(true),
    CLOB,

    // Date and Time
    DATE,
    TIME,
    DATETIME,
    TIMESTAMP,
    YEAR,

    // Boolean
    BOOLEAN,
    BOOL,

    // Binary
    BINARY(true),
    VARBINARY(true),
    BLOB,
    TINYBLOB,
    MEDIUMBLOB,
    LONGBLOB,

    // Others
    ENUM,    // MySQL
    SET,     // MySQL
    JSON;    // MySQL


    DataType() {

    }

    private boolean hasParameters = false;

    DataType(boolean hasParameters) {
        this.hasParameters = hasParameters;
    }

    public boolean hasParameters() {
        return hasParameters;
    }

    public String toSql(int... parameters) {
        if (!hasParameters()) {
            return name();
        }
        return name() + "(" + String.join(", ", Arrays.stream(parameters).mapToObj(String::valueOf).toList()) + ")";
    }
}
