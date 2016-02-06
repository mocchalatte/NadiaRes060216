package latte.nadia.nadiares;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by LatteCoffee on 06-Feb-16.
 */
public class MyManage {


    //Explicit ประกาศ
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;


    public MyManage(Context context){

        //Create&Connect DB
        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }//Constructor

}// Main Class
