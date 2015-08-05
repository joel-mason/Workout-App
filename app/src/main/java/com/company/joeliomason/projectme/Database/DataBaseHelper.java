package com.company.joeliomason.projectme.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;

    // This is for the user table
    public static final String TABLE_USER = "users";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRSTNAME = "firstName";
    public static final String USER_LASTNAME = "surname";
    public static final String USER_HEIGHT = "height";
    public static final String USER_WEIGHT = "weight";

    // this is for the workout categories table table
    public static final String TABLE_CATEGORIES = "categories";
    public static final String CATEGORY_ID = "cat_id";
    public static final String CATEGORY_NAME = "name";

    //this is for the workouts table
    public static final String TABLE_WORKOUTS = "workouts";
    public static final String WORKOUT_NAME = "name";
    public static final String WORKOUT_TYPE = "type";
    public static final String WORKOUT_CATEGORY = "category";

    //TRACKER TABLE WITH CARDS THING LOL
    public static final String CARD_TABLE = "cardview";
    public static final String CARD_ID = "card_ID";
    public static final String CARD_NAME = "cardName";
    public static final String CARD_DATE = "cardDate";

    //Card information, linked with the table above ^^^
    public static final String CARD_INFO_TABLE = "cardinfo";
    public static final String CARD_ID2 = "card_ID2";
    public static final String CARD_INFO_ID = "id";
    public static final String CARD_NAME2 = "name2";
    public static final String CARD_WEIGHT = "weight";
    public static final String CARD_REPS = "reps";
    public static final String CARD_DATE2 = "cardDate2";
    public static final String CARD_CATEGORY = "cardCategory";

    // SQL Statement to create a new database.
    public static final String CREATE_USER_TABLE = "CREATE TABLE "
            + TABLE_USER + "(" + USER_EMAIL
            + " TEXT PRIMARY KEY, " + USER_PASSWORD
            + " TEXT, " + USER_FIRSTNAME
            + " TEXT, " + USER_LASTNAME
            + " TEXT, " + USER_HEIGHT
            + " INTEGER, " + USER_WEIGHT
            + " INTEGER )";

    //SQL Statement to create the category table
    public static final String CREATE_CAT_TABLE = "CREATE TABLE "
            + TABLE_CATEGORIES + "(" + CATEGORY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY_NAME
            + " TEXT )";


    //card id
    public static final String CREATE_CARD_TABLE = "CREATE TABLE "
            + CARD_TABLE + "(" + CARD_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CARD_NAME
            + " TEXT, " + CARD_DATE
            + " TEXT )";

    //card foreign key, this will be where the card information is stored.
    public static final String CREATE_CARDINFO_TABLE = "CREATE TABLE "
            + CARD_INFO_TABLE + "(" + CARD_INFO_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CARD_ID2
            + " INTEGER, " + CARD_NAME2
            + " TEXT, " + CARD_WEIGHT
            + " REAL, " + CARD_REPS
            + " INTEGER, " + CARD_DATE2
            + " TEXT, " + CARD_CATEGORY
            + " INTEGER, "
            + " FOREIGN KEY " + "("+ CARD_ID2 +")"+ " REFERENCES " + CARD_TABLE + "("+CARD_ID+"));";

    public static final String CREATE_WORKOUT_TABLE = "CREATE TABLE "
            + TABLE_WORKOUTS + "(" + WORKOUT_NAME
            + " TEXT PRIMARY KEY, " + WORKOUT_TYPE
            + " INTEGER," + WORKOUT_CATEGORY
            + " INTEGER,"
            + " FOREIGN KEY" + "("+ WORKOUT_CATEGORY +")"+ " REFERENCES " + TABLE_CATEGORIES + "("+CATEGORY_ID+"));";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_USER_TABLE);
        database.execSQL(CREATE_CAT_TABLE);
        database.execSQL(CREATE_WORKOUT_TABLE);
        database.execSQL(CREATE_CARD_TABLE);
        database.execSQL(CREATE_CARDINFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DataBaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

} 