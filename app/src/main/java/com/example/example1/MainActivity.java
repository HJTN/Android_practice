package com.example.example1;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    TextInputEditText TextInputEditText_email, TextInputEditText_password;
    RelativeLayout RelativeLayout_login;

    String emailOk = "123@gmail.com";
    String passwordOk = "1234";
    String inputemail = "";
    String inputpassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText_email     = findViewById(R.id.TextInputEditText_email);
        TextInputEditText_password  = findViewById(R.id.TextInputEditText_password);
        RelativeLayout_login        = findViewById(R.id.RelativeLayout_login);

        // 1. 값을 가져옴 -> 검사 (123@gmail.com / 1234)
        // 2. 클릭을 감지
        // 3. 1번의 값을 다음 액티비티로 넘김

        RelativeLayout_login.setClickable(false);
        TextInputEditText_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Log.d("HJTN", s + ", " + count);
                if(s != null) {
                    inputemail = s.toString();
                    RelativeLayout_login.setClickable(validation());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TextInputEditText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.d("HJTN", s + ", " + count);
                if(s != null) {
                    inputpassword = s.toString();
                    RelativeLayout_login.setClickable(validation());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        RelativeLayout_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = TextInputEditText_email.getText().toString();
                String password = TextInputEditText_password.getText().toString();

                Intent intent = new Intent(MainActivity.this, LoginResultActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });
    }
    public boolean validation() {
        return inputemail.equals(emailOk) && inputpassword.equals(passwordOk);
    }
}