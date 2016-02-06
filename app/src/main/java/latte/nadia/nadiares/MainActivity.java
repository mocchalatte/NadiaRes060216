package latte.nadia.nadiares;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //ประกาศ
    private MyManage objMyManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Request Database
        objMyManage = new MyManage(this);

        //Test add Value
        //testAddValue();

        //CleanData
        cleanData();//ตัวแปร

    }//Main Method

    private void cleanData() {

        SQLiteDatabase objSQLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        objSQLiteDatabase.delete(MyManage.food_TABLE, null, null);
        objSQLiteDatabase.delete(MyManage.user_TABLE, null, null);

    }// CleanData

    private void testAddValue() {

        for ( int i=0;i<=1;i++){
            objMyManage.addNewValue(i,"test1","test2","test3");
        }// for

    }// testaddvalue


}// Main Class


