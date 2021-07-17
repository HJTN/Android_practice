package com.example.movieapi_practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapi_practice.databinding.ActivityLikeBinding;
import com.example.movieapi_practice.databinding.MovieDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieLikeActivity extends AppCompatActivity {

    private static final String TAG = "MovieLike_Activity";

    private ActivityLikeBinding binding;

    static YtsAdapter cardAdapter = new YtsAdapter();
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext = MovieLikeActivity.this;
    static List<YtsData.MyData.Movie> Sc_movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLikeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        initDownload();

    }


    private void init() {
        layoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        binding.LMRcCard.setLayoutManager(layoutManager);

        binding.LMRcCard.setAdapter(cardAdapter);
        cardAdapter.addCardModel(Sc_movies);
        cardAdapter.notifyDataSetChanged();
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

    }
}
