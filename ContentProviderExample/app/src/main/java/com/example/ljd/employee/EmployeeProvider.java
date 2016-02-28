package com.example.ljd.employee;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by ljd-PC on 2016/2/28.
 */
public class EmployeeProvider extends ContentProvider {
    private SQLiteDatabase mDb;
    private static final String AUTHORITY = "com.example.ljd.employee.EmployeeProvider";
    private static final int EMPLOYEE_URI_CODE = 0;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, "employee", EMPLOYEE_URI_CODE);
    }

    @Override
    public boolean onCreate() {
        insertDataToDb();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        Cursor cursor = mDb.query(tableName, projection, selection, selectionArgs, null, null, sortOrder, null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        mDb.insert(table, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int count = mDb.delete(table, selection, selectionArgs);
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String table = getTableName(uri);
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        int row = mDb.update(table, values, selection, selectionArgs);
        if (row > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return row;
    }

    private void insertDataToDb() {
        mDb = new EmployeeDBHelper(getContext()).getWritableDatabase();
        mDb.execSQL("delete from " + EmployeeDBHelper.EMPLOYEE_TABLE_NAME);
        mDb.execSQL("insert into employee values(1,'1001','张三','销售部');");
        mDb.execSQL("insert into employee values(2,'1002','李四','人事部');");
        mDb.execSQL("insert into employee values(3,'1003','王五','研发部');");
        mDb.execSQL("insert into employee values(4,'1004','小明','研发部');");
        mDb.execSQL("insert into employee values(5,'1005','小强','销售部');");
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        if (sUriMatcher.match(uri) == EMPLOYEE_URI_CODE){
            tableName = EmployeeDBHelper.EMPLOYEE_TABLE_NAME;
        }
        return tableName;
    }
}
