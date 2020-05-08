package com.example.grv5783.lowertogo.AccountPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.grv5783.lowertogo.R;

public class AccountPage extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);

        Button createAccount = (Button) findViewById(R.id.createAccountButton);
        createAccount.setOnClickListener(this);

        Button loginAccount = (Button) findViewById(R.id.loginButton);
        loginAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createAccountButton:
                Intent createIntent = new Intent(this, CreateAccountPage.class);
                startActivity(createIntent);
                break;
            case R.id.loginButton:
                Intent loginIntent = new Intent(this, LoginPage.class);
                startActivity(loginIntent);
                break;
        }
    }

}
