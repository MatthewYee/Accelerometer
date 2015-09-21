package com.example.evan.accelerometer;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SQLHelper extends SQLiteOpenHelper{

    private static SQLHelper sInstance;
    private final static String DATABASE_NAME = "dbDroidSen.db";

    /* Table Name */
    public static final String TABLE_DATA = "tbData";
    private static final int DATABASE_VERSION = 1;

    public static synchronized SQLHelper getInstance(Context context) {
        /* Singleton */
        if (sInstance == null) {
            sInstance = new SQLHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /* Data Table Columns */
    public static final String COLUMN_TIME = "floatTIME";
    public static final String COLUMN_XACCEL = "floatXACCEL";
    public static final String COLUMN_YACCEL = "floatYACCEL";
    public static final String COLUMN_ZACCEL = "floatZACCEL";
    public static final String COLUMN_XGYRO = "floatXGYRO";
    public static final String COLUMN_YGYRO = "floatYGYRO";
    public static final String COLUMN_ZGYRO = "floatZGYRO";

    /* Create Table Data */
    privat static String TABLE_DATA_CREATE = "CREATE TABLE "
            + TABLE_DATA
            + "("
            + COLUMN_TIME + " FLOAT NOT NULL, "
            + COLUMN_XACCEL + " FLOAT NOT NULL, "
            + COLUMN_YACCEL + " FLOAT NOT NULL, "
            + COLUMN_ZACCEL + " FLOAT NOT NULL, "
            + COLUMN_XGYRO + " FLOAT NOT NULL, "
            + COLUMN_YGYRO + " FLOAT NOT NULL, "
            + COLUMN_ZGYRO + " FLOAT NOT NULL "
            + " );";

    private SQLHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(TABLE_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        onCreate(db);
    }
}
