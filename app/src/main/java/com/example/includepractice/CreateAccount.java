package com.example.includepractice;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.includepractice.databinding.CreateAccountBinding;

public class CreateAccount extends AppCompatActivity {

    private CreateAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
