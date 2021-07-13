package com.example.movieapi_practice;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class YtsAdapter extends RecyclerView.Adapter<YtsAdapter.CardView> {
    private static final String TAG = "YtsAdapter";

    private List<YtsData.MyData.Movie> cardModels = new ArrayList<>();

    public void addCardModel(YtsData.MyData.Movie movie) {
        cardModels.add(movie);
    }

    public void addCardModel(List<YtsData.MyData.Movie> movies) {
        cardModels = movies;
    }

    @NonNull
    @Override
    public YtsAdapter.CardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_item,parent, false);
        return new CardView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardView holder, int position) {
        Log.d(TAG, "onBindViewHolder: "+position);
        holder.setCard(cardModels.get(position));
    }

    @Override
    public int getItemCount() {
        return cardModels.size();
    }

    public static class CardView extends RecyclerView.ViewHolder {


        private TextView tvTitle, tvScore;
        private ImageView ivImage;
        private RatingBar ratingBar;

        public CardView(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvScore = itemView.findViewById(R.id.tv_rating);
            ivImage = itemView.findViewById(R.id.iv_poster);
            ratingBar = itemView.findViewById(R.id.rating_bar);
        }

        public void setCard(YtsData.MyData.Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvScore.setText(movie.getRating() + "");
            Picasso.get().load(movie.getMedium_cover_image()).into(ivImage);
            ratingBar.setRating(movie.getRating() / 2);
        }

    }
}
