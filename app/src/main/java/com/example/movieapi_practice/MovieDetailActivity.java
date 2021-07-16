package com.example.movieapi_practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        listener();

    }

    private void init() {
        intent = getIntent();
    }

    private void listener() {
        Picasso.get().load(intent.getStringExtra("image")).into(binding.moviePoster);
        binding.movieTitle.setText(intent.getStringExtra("title"));
        binding.movieSummary.setText(intent.getStringExtra("summary"));
    }
}
