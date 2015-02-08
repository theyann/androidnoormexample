package com.techyos.noorm;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDao {

    /**
     * Static
     */

    private static final String GET_ALL_QUERY = "SELECT * FROM "
            + ContactBookContract.Contact.TABLE_NAME + " ORDER BY "
            + ContactBookContract.Contact._ID + " ASC";

    /**
     * Attributes
     */

    private SQLiteOpenHelper dbHelper;

    /**
     * Constructors
     */

    public ContactDao(SQLiteOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    /**
     * Public Methods
     */

    public void addContact(String firstName, String lastName) {
        ContentValues values = new ContentValues();
        values.put(ContactBookContract.Contact.COLUMN_NAME_FIRST_NAME, firstName);
        values.put(ContactBookContract.Contact.COLUMN_NAME_LAST_NAME, lastName);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(ContactBookContract.Contact.TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getAllCursor() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.rawQuery(GET_ALL_QUERY, null);
    }

    public void deleteContact(String id) {
        String selection = ContactBookContract.Contact._ID + " LIKE ?";
        String[] selectionArgs = { id };

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(ContactBookContract.Contact.TABLE_NAME, selection, selectionArgs);
        db.close();
    }
}
