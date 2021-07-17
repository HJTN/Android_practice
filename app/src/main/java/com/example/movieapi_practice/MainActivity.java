package com.example.movieapi_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.movieapi_practice.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private ActivityMainBinding binding;
    private YtsAdapter cardAdapter = new YtsAdapter();
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        initDownload();
        listener();
        list_view();
    }

    private void init() {
        layoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        binding.rcCard.setLayoutManager(layoutManager);
    }

    private void initDownload() {
        YtsService ytsService = YtsService.retrofit.create(YtsService.class);
        Call<YtsData> call = ytsService.getMovieList("rating", 20, 1);
        call.enqueue(new Callback<YtsData>() {
            @Override
            public void onResponse(Call<YtsData> call, Response<YtsData> response) {
                if(response.isSuccessful()) {
                    YtsData ytsData = response.body();
                    Log.d(TAG, "onResponse:" + ytsData);

                    binding.rcCard.setAdapter(cardAdapter);
                    cardAdapter.addCardModel(ytsData.getData().getMovies());
                }
            }

            @Override
            public void onFailure(Call<YtsData> call, Throwable t) {
                Log.d(TAG, "onFailure:" + t);
                Toast.makeText(mContext, "Download Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void listener() {

        binding.toolbarIncludes.menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Clicked");

                if(!binding.DYMain.isDrawerOpen(Gravity.LEFT)) {
                    binding.DYMain.openDrawer(Gravity.LEFT);
                }
                else if(binding.DYMain.isDrawerOpen(Gravity.LEFT)) {
                    binding.DYMain.closeDrawer(Gravity.LEFT);
                }
            }
        });

        binding.DIIncludes.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.DYMain.isDrawerOpen(Gravity.LEFT)) {
                    binding.DYMain.closeDrawer(Gravity.LEFT);
                }
            }
        });

        binding.toolbarIncludes.searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked Search Button");

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
        });

        binding.toolbarSubIncludes.TSLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked Like Button");

                Intent intent = new Intent(MainActivity.this, MovieLikeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
            }
        });
    }

    private void list_view() {
        List<String> data = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_list_item_1, data);

        binding.DIIncludes.listview.setAdapter(adapter);

        data.add("Genre");
        adapter.notifyDataSetChanged();
    }
}