package latte.nadia.nadiares;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by LatteCoffee on 06-Feb-16.
 */
public class MyManage {


    //Explicit ประกาศ
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String user_TABLE = "userTABLE";
    public static final String food_TABLE = "foodTABLE";
    public static final String column_id = "_id";
    public static final String column_user = "User";
    public static final String column_pass = "Password";
    public static final String column_name = "Name";
    public static final String column_food = "Food";
    public static final String column_price = "Price";
    public static final String column_source = "Source";




    public MyManage(Context context){

        //Create&Connect DB
        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }//Constructor

    public String[] searchUser(String strUser){

        try{

            String[] resultStrings = null;
            Cursor objCursor = readSqLiteDatabase.query(user_TABLE,new String[]
                    {column_id,column_user,column_pass,column_name},column_user+"=?",new String[]{String.valueOf(strUser)},
                    null,null,null,null);

            if (objCursor !=null) {
                if (objCursor.moveToFirst()) {

                    resultStrings = new String[4];
                    for (int i=0;i<4;i++){
                        resultStrings[i] = objCursor.getString(i);
                    }

                }//if2
            }// if
            objCursor.close();
            return  resultStrings;// เจอ

        }catch (Exception e){
            return null;// ไม่เจอ
        }

        //return new String[0];
    }

    public long addNewValue (int intTable,
                             String strColumn2,
                             String strColumn3,
                             String strColumn4){

        ContentValues objContentValues = new ContentValues();
        long longreturn = 0 ;
        switch (intTable) {
            case 0:

                objContentValues.put(column_user,strColumn2);
                objContentValues.put(column_pass,strColumn3);
                objContentValues.put(column_name,strColumn4);
                writeSqLiteDatabase.insert(user_TABLE,null,objContentValues);


                break;
            case 1:

                objContentValues.put(column_food,strColumn2);
                objContentValues.put(column_price,strColumn3);
                objContentValues.put(column_source,strColumn4);
                writeSqLiteDatabase.insert(food_TABLE,null,objContentValues);

                break;
        }// swich



        return longreturn;
    }

}// Main Class
