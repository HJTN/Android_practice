package com.example.layoutpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Picasso using Practice!
        ImageButton IMG_B1 = (ImageButton) findViewById(R.id.IMG_B1);
        ImageButton IMG_B2 = (ImageButton) findViewById(R.id.IMG_B2);
        ImageButton IMG_B3 = (ImageButton) findViewById(R.id.IMG_B3);
        ImageButton IMG_B4 = (ImageButton) findViewById(R.id.IMG_B4);

        Picasso.get().load(R.drawable.home).into(IMG_B1);
        Picasso.get().load(R.drawable.calendar).into(IMG_B2);
        Picasso.get().load(R.drawable.build).into(IMG_B3);
        Picasso.get().load(R.drawable.settings).into(IMG_B4);

        IMG_B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                if(drawer.isDrawerOpen(Gravity.LEFT)){
                    drawer.closeDrawer(Gravity.LEFT);
                }
            }
        });

        // DrawerLayout Practice!
        Button bOpen = (Button)findViewById(R.id.open);
        bOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                if(!drawer.isDrawerOpen(Gravity.LEFT)){
                    drawer.openDrawer(Gravity.LEFT);
                }
            }
        });

        Button bClose = (Button)findViewById(R.id.close);
        bClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                if(drawer.isDrawerOpen(Gravity.LEFT)){
                    drawer.closeDrawer(Gravity.LEFT);
                }
            }
        });

        CheckBox cLock = (CheckBox)findViewById(R.id.lock);
        cLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                if(((CheckBox)v).isChecked()){
                    if(drawer.isDrawerOpen(Gravity.LEFT)){
                        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                    }
                    else{
                        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    }
                }
                else{
                    drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                }
            }
        });
    }
}