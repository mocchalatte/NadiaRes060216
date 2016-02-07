package latte.nadia.nadiares;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private TextView showNaTextView;
    private Spinner deskSpinner;
    private ListView foodListView;

    private String officerString,deskString,foodString,amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Bind Widget
        bindWidget();

        //show view
        showView();

        //show view
        showDesk();



    }// Main Method

    private void showDesk() {

        final String[] showDeskString = {"โต๊ะที่ 1","โต๊ะที่ 2","โต๊ะที่ 3","โต๊ะที่ 4","โต๊ะที่ 5",
                "โต๊ะที่ 6","โต๊ะที่ 7","โต๊ะที่ 8","โต๊ะที่ 9","โต๊ะที่ 10",};
        ArrayAdapter<String> deskAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, showDeskString);
        deskSpinner.setAdapter(deskAdapter);
        deskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deskString = showDeskString[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                deskString = showDeskString[0];
            }//
        });

    }

    private void showView() {

        //Recieve From Intent
        officerString = getIntent().getStringExtra("Name"); // ต้องให้ตรงกับอีกหน้านึงนะ
        showNaTextView.setText(officerString);
    }

    private  void bindWidget(){
        showNaTextView = (TextView) findViewById(R.id.textView2);
        deskSpinner = (Spinner) findViewById(R.id.spinner);
        foodListView = (ListView) findViewById(R.id.listView);
    }

}// Main Class
