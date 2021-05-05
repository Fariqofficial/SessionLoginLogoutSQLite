package it.pradita.ac.sessionloginlogoutsqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_login";
    public static final String TABLE_NAME = "Login";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String quey = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USERNAME + " TEXT," + PASSWORD + " TEXT)";
        db.execSQL(quey);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void insertData(ContentValues values){
        db.insert(TABLE_NAME, null, values);
    }

    public boolean checkUser(String username, String password){
        String[] columns = {ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = USERNAME + "=?" + "and " + PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();;
        db.close();

        if (count>0)
            return true;
            else
            return false;
        }
}
