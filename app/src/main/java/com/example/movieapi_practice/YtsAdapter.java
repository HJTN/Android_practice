package com.example.movieapi_practice;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class YtsAdapter extends RecyclerView.Adapter<YtsAdapter.CardView> {
    private static final String TAG = "YtsAdapter";

    private List<YtsData.MyData.Movie> cardModels = new ArrayList<>();

    public void addCardModel(YtsData.MyData.Movie movie) {
        cardModels.add(movie);
    }

    public void addCardModel(List<YtsData.MyData.Movie> movies) {
        cardModels.addAll(movies);
    }

    public List<YtsData.MyData.Movie> getCardModels() {
        return cardModels;
    }

    public void removeCardModel() {
        this.cardModels.clear();
    }

    @NonNull
    @Override
    public YtsAdapter.CardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_item,parent, false);
        //Log.d(TAG, "onCreateViewHolder : " + view.toString());
        return new CardView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardView holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
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
        private List<YtsData.MyData.Movie> images = new ArrayList<>();

        public CardView(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvScore = itemView.findViewById(R.id.tv_rating);
            ivImage = itemView.findViewById(R.id.iv_poster);
            ratingBar = itemView.findViewById(R.id.rating_bar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), MovieDetailActivity.class);
                    intent.putExtra("image", images.get(getAdapterPosition()).getMedium_cover_image());
                    intent.putExtra("title", images.get(getAdapterPosition()).getTitle());
                    intent.putExtra("summary", images.get(getAdapterPosition()).getSummary());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(itemView.getContext(),intent, null);
                }
            });
        }

        public void setCard(YtsData.MyData.Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvScore.setText(movie.getRating() + "");
            Picasso.get().load(movie.getMedium_cover_image()).into(ivImage);
            images.add(movie);
            ratingBar.setRating(movie.getRating() / 2);

        }

    }
}
