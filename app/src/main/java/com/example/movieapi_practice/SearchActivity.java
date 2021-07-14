package com.example.movieapi_practice;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapi_practice.databinding.ActivitySearchBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "Search_Activity";
    private String findD = "";

    private ActivitySearchBinding binding;
    private YtsAdapter cardAdapter = new YtsAdapter();
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext = SearchActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        binding.scToolBarIncludes.searchIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDownload();
            }
        });
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
                if(response.isSuccessful()) {
                    YtsData ytsData = response.body();
                    Log.d(TAG, "onResponse:" + ytsData);

                    findD += binding.scToolBarIncludes.edittext.getText().toString();

                    binding.scRcCard.setAdapter(cardAdapter);
                    if(findD != null) {
                        for(YtsData.MyData.Movie movie : ytsData.getData().getMovies()) {
                            if(findD.equals(movie.getTitle())) {
                                cardAdapter.addCardModel(movie);
                            }
                        }
                    }
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
