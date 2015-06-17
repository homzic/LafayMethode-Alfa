package com.kris.lm.DB;


class Table_Training {
    private static final String UID = "_id";
    public static final String TABLE_NAME = "table_training";
    private static final String ID_TRAINING = "ID_training";
    private static final String DATE = "date";
    private static final String LEVEL = "level";
    private static final String SERIA = "seria";
    private static final String REPEATS = "repeats";
    private static final String EXC_ID = "exc_ID";

    public static final String CREATE_TABLE_TRAINING =
            "CREATE TABLE " + TABLE_NAME
                    + " (" +
                    UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ID_TRAINING + " INT, "
                    + DATE + " DATETIME, "
                    + LEVEL + " TEXT, "
                    + EXC_ID + " VARCHAR, "
                    + REPEATS + " INT, "
                    + SERIA + " INT, " +
                    "FOREIGN KEY(" + Table_Exercises.EXC_ID + " ) REFERENCES "
                    + Table_Exercises.TABLE_NAME + " (" + EXC_ID + ") ON DELETE CASCADE" +
                    ");";

}
