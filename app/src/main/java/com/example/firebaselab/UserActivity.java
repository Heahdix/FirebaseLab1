package com.example.firebaselab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {
    private String userLogin;
    private String userPassword;
    private TextView userLoginTextView;
    private TextView userPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Bundle arguments = getIntent().getExtras();
        userLogin = arguments.get("userLogin").toString();
        userPassword = arguments.get("userPassword").toString();

        userLoginTextView = findViewById(R.id.loginTextView);
        userPasswordTextView = findViewById(R.id.passwordTextView);

        userLoginTextView.setText(userLogin);
        userPasswordTextView.setText(userPassword);
    }
}