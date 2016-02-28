package com.example.ljd.client;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljd-PC on 2016/2/28.
 */
public class HandleProvider {
    private static Context mContext;
    private static HandleProvider mInstance;
    private static Uri mEmployeeUri;
    private static final String EMPLOYEE_CONTENT_URI = "content://com.example.ljd.employee.EmployeeProvider/employee";

    private HandleProvider(Context mContext) {
        this.mContext = mContext.getApplicationContext();
        mEmployeeUri = Uri.parse(EMPLOYEE_CONTENT_URI);
    }

    public static HandleProvider getInstance(Context context) {
        if (mInstance == null) {
            synchronized (HandleProvider.class) {
                if (mInstance == null) {
                    mInstance = new HandleProvider(context);
                }
            }
        }
        return mInstance;
    }

    public void delete() {
        ContentResolver contentResolver = mContext.getContentResolver();
        String where = "id=?";
        String[] where_args = {"7"};
        contentResolver.delete(mEmployeeUri, where, where_args);
    }

    public void update() {
        ContentResolver contentResolver = mContext.getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "梁山伯");
        String where = "id=?";
        String[] where_args = {"1"};
        contentResolver.update(mEmployeeUri, values, where, where_args);
    }

    public void create() {
        ContentValues values = new ContentValues();
        values.put("id", 7);
        values.put("workNum", "1006");
        values.put("name", "张三丰");
        values.put("department", "研发部");
        mContext.getContentResolver().insert(mEmployeeUri, values);
    }

    public List<String> query() {
        List<String> list = new ArrayList<>();
        Cursor cursor = mContext.getContentResolver().query(mEmployeeUri, new String[]{"id", "workNum", "name", "department"}, null, null, null);

        while (cursor.moveToNext()) {
            Employee employee = new Employee();
            employee.setId(cursor.getInt(0));
            employee.setWorkNum(cursor.getString(1));
            employee.setName(cursor.getString(2));
            employee.setDepartment(cursor.getString(3));
            String str = employee.toString();
            list.add(str);
            Log.d("mainActivity", "query employee:" + str);
        }
        cursor.close();
        return list;
    }

}
