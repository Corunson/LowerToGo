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

public class CreateAccountPage extends AppCompatActivity implements View.OnClickListener {

    EditText createEmail;
    EditText createPassword;
    FirebaseAuth accountAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_page);

        createEmail = (EditText) findViewById(R.id.createEmailText);
        createPassword = (EditText) findViewById(R.id.createPasswordText);

        Button create = (Button) findViewById(R.id.createAccountButton);
        create.setOnClickListener(this);

        accountAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createAccountButton:
                String createEmailInfo = createEmail.getText().toString();
                String createPasswordInfo = createPassword.getText().toString();


                if (createEmailInfo.length() < 21 && (createPasswordInfo.length() < 10 || !createPasswordInfo.matches(".*\\d+.*") || !createPasswordInfo.matches(".*[!@#$%^&.].*"))) {
                    Toast.makeText(getApplicationContext(),
                            "Invalid Email and password requirements.",
                            Toast.LENGTH_LONG).show();

                } else if (createEmailInfo.length() < 21) {
                    Toast.makeText(getApplicationContext(),
                            "Invalid email, too short to exist", Toast.LENGTH_LONG).show();

                } else if (createPasswordInfo.length() < 10 || !createPasswordInfo.matches(".*\\d+.*") || !createPasswordInfo.matches(".*[!@#$%^&.].*")) {
                    Toast.makeText(getApplicationContext(),
                            "Password must be at least 10 characters with at least 1 number and at least 1 special character.", Toast.LENGTH_LONG).show();


                } else if (!createEmailInfo.substring(createEmailInfo.length() - 14, createEmailInfo.length()).contains("@lockhaven.edu")) {
                    Toast.makeText(getApplicationContext(),
                            "Invalid email, must be @lockhaven.edu", Toast.LENGTH_LONG).show();

                } else if (createEmailInfo.substring(createEmailInfo.length() - 14, createEmailInfo.length()).contains("@lockhaven.edu")
                        && createPasswordInfo.length() >= 10 && createPasswordInfo.matches(".*\\d+.*")
                        && createPasswordInfo.matches(".*[!@#$%^&.].*")) {
                    accountAuth.createUserWithEmailAndPassword(createEmailInfo, createPasswordInfo).addOnCompleteListener(CreateAccountPage.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(CreateAccountPage.this,"SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT);
                            } else {
                                startActivity(new Intent(CreateAccountPage.this, AccountPage.class));
                            }
                        }
                    });

                }


        }
    }
}




