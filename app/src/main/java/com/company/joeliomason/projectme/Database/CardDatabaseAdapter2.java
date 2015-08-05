package com.company.joeliomason.projectme.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.company.joeliomason.projectme.POJOs.Card;
import com.company.joeliomason.projectme.POJOs.Set;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joelmason on 12/04/2015.
 */
public class CardDatabaseAdapter2 {

    // Database fields
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private String[] allColumns = { DataBaseHelper.CARD_ID,
            DataBaseHelper.CARD_NAME, DataBaseHelper.CARD_DATE};
    private String[] allColumns2 = { DataBaseHelper.CARD_INFO_ID, DataBaseHelper.CARD_ID2,
            DataBaseHelper.CARD_NAME2, DataBaseHelper.CARD_WEIGHT, DataBaseHelper.CARD_REPS, DataBaseHelper.CARD_DATE2, DataBaseHelper.CARD_CATEGORY};
    int flag = 0;

    public CardDatabaseAdapter2(Context context) {
        dbHelper = new DataBaseHelper(context);
    }


    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String date) {
        open();
        database.beginTransaction();
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put(DataBaseHelper.CARD_NAME, name);
        newValues.put(DataBaseHelper.CARD_DATE, date);

        // Insert the row into your table
        database.insert(DataBaseHelper.CARD_TABLE, null, newValues);
        database.setTransactionSuccessful();
        database.endTransaction();
        close();
    }

    public void insert2(long id, String name, double weight, int reps, String date, int category) {
        Log.v("id", id + "");
        open();
        database.beginTransaction();
        ContentValues newValues2 = new ContentValues();
        // Assign values for each row.
        newValues2.put(DataBaseHelper.CARD_ID2, id);
        newValues2.put(DataBaseHelper.CARD_NAME2, name);
        newValues2.put(DataBaseHelper.CARD_WEIGHT, weight);
        newValues2.put(DataBaseHelper.CARD_REPS, reps);
        newValues2.put(DataBaseHelper.CARD_DATE2, date);
        newValues2.put(DataBaseHelper.CARD_CATEGORY, category);

        // Insert the row into your table
        database.insert(DataBaseHelper.CARD_INFO_TABLE, null, newValues2);
        database.setTransactionSuccessful();
        database.endTransaction();
        close();
    }

    public List<Card> getAllInfo2(String date) {
        Log.v("date", date);
        List<Set> ls;
        List<Card> ls2 = new ArrayList<>();
        open();
        Cursor cursor = database.query(DataBaseHelper.CARD_TABLE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            String foo = cursor.getString(1);
            //Log.v("foo", foo);
            Card card = new Card(cursor.getLong(0), foo, cursor.getString(2));
            ls = getValuesDates(cursor.getLong(0), date);
            //Log.v("values of ls", ls.toString());
            if (!ls.isEmpty()) {
                for (Set temp : ls) {
                    card.addSet(temp);
                    //Log.v("forloop", temp.toString());
                }
            }
            ls2.add(card);
            Log.v("Card " + cursor.getInt(0), card.toString());
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return ls2;
    }

    public List<Set> getValuesDates(long id, String date) {
        List<Set> ls = new ArrayList<>();
        Set set;
        open();
        Cursor cursor = database.query(DataBaseHelper.CARD_INFO_TABLE,
                allColumns2, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (id == cursor.getInt(1) && date.equals(cursor.getString(5))) {

                set = new Set(cursor.getLong(0), cursor.getString(2), cursor.getDouble(3), cursor.getInt(4), cursor.getString(5), cursor.getInt(6));
                ls.add(set);
                //Log.d("Got DB:", set.toString());
            }
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        close();
        return ls;
    }


    public long highestID() {
        long id = 0;
        long previous = 0;
        open();
        database.beginTransaction();
        Cursor c = database.query(DataBaseHelper.CARD_TABLE, allColumns, null, null, null, null, null);
        c.moveToFirst();
        for(int i = 0; i < c.getCount(); i++) {
            if (c.getInt(0) > previous) {
                previous = c.getInt(0);
                id = previous;
            } else {
                id = 0;
            }
            c.moveToNext();
        }
        c.close();
        database.setTransactionSuccessful();
        database.endTransaction();
        close();
        return id;

    }

    public void resetID() {
        flag = 0;
    }


    public void deleteEntry(String id) {
        open();
        database.beginTransaction();
        String where = DataBaseHelper.CARD_INFO_ID + " = ?";
        //database.execSQL("DELETE FROM cardinfo WHERE card_ID2 = ? AND setNo = ?", new String[]{String.valueOf(id), String.valueOf(setNo) });
        database.delete(DataBaseHelper.CARD_INFO_TABLE, where, new String[]{id});
        database.setTransactionSuccessful();
        database.endTransaction();
        close();
    }

    public void deleteCard(String cardID) {
        open();
        database.beginTransaction();
        String where = "card_ID = ?";
        String where2 = "card_ID2 = ?";
        database.delete(DataBaseHelper.CARD_TABLE, where, new String[]{cardID});
        database.delete(DataBaseHelper.CARD_INFO_TABLE, where2, new String[]{cardID});
        Log.v("deleted", cardID);
        database.setTransactionSuccessful();
        database.endTransaction();
        close();
    }


    public ArrayList<Set> getAllSetsWithName(String name) {
        ArrayList<Set> ls = new ArrayList<>();
        Set set;
        open();
        Cursor cursor = database.query(DataBaseHelper.CARD_INFO_TABLE,
                allColumns2, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (name.equals(cursor.getString(2))) {

                set = new Set(cursor.getLong(0), cursor.getString(2), cursor.getDouble(3), cursor.getInt(4), cursor.getString(5), cursor.getInt(6));
                ls.add(set);
                Log.d("Got:", set.toString());
            }
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        close();
        return ls;
    }

    public void updateEntry(long id, double weight, int reps) {
        // Define the updated row content.
        open();
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put(DataBaseHelper.CARD_WEIGHT, weight);
        updatedValues.put(DataBaseHelper.CARD_REPS, reps);

        String where = DataBaseHelper.CARD_ID2 + " = ?";
        database.update(DataBaseHelper.CARD_INFO_TABLE, updatedValues, where, new String[]{String.valueOf(id)});
        close();
    }

}
