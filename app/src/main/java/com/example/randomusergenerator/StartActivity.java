package com.example.randomusergenerator;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null) {

            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        super.onStart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mAuth = FirebaseAuth.getInstance();
        EditText email = findViewById(R.id.emailLogIn);
        EditText pass = findViewById(R.id.passwordLogIn);
        findViewById(R.id.start_layout).setOnClickListener(view -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            View focusedView = StartActivity.this.getCurrentFocus();
            if (focusedView != null) {
                imm.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        Button login = findViewById(R.id.login);

        TextView signUpView = findViewById(R.id.signUpView);
        signUpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passWord = pass.getText().toString();

                if(email.getText().toString().isEmpty()) {
                    email.setError("Email Required");
                    email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    email.setError("Valid Email Required");
                    email.requestFocus();
                    return;
                }
                if(pass.getText().toString().isEmpty()) {
                    pass.setError("Password Required");
                    pass.requestFocus();
                    return;
                }
                if(pass.getText().toString().length()<6) {
                    pass.setError("Min 6 char required");
                    pass.requestFocus();
                    return;
                }

                loginUser(emailText,passWord);
            }
        });


    }

    public void loginUser(String email, String password)
    {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    if(task.isSuccessful()) {
                        //Log In success
                        Intent intent = new Intent(StartActivity.this,MainActivity.class);
                        intent.putExtra("Email",email);
                        startActivity(intent);
                        finish();
                    }
                    else
                        Toast.makeText(this, "Invalid Email / Password", Toast.LENGTH_SHORT).show();
                });
    }

}