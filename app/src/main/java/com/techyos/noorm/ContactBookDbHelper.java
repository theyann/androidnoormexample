package com.techyos.noorm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactBookDbHelper extends SQLiteOpenHelper {

    /**
     * Static
     */

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NoORM.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ContactBookContract.Contact.TABLE_NAME + " (" +
                    ContactBookContract.Contact._ID + " INTEGER PRIMARY KEY," +
                    ContactBookContract.Contact.COLUMN_NAME_FIRST_NAME + TEXT_TYPE + "," +
                    ContactBookContract.Contact.COLUMN_NAME_LAST_NAME + TEXT_TYPE + ")";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ContactBookContract.Contact.TABLE_NAME;

    /**
     * Constructors
     */

    public ContactBookDbHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Lifecycle
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
