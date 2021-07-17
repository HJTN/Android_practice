package com.example.fragmentpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public boolean isFragmentB = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragmentBorC, new FragmentB());
        fragmentTransaction.commit();

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment();
            }
        });
    }

    public void switchFragment() {
        Fragment fr;

        if (isFragmentB) {
            fr = new FragmentB();
        } else {
            fr = new FragmentC();
        }

        isFragmentB = (isFragmentB) ? false : true;

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentBorC, fr);
        fragmentTransaction.commit();
    }
}