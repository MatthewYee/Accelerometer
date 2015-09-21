package com.example.evan.accelerometer;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLDataSource {

    /* Helper class to interact with database functions */

        /* Class Member Variables */
        private SQLHelper dbhelper;
        private SQLiteDatabase database;

        private String[] DATA_COLUMN = {
                SQLHelper.COLUMN_TIME,
                SQLHelper.COLUMN_XACCEL,
                SQLHelper.COLUMN_YACCEL,
                SQLHelper.COLUMN_ZACCEL,
                SQLHelper.COLUMN_XGYRO,
                SQLHelper.COLUMN_YGYRO,
                SQLHelper.COLUMN_ZGYRO
        };

        enum DATA_ENUM {
            COLUMN_TIME,
            COLUMN_XACCEL,
            COLUMN_YACCEL,
            COLUMN_ZACCEL,
            COLUMN_XGYRO,
            COLUMN_YGYRO,
            COLUMN_ZGYRO
        }

        public void open() throws SQLException {
            database = dehelper.getWritableDatabase();
        }

    public SQLDataSource(Context context) {
        dbhelper = SQLHelper.getInstance(context);
    }

    /* Close Database */
    public void close() {
        dbhelper.close();
    }

    /* Save Data */
    public void saveData(Float time, Float xaccel, Float yaccel, Float zaccel, Float xgyro, Float ygyro, String zgyro) {
        database.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(SQLHelper.COLUMN_TIME, time);
        values.put(SQLHelper.COLUMN_XACCEL, xaccel);
        values.put(SQLHelper.COLUMN_YACCEL, yaccel);
        values.put(SQLHelper.COLUMN_ZACCEL, zaccel);
        values.put(SQLHelper.COLUMN_XGYRO, xgyro);
        values.put(SQLHelper.COLUMN_YGYRO, ygyro);
        values.put(SQLHelper.COLUMN_ZGYRO, zgyro);

                database.insert(SQLHelper.TABLE_DATA, null, values);
        database.setTransactionSuccessful();
        database.endTransaction();
    }

}
