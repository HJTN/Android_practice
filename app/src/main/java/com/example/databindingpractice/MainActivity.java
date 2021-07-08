package com.example.databindingpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.databindingpractice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.toggleButton.setOnClickListener(this::toggleViews);

        Bio bio = new Bio();
        bio.setName("HJTN");
        bio.setHobbies("Studying android studio, ... etc");
        binding.setBio(bio);
    }

    private void toggleViews(View view) {
        int visibility;
        String buttonText;

        switch (binding.profileImage.getVisibility()) {
            case View.VISIBLE:
                visibility = View.GONE;
                buttonText = "SHOW";
                break;

            default:
                visibility = View.VISIBLE;
                buttonText = "HIDE";
        }

        binding.profileImage.setVisibility(visibility);
        binding.name.setVisibility(visibility);
        binding.hobbies.setVisibility(visibility);
        binding.toggleButton.setText(buttonText);
    }
}