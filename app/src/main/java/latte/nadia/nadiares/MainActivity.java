package latte.nadia.nadiares;

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
    }//Main Method

}// Main Class


