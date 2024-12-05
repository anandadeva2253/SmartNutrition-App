package com.example.nutritionapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "nutrition.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PROFILE = "profile";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_WEIGHT = "weight";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_AGE + " INTEGER, "
                + COLUMN_GENDER + " TEXT, "
                + COLUMN_HEIGHT + " REAL, "
                + COLUMN_WEIGHT + " REAL"
                + ")";
        db.execSQL(CREATE_PROFILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        onCreate(db);
    }

    // Method to insert a profile into the database
    public boolean insertProfile(String name, int age, String gender, float height, float weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_AGE, age);
        contentValues.put(COLUMN_GENDER, gender);
        contentValues.put(COLUMN_HEIGHT, height);
        contentValues.put(COLUMN_WEIGHT, weight);

        long result = db.insert(TABLE_PROFILE, null, contentValues);
        db.close();
        return result != -1;  // Return true if the insert was successful, otherwise false
    }

    // Method to get height and weight by name
    public float[] getHeightAndWeight(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PROFILE, new String[]{COLUMN_HEIGHT, COLUMN_WEIGHT},
                COLUMN_NAME + "=?", new String[]{name}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") float height = cursor.getFloat(cursor.getColumnIndex(COLUMN_HEIGHT));
            @SuppressLint("Range") float weight = cursor.getFloat(cursor.getColumnIndex(COLUMN_WEIGHT));
            cursor.close();
            return new float[]{height, weight};
        } else {
            cursor.close();
            return null;  // Return null if no data found for the name
        }
    }

    // Method to get all users' profiles
    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PROFILE, new String[]{COLUMN_NAME, COLUMN_HEIGHT, COLUMN_WEIGHT},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") float height = cursor.getFloat(cursor.getColumnIndex(COLUMN_HEIGHT));
                @SuppressLint("Range") float weight = cursor.getFloat(cursor.getColumnIndex(COLUMN_WEIGHT));
                users.add("Name: " + name + " | Height: " + height + " | Weight: " + weight);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return users;
    }
}
