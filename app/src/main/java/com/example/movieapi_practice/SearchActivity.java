package com.example.movieapi_practice;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapi_practice.databinding.ActivitySearchBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "Search_Activity";

    private ActivitySearchBinding binding;
    private YtsAdapter cardAdapter = new YtsAdapter();
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext = SearchActivity.this;
    private List<YtsData.MyData.Movie> Sc_movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        initDownload();
        listener();

    }

    private void init() {
        layoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        binding.scRcCard.setLayoutManager(layoutManager);
    }

    private void initDownload() {
        YtsService ytsService = YtsService.retrofit.create(YtsService.class);
        Call<YtsData> call = ytsService.getMovieList("rating", 20, 1);
        call.enqueue(new Callback<YtsData>() {
            @Override
            public void onResponse(Call<YtsData> call, Response<YtsData> response) {
                if (response.isSuccessful()) {
                    YtsData ytsData = response.body();
                    Log.d(TAG, "onResponse:" + ytsData);

                    binding.scRcCard.setAdapter(cardAdapter);
                    cardAdapter.addCardModel(ytsData.getData().getMovies());

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
        binding.scToolBarIncludes.edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String findD = binding.scToolBarIncludes.edittext.getText().toString();
                search(findD);
            }
        });
    }

    private void search(String data) {
        cardAdapter.removeCardModel();

        if (data.length() == 0) {
            cardAdapter.addCardModel(Sc_movies);
        } else {
            for (int i = 0; i < Sc_movies.size(); i++) {
                if (Sc_movies.get(i).getTitle().toLowerCase().contains(data)) {
                    cardAdapter.addCardModel(Sc_movies.get(i));
                }
            }
        }

        cardAdapter.notifyDataSetChanged();
    }
}
