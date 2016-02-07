package latte.nadia.nadiares;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
            String strURLuser = "http://swiftcodingthai.com/6feb/php_get_nadia.php";
            String strURLfood = "http://swiftcodingthai.com/6feb/php_get_data_food.php";

                HttpPost objHttpPost = null;
                try {
                    HttpClient objHttpClient = new DefaultHttpClient();//ขีดกลาง คือ มองว่าไม่ปลอดภัย
                    switch (intTable) {
                        case 1:
                            objHttpPost = new HttpPost(strURLuser);
                            break;
                        case 2:
                            objHttpPost = new HttpPost(strURLfood);
                            break;
                        default:
                            break;
                    } // switch
                    HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                    HttpEntity objHttpEntity = objHttpResponse.getEntity();
                    objInputStream = objHttpEntity.getContent();

            }catch (Exception e){
                Log.d(tag,"InputStream ==>"+e.toString());
            }


            //2. Create JSON String สิ่งที่โหลดมา เปลี่ยนเป็น String
            String strJSON = null;
            try{


                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream,"UTF-8"));//แปลงให้เป็นภาษาไทย
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;

                while ((strLine = objBufferedReader.readLine()) != null){
                    objStringBuilder.append(strLine);

                }//while //StringBuilder เป็นตัวประกอบ String ที่มาสเตอร์ตัดเป็นท่อนๆ

                objInputStream.close();
                strJSON = objStringBuilder.toString();//strJSON ยาวมาก แค่บรรทัดเดียว



            }catch (Exception e){
                Log.d(tag,"strJSON ==>" + e.toString());
            }

            //3. Update SQLite ขึ้น Lite
            try{

                JSONArray objJsonArray = new JSONArray(strJSON);
                for(int i=0;i<objJsonArray.length();i++){

                    JSONObject jsonObject = objJsonArray.getJSONObject(i);
                    switch (intTable){
                        case 1:

                            //GetValue From userTABLE
                            String strUser = jsonObject.getString(MyManage.column_user);//,มันวิ่งไป user
                            String strPassward = jsonObject.getString(MyManage.column_pass);
                            String strName = jsonObject.getString(MyManage.column_name);

                            objMyManage.addNewValue(0,strUser,strPassward,strPassward);


                            break;
                        case 2:

                            //GetValue From foodTABLE
                            String strFood = jsonObject.getString(MyManage.column_food);
                            String strPrice = jsonObject.getString(MyManage.column_price);
                            String strSource = jsonObject.getString(MyManage.column_source);

                            objMyManage.addNewValue(1,strFood,strPrice,strSource);



                            break;
                    }

                }// for



            }catch (Exception e){
                Log.d(tag,"Update ==>"+ e.toString());
            }

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


