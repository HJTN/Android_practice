package com.example.movieapi_practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapi_practice.databinding.ActivitySearchBinding;
import com.example.movieapi_practice.databinding.MovieDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = "MovieDetail_Activity";

    private MovieDetailBinding binding;
    private Intent intent;
    private boolean islike = false;

    private Context mContext = MovieDetailActivity.this;
    private List<YtsData.MyData.Movie> Sc_movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        initDownload();
        listener();

    }

    private void init() {
        intent = getIntent();
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

                    Sc_movies.addAll(ytsData.getData().getMovies());
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
        Picasso.get().load(intent.getStringExtra("image")).into(binding.moviePoster);
        binding.movieTitle.setText(intent.getStringExtra("title"));
        binding.movieSummary.setText(intent.getStringExtra("summary"));

        binding.movieLikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!islike) {
                    Log.d(TAG, "like checked");
                    for(YtsData.MyData.Movie movie : Sc_movies) {
                        if(binding.movieTitle.getText().toString().equals(movie.getTitle())) {
                            MovieLikeActivity.Sc_movies.add(movie);
                            MovieLikeActivity.cardAdapter.notifyDataSetChanged();
                        }
                    }
                    binding.movieLikeBtn.setImageResource(R.drawable.thumbup);

                    islike = true;
                }
                else {
                    binding.movieLikeBtn.setImageResource(R.drawable.thumbupoff);

                    islike = false;
                }
            }
        });
    }
}
