package com.example.adapterviewpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        setTitle("리스트뷰 테스트");
        final String[] mid ={"셜록홈즈","프렌즈","로스트","글리","스킨스","모던패밀리"};

        ListView list = (ListView) findViewById(R.id.ListView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mid);
        Log.d("Adapter check", String.valueOf(adapter));
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Log.d("Clicked", parent + " / " + position + " / " + id);
                Toast.makeText(getApplicationContext(),mid[position],Toast.LENGTH_SHORT).show();
            }
        });
    }
}