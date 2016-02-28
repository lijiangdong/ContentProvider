package com.example.ljd.employee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ljd-PC on 2016/2/28.
 */
public class EmployeeDBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "myDatabase.db";
    private final static int DB_VERSION = 1;
    public static final String EMPLOYEE_TABLE_NAME = "employee";

    private String CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS "
            + EMPLOYEE_TABLE_NAME + " (id INTEGER PRIMARY KEY,"
            +"workNum TEXT,"+ "name TEXT,"+"department TEXT)";

    public EmployeeDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
