package latte.nadia.nadiares;

import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;

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

        //Sync JSON to SQLite
        synJSONtoSQLite();

    }//Main Method

    private void synJSONtoSQLite() {


        //Change Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        int intTable = 1;
        String tag = "Restuarant";

        while (intTable <= 2){

            //1. Create InputStream คือ ไหมพรหมมีแค่ 1 เส้น โหลดไปประมวลผลไป
            InputStream objInputStream = null;
            String strURL_user = "http://swiftcodingthai.com/6feb/php_get_nadia.php";
            String strURL_food = "http://swiftcodingthai.com/6feb/php_get_data_food.php";

            try {

            }catch (Exception e){
                Log.d(tag,"InputStream ==>"+e.toString());
            }


            //2. Create JSON String สิ่งที่โหลดมา เปลี่ยนเป็น String
            //3. Update SQLite ขึ้น Lite

            intTable +=1;


        }// while


    }// synJSONtoSQLite

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


