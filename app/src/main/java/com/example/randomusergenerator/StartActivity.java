package com.example.randomusergenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button register;
    private Button accountExists;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        email = findViewById(R.id.editTextTextEmailAddress2);
        password = findViewById(R.id.editTextTextPassword);
        register = findViewById(R.id.registerButton);
        accountExists = findViewById(R.id.accountExistsButton);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(StartActivity.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                } else if(txt_password.length() < 6){
                    Toast.makeText(StartActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                } else{
                    registerUser(txt_email, txt_password);
                }
            }
        });

        accountExists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this , LoginActivity.class));
                finish();
            }
        });

    }

    private void registerUser(String txt_email, String txt_password) {

        auth.createUserWithEmailAndPassword(txt_email, txt_password).addOnCompleteListener(StartActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(StartActivity.this, "Registering User Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                    finish();
                } else{
                    Toast.makeText(StartActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}