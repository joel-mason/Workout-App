package com.company.joeliomason.projectme.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDatabaseAdapter{

    // Database fields
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public LoginDatabaseAdapter(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    private SQLiteDatabase open() throws SQLException {
        return database = dbHelper.getWritableDatabase();
    }

    public void close(SQLiteDatabase database) {
        database.close();
    }

    public void insertEntry(String email, String password, String fName, String sName, int height, int weight) {
        open();
        database.beginTransaction();
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put(DataBaseHelper.USER_EMAIL, email);
        newValues.put(DataBaseHelper.USER_PASSWORD, password);
        newValues.put(DataBaseHelper.USER_FIRSTNAME, fName);
        newValues.put(DataBaseHelper.USER_LASTNAME, sName);
        newValues.put(DataBaseHelper.USER_HEIGHT, height);
        newValues.put(DataBaseHelper.USER_WEIGHT, weight);

        // Insert the row into your table
        database.insert(DataBaseHelper.TABLE_USER, null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    public void deleteEntry(String email) {
        String where = "email=?";
        database.delete(DataBaseHelper.TABLE_USER, where, new String[]{email});
    }

    public String getPassword(String email)
    {
        SQLiteDatabase database = open();
        database.beginTransaction();
        String password = "";
        Cursor cursor=database.query(DataBaseHelper.TABLE_USER, null, DataBaseHelper.USER_EMAIL + "=?", new String[]{email}, null, null, null);
        if(!cursor.moveToFirst()) // UserName Not Exist
        {
            cursor.close();
        }
        for (int i = 0; i < cursor.getCount(); i++) {
            // if the email parameter is equal to a value in the table then get the password
            if (email.equals(cursor.getString(cursor.getColumnIndex(DataBaseHelper.USER_EMAIL)))) {
                password = cursor.getString(cursor.getColumnIndex(DataBaseHelper.USER_PASSWORD));

            } else {
                cursor.moveToNext();
            }
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        cursor.close();
        close(database);
        return password;
    }

    public boolean alreadyExists(String email) {
        open();
        database.beginTransaction();
        boolean xxx = false;
        Cursor cursor=database.query(DataBaseHelper.TABLE_USER, null, DataBaseHelper.USER_EMAIL + "=?", new String[]{email}, null, null, null);
        if(!cursor.moveToFirst()) // UserName Not Exist
        {
            xxx = false;
            cursor.close();
        }
        for (int i = 0; i < cursor.getCount(); i++) {
            // if the email parameter is equal to a value in the table then get the password
            if (email.equals(cursor.getString(cursor.getColumnIndex(DataBaseHelper.USER_EMAIL)))) {
                xxx = true;

            } else {
                cursor.moveToNext();
            }
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        cursor.close();
        close(database);
        return xxx;
    }

    public void updateEntry(String email, String password) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("email", email);
        updatedValues.put("password", password);

        String where = "email = ?";
        database.update("user", updatedValues, where, new String[]{email});
    }
}
