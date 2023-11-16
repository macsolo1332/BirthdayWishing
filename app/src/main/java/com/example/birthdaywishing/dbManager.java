package com.example.birthdaywishing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "reminder.db";
    private static final int DATABASE_VERSION = 1;

    public dbManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE tbl_reminder (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "date TEXT," +
                "time TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_reminder");
        onCreate(db);
    }

    public String addReminder(String title, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("date", date);
        values.put("time", time);
        long result = db.insert("tbl_reminder", null, values);

        if (result == -1) {
            return "Failed to insert reminder";
        } else {
            return "Successfully inserted reminder";
        }
    }

    public Cursor readAllReminders() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM tbl_reminder ORDER BY id DESC";
        return db.rawQuery(query, null);
    }

    public String updateReminder(String oldTitle, String newTitle, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", newTitle);
        values.put("date", date);
        values.put("time", time);

        String whereClause = "title = ?";
        String[] whereArgs = {oldTitle};

        int updatedRows = db.update("tbl_reminder", values, whereClause, whereArgs);

        if (updatedRows > 0) {
            return "Successfully updated reminder";
        } else {
            return "Failed to update reminder";
        }
    }

    public String deleteReminder(String title, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "title = ? AND date = ? AND time = ?";
        String[] whereArgs = {title, date, time};

        int deletedRows = db.delete("tbl_reminder", whereClause, whereArgs);

        if (deletedRows > 0) {
            return "Successfully deleted reminder";
        } else {
            return "Failed to delete reminder";
        }
    }
}
