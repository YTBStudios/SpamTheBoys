package com.example.ryan.practical5part1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseManager {
    public static final String DB_NAME = "Student";
    public static final String DB_TABLE = "StudentInfo";
    public static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE
            + " (student_ID INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT, birth_year INTEGER, gender TEXT);";
    private SQLHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public DatabaseManager(Context c) {
        this.context = c;
        helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();

    }

    public DatabaseManager openReadable() throws android.database.SQLException {
        helper = new SQLHelper(context);
        db = helper.getReadableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    public void addRow(Integer c, String f, String l, Integer b, String g) {
        ContentValues newProduct = new ContentValues();
        newProduct.put("student_ID", c);
        newProduct.put("first_name", f);
        newProduct.put("last_name", l);
        newProduct.put("birth_year", b);
        newProduct.put("gender", g);
        try {
            db.insertOrThrow(DB_TABLE,null,newProduct);
        } catch (Exception e) {
            Log.e("Error in inserting rows ", e.toString());
            e.printStackTrace();
        }
        db.close();
    }

    public String getRows()
    {
        String[] columns = new String[]{"student_ID", "first_name", "last_name", "birth_year", "gender"};

        Cursor curs = db.query("StudentInfo", columns, null, null, null, null, null);

        String tablerows = "";
        curs.moveToFirst();
        while (curs.isAfterLast() == false)
        {
            tablerows = tablerows + curs.getInt(0) + ", " + curs.getString(1) + ", " + curs.getString(2) + ", " + curs.getString(3) + ", " + curs.getString(4) + "\n";
            curs.moveToNext();
        }
        if (curs != null && !curs.isClosed())
        {
            curs.close();
        }

        return tablerows;
    }

    public class SQLHelper extends SQLiteOpenHelper {
        public SQLHelper(Context c) {
            super(c, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Products table", "Upgrading database i.e. dropping table and re-creating it");
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }

    }
}