package latte.nadia.nadiares;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LatteCoffee on 06-Feb-16.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    //Explicit ประกาศตัวแปร(สีเทา)
    public static final String database_name = "Restuarant.db";
    private static final int database_version = 1;
    private static final String create_user_table = "create table userTABLE (" +
            "_id integer primary key, " +
            "User text," +
            "Password text," +
            "Name text);";
    private static final String create_food_table = "create table foodTABLE(" +
            "_id integer primary key," +
            "Food text," +
            "Price text," +
            "Source text);";


    public MyOpenHelper(Context context) {
        super(context,database_name,null,database_version);
    }// Constructor เหมือนตัวเชื่อม มาสเตอร์ทะลุกำแพงไปได้^^

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_user_table);
        db.execSQL(create_food_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
} // Main Class
