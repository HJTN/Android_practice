package com.example.movieapi_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.movieapi_practice.databinding.ActivityMainBinding;

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
        // listener();
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
}