package com.example.includepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.includepractice.databinding.ActivityMainBinding;
import com.example.includepractice.databinding.LayoutLoginBinding;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.includes.TIETUN.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    return true;
                }
                return false;
            }
        });
        binding.includes.TIETPW.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    binding.btLogin.requestFocus();
                    return true;
                }
                return false;
            }
        });

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.includes.TIETUN.getText().toString();
                String password = binding.includes.TIETPW.getText().toString();

                String cu = getText(R.string.correct_username).toString();
                String cp = getText(R.string.correct_password).toString();

                if(username.equals(cu) && password.equals(cp)) {
                    startActivity(new Intent(getBaseContext(), HomeActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(), "No account, Try again!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.btCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateAccount.class));
            }
        });
    }
}