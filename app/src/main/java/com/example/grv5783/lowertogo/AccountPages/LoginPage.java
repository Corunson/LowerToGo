package com.example.grv5783.lowertogo.AccountPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.grv5783.lowertogo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    EditText email;
    EditText password;
    FirebaseAuth loginAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        email = (EditText) findViewById(R.id.loginEmailText);
        password = (EditText) findViewById(R.id.loginPasswordText);

        Button login = (Button) findViewById(R.id.loginHomeButton);
        login.setOnClickListener(this);
        loginAuth = loginAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser loginUser = loginAuth.getCurrentUser();
                if(loginUser != null) {
                    Toast.makeText(LoginPage.this,"You are logged in", Toast.LENGTH_SHORT).show();
                    Intent home = new Intent(LoginPage.this, HomePage.class);
                    startActivity(home);
                }
                else {
                    Toast.makeText(LoginPage.this,"Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginHomeButton:
                final Intent loginIntent = new Intent(this, HomePage.class);
                final String emailInfo = email.getText().toString();
                final String passwordInfo = password.getText().toString();
                loginAuth.signInWithEmailAndPassword(emailInfo, passwordInfo).addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginPage.this, "Login Error, Please Login Again", Toast.LENGTH_SHORT).show();
                        } else {
                            //loginIntent.putExtra("EMAIL", emailInfo);
                            //loginIntent.putExtra("PASSWORD",passwordInfo);
                            startActivity(loginIntent);
                        }
                    }
                });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginAuth.addAuthStateListener(authStateListener);
    }
}
