package com.company.joeliomason.projectme.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.company.joeliomason.projectme.POJOs.Category;
import com.company.joeliomason.projectme.POJOs.Exercise;
import com.company.joeliomason.projectme.POJOs.Workout;

import java.util.ArrayList;

/**
 * Created by JoelioMason on 04/03/15.
 */
public class WorkoutDatabaseAdapter {
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private char CATEGORY_FLAG = 0;

    private static ArrayList<Workout> workouts = new ArrayList<>();
    private static ArrayList<Category> categories = new ArrayList<>();

    private String[] allColumns = { DataBaseHelper.CATEGORY_ID,
            DataBaseHelper.CATEGORY_NAME };


    public WorkoutDatabaseAdapter(Context context) {
        dbHelper = new DataBaseHelper(context);
        CATEGORY_FLAG = 0;
        populateDB();
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void populateDB() {
        if(emptyTable()) {
            addTo();
            open();
            database.beginTransaction();
            for (Workout xxx : workouts) {
                ContentValues newValues = new ContentValues();
                newValues.put(DataBaseHelper.WORKOUT_NAME, xxx.getName());
                newValues.put(DataBaseHelper.WORKOUT_TYPE, xxx.getType());
                newValues.put(DataBaseHelper.WORKOUT_CATEGORY, xxx.getCategory());
                database.insert(DataBaseHelper.TABLE_WORKOUTS, null, newValues);
                Log.d("Got:", newValues.toString());
            }

            for (Category yyy : categories) {
                ContentValues newValues2 = new ContentValues();
                newValues2.put(DataBaseHelper.CATEGORY_NAME, yyy.getName());
                database.insert(DataBaseHelper.TABLE_CATEGORIES, null, newValues2);
                Log.d("Got:", newValues2.toString());
            }

            database.setTransactionSuccessful();
            database.endTransaction();
            close();
            CATEGORY_FLAG = 1;
            Log.d("Got:", "Successfully Added!!");

        } else {
            Log.d("dumbass:", "already been populated!");
            close();
        }
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> c = new ArrayList<>();
        open();
        Cursor cursor = database.query(DataBaseHelper.TABLE_CATEGORIES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = cursorToCategory(cursor);
            c.add(category);
            Log.d("Got:", category.toString());
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        close();
        return c;
    }

    private Category cursorToCategory(Cursor cursor) {
        Category c = new Category(cursor.getLong(0), cursor.getString(1));
        return c;
    }

    public ArrayList<Exercise> getExercisePerCategory(int id){
        ArrayList<Exercise> c = new ArrayList<>();
        database.beginTransaction();
        Cursor c1 = database.query(DataBaseHelper.TABLE_WORKOUTS, null, DataBaseHelper.WORKOUT_CATEGORY + " = " + id, null, null, null, null);
        c1.moveToFirst();
        if (id == c1.getColumnIndex(DataBaseHelper.WORKOUT_CATEGORY)) {
            Log.d("Got:", "Exercise has been found");
            while (!c1.isAfterLast()) {
                Exercise wo = cursorToWorkout(c1, id);
                c.add(wo);
                Log.d("Got workout names", wo.getName());
                c1.moveToNext();
            }
            c1.close();
        } else {
            Log.d("Got:", "Exercise Not Found " + id);
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        c1.close();
        close();
        return c;
    }

    public ArrayList<Exercise> getExercisePerCategory1(int id)
    {
        ArrayList<Exercise> c = new ArrayList<>();
        int count = 0;
        Exercise notFound = new Exercise("No Records Found", 0, 0);
        open();
        database.beginTransaction();
        Cursor c1 = database.query(DataBaseHelper.TABLE_WORKOUTS, null, DataBaseHelper.WORKOUT_CATEGORY + " = " + id, null, null, null, null);
        if(!c1.moveToFirst())
        {
            c1.close();
            Log.d("Got workout names", "somethings not right here");
            c.add(notFound);
        } else if(id == c1.getInt(c1.getColumnIndex(DataBaseHelper.WORKOUT_CATEGORY))){

            for (int i = 0; i < c1.getCount(); i++) {
                Log.d("Got workout names", "getting closer");
                Exercise wo = cursorToWorkout(c1, id);
                c.add(wo);
                Log.d("Got workout names", wo.getName());
                c1.moveToNext();
                count++;
            }
        }
        else {
            c.add(notFound);
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        c1.close();
        close();
        return c;
    }

    private Exercise cursorToWorkout(Cursor cursor, int x) {
        //String name, int type, int category
        Exercise d = null;
        try {
            if (cursor.getInt(cursor.getColumnIndex(DataBaseHelper.WORKOUT_CATEGORY)) == x) {
                d = new Exercise(cursor.getString(0), cursor.getInt(1), cursor.getInt(2));
            }
        } catch(NullPointerException e) {
            Log.d("null", "value returned null :/ ");
        }
        return d;
    }

    public boolean emptyTable() {
        boolean xxx;
        open();
        database.beginTransaction();
        Cursor cursor = database.query(DataBaseHelper.TABLE_CATEGORIES,
                allColumns, null, null, null, null, null);

        if(!cursor.moveToFirst()) {
            cursor.close();
            xxx =  true;
        } else {
            xxx = false;
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        cursor.close();
        close();
        return xxx;
    }


    private void addTo() {
        //shoulders
        Workout q = new Workout("Dumbbell Shoulder Press", 1, 1);
        workouts.add(q);
        Workout w = new Workout("Dumbbell Arnold Press", 1, 1);
        workouts.add(w);
        Workout e = new Workout("Front Raise", 1, 1);
        workouts.add(e);
        Workout r= new Workout("Barbell Shoulder Press", 1, 1);
        workouts.add(r);
        Workout t= new Workout("Side Raise", 1, 1);
        workouts.add(t);
        Workout y= new Workout("Upright Row", 1, 1);
        workouts.add(y);
        Workout u= new Workout("Dumbbell Shrug", 1, 1);
        workouts.add(u);
        Workout i= new Workout("Barbell Shrug", 1, 1);
        workouts.add(i);
        Workout hjk= new Workout("Behind The Neck Barbell Press", 1, 1);
        workouts.add(hjk);
        Workout hjl= new Workout("Cable Face Pull", 1, 1);
        workouts.add(hjl);
        Workout hjo= new Workout("Hammer Strength Shoulder Press", 1, 1);
        workouts.add(hjo);
        Workout hja= new Workout("Log Press", 1, 1);
        workouts.add(hja);
        Workout hjh= new Workout("One-Arm Standing Dumbbell Press", 1, 1);
        workouts.add(hjh);
        Workout hjj= new Workout("Overhead Press", 1, 1);
        workouts.add(hjj);
        Workout hje= new Workout("Push Press", 1, 1);
        workouts.add(hje);
        Workout hjc= new Workout("Smith Machine Overhead Press", 1, 1);
        workouts.add(hjc);
        //Chest
        Workout a= new Workout("Dumbbell Fly", 1, 2);
        workouts.add(a);
        Workout h= new Workout("Incline Dumbbell Fly", 1, 2);
        workouts.add(h);
        Workout j= new Workout("Decline Dumbbell Fly", 1, 2);
        workouts.add(j);
        Workout o= new Workout("Dumbbell Chest Press", 1, 2);
        workouts.add(o);
        Workout s= new Workout("Incline Dumbbell Chest Press", 1, 2);
        workouts.add(s);
        Workout f= new Workout("Decline Dumbbell Chest Press", 1, 2);
        workouts.add(f);
        Workout p= new Workout("Barbell Chest Press", 1, 2);
        workouts.add(p);
        Workout d= new Workout("Incline Barbell Chest Press", 1, 2);
        workouts.add(d);
        Workout g= new Workout("Decline Barbell Chest Press", 1, 2);
        workouts.add(g);
        Workout jkl= new Workout("Upper Cable Fly", 1, 2);
        workouts.add(jkl);
        Workout jkk= new Workout("Middle Cable Fly", 1, 2);
        workouts.add(jkk);
        Workout jkh= new Workout("Lower Cable Fly", 1, 2);
        workouts.add(jkh);
        Workout jkp= new Workout("Weighted Chest Dip", 1, 2);
        workouts.add(jkp);
        //back
        Workout k= new Workout("Bent Over Row", 1, 3);
        workouts.add(k);
        Workout l= new Workout("Single Arm Row", 1, 3);
        workouts.add(l);
        Workout z= new Workout("T-Bar Row", 1, 3);
        workouts.add(z);
        Workout x= new Workout("Deadlift", 1, 3);
        workouts.add(x);
        Workout c= new Workout("Pull Up", 1, 3);
        workouts.add(c);
        Workout v= new Workout("Lateral Pull Down", 1, 3);
        workouts.add(v);
        Workout vw= new Workout("Reverse Cable Fly", 1, 3);
        workouts.add(vw);
        Workout vq= new Workout("Good Morning", 1, 3);
        workouts.add(vq);
        Workout vg= new Workout("Straight Arm Cable Push Down", 1, 3);
        workouts.add(vg);
        //legs
        Workout b= new Workout("Back Squat", 1, 4);
        workouts.add(b);
        Workout n= new Workout("Front Squat", 1, 4);
        workouts.add(n);
        Workout m= new Workout("Jefferson Squat", 1, 4);
        workouts.add(m);
        Workout mm= new Workout("Romanian Deadlift", 1, 4);
        workouts.add(mm);
        Workout mv= new Workout("Leg Press", 1, 4);
        workouts.add(mv);
        Workout mz= new Workout("Seated Calf Raises", 1, 4);
        workouts.add(mz);
        Workout mb= new Workout("Hamstring Curl", 1, 4);
        workouts.add(mb);
        Workout mf= new Workout("Leg Extension", 1, 4);
        workouts.add(mf);
        Workout mc= new Workout("Calf Raise", 1, 4);
        workouts.add(mc);
        Workout mp= new Workout("Smith Machine Calf Raise", 1, 4);
        workouts.add(mp);
        Workout mo= new Workout("Lunge", 1, 4);
        workouts.add(mo);
        Workout ml= new Workout("Barbell Single Leg Lunge", 1, 4);
        workouts.add(ml);

        //arms
        Workout qq= new Workout("Preacher Curl", 1, 5);
        workouts.add(qq);
        Workout qw= new Workout("Dumbbell Curl", 1, 5);
        workouts.add(qw);
        Workout qe= new Workout("Skull Crushers", 1, 5);
        workouts.add(qe);
        Workout qee= new Workout("EZ Bar Curl", 1, 5);
        workouts.add(qee);
        Workout qef= new Workout("Single Arm Curl", 1, 5);
        workouts.add(qef);
        Workout qek= new Workout("Isolated Single Arm Curl", 1, 5);
        workouts.add(qek);
        Workout qel= new Workout("Single Arm Hammer", 1, 5);
        workouts.add(qel);
        Workout qep= new Workout("Isolated Single Arm Hammer", 1, 5);
        workouts.add(qep);
        Workout qej= new Workout("Cable Curl", 1, 5);
        workouts.add(qej);
        Workout qeh= new Workout("Rope Pulldown", 1, 5);
        workouts.add(qeh);
        Workout qei= new Workout("Close Grip Barbell Press", 1, 5);
        workouts.add(qei);
        Workout qeg= new Workout("Incline Close Grip Barbell Press", 1, 5);
        workouts.add(qeg);
        Workout qeo= new Workout("Weighted Tricep Dip", 1, 5);
        workouts.add(qeo);
        Workout qen= new Workout("Plate Curl", 1, 5);
        workouts.add(qen);
        Workout tru= new Workout("Tricep Kickback", 1, 5);
        workouts.add(tru);
        Workout jjj= new Workout("Behind The Head Tricep Press", 1, 5);
        workouts.add(jjj);
        Workout hhh= new Workout("Single Arm Behind The Head Tricep Curl", 1, 5);
        workouts.add(hhh);
        Workout lll= new Workout("Dip Machine", 1, 5);
        workouts.add(lll);
        //Abs
        Workout qr= new Workout("Hanging Leg Raise", 1, 6);
        workouts.add(qr);
        Workout qnn= new Workout("Hanging Knee Raise", 1, 6);
        workouts.add(qnn);
        Workout qt= new Workout("Crunch", 1, 6);
        workouts.add(qt);
        Workout qj= new Workout("Ab-Wheel Rollout", 1, 6);
        workouts.add(qj);
        Workout qm= new Workout("Cable Crunch", 1, 6);
        workouts.add(qm);
        Workout qn= new Workout("Crunch Machine", 1, 6);
        workouts.add(qn);
        Workout qz= new Workout("Decline Crunch", 1, 6);
        workouts.add(qz);
        Workout qf= new Workout("Plank", 1, 6);
        workouts.add(qf);
        Workout qc= new Workout("Side Plank", 1, 6);
        workouts.add(qc);
        Workout qb= new Workout("Lying Leg Raise", 1, 6);
        workouts.add(qb);
        Workout qx= new Workout("Laying Bicycle", 1, 6);
        workouts.add(qx);
        Workout qg= new Workout("Elbow To Knee", 1, 6);
        workouts.add(qg);
        //cardio
        Workout c1= new Workout("Treadmill", 1, 7);
        workouts.add(c1);
        Workout c2= new Workout("Bike", 1, 7);
        workouts.add(c2);
        Workout c3= new Workout("Stepper", 1, 7);
        workouts.add(c3);
        Workout c4= new Workout("Cross Trainer", 1, 7);
        workouts.add(c4);
        Workout c5= new Workout("Rowing Machine", 1, 7);
        workouts.add(c5);
        Workout c6= new Workout("Swimming", 1, 7);
        workouts.add(c6);

        //Categories
        Category cat1 = new Category(1, "Shoulders");
        categories.add(cat1);
        Category cat2 = new Category(2, "Chest");
        categories.add(cat2);
        Category cat3 = new Category(3, "Back");
        categories.add(cat3);
        Category cat4 = new Category(4, "Legs");
        categories.add(cat4);
        Category cat5 = new Category(5, "Arms");
        categories.add(cat5);
        Category cat6 = new Category(6, "Abs");
        categories.add(cat6);
        Category cat7 = new Category(7, "Cardio");
        categories.add(cat7);

    }


}