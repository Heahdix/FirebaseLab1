package com.example.firebaselab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText loginEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private String USER_KEY="User";

    DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userLogin = loginEditText.getText().toString();
                String userPassword = passwordEditText.getText().toString();

                Query query = mDataBase.orderByChild("password").equalTo(userPassword).limitToFirst(1);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                User user = snapshot.getValue(User.class);
                                if(user.login.equals(userLogin)){
                                    Toast.makeText(LoginActivity.this, "Добро пожаловать, " + user.login, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                                    intent.putExtra("userLogin", user.login);
                                    intent.putExtra("userPassword", user.password);
                                    startActivity(intent);
                                }
                            }
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Пользователь не найден!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(LoginActivity.this, "Ошибка: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
