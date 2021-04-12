package com.example.randomusergenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements TextWatcher {

    private FirebaseAuth mAuth;
    private SeekBar progressBar1;


    //    private void myMethod(EditText mEmailInput) {
//        mEmailInput.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() > 0) {
//                    if (mEmailInputIsEmpty) {
//                        progressBar1.setProgress(progressBar1.getProgress() + 1);
//                        mEmailInputIsEmpty = false;
//                    }
//                } else {
//                    progressBar1.setProgress(progressBar1.getProgress() -1);
//                    mEmailInputIsEmpty = true;
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        EditText name = findViewById(R.id.firstName);
        EditText last_name = findViewById(R.id.lastName);
        EditText email = findViewById(R.id.email);
        EditText phone = findViewById(R.id.editTextPhone);
        EditText pass = findViewById(R.id.password);
        EditText con_pass = findViewById(R.id.conPass);

        findViewById(R.id.login_layout).setOnClickListener(view -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            View focusedView = LoginActivity.this.getCurrentFocus();
            if (focusedView != null) {
                imm.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        Button create = findViewById(R.id.signUpAccButton);
        progressBar1 = findViewById(R.id.seekBar1);
        progressBar1.setMax(6);
        boolean[] isEmpty = {true,true,true,true,true,true};
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    if (isEmpty[0]) {
                        progressBar1.setProgress(progressBar1.getProgress() + 1);
                        isEmpty[0] = false;
                    }
                } else {
                    progressBar1.setProgress(progressBar1.getProgress() - 1);
                    isEmpty[0] = true;
                }
            }

        });

        last_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    if (isEmpty[1]){
                        progressBar1.setProgress(progressBar1.getProgress() + 1);
                        isEmpty[1] = false;
                    }
                } else {
                    progressBar1.setProgress(progressBar1.getProgress() - 1);
                    isEmpty[1] = true;
                }
            }

        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    if (isEmpty[2]) {
                        progressBar1.setProgress(progressBar1.getProgress() + 1);
                        isEmpty[2] = false;
                    }
                } else {
                    progressBar1.setProgress(progressBar1.getProgress() - 1);
                    isEmpty[2] = true;
                }
            }

        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    if (isEmpty[3]) {
                        progressBar1.setProgress(progressBar1.getProgress() + 1);
                        isEmpty[3] = false;
                    }
                } else {
                    progressBar1.setProgress(progressBar1.getProgress() - 1);
                    isEmpty[3] = true;
                }
            }

        });
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    if (isEmpty[4]) {
                        progressBar1.setProgress(progressBar1.getProgress() + 1);
                        isEmpty[4] = false;
                    }
                } else {
                    progressBar1.setProgress(progressBar1.getProgress() - 1);
                    isEmpty[4] = true;
                }
            }

        });
        con_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    if (isEmpty[5]) {
                        progressBar1.setProgress(progressBar1.getProgress() + 1);
                        isEmpty[5] = false;
                    }
                } else {
                    progressBar1.setProgress(progressBar1.getProgress() - 1);
                    isEmpty[5] = true;
                }
            }

        });


        TextView logInView = findViewById(R.id.loginView);
        logInView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passWord = pass.getText().toString();
                String con_password = con_pass.getText().toString();

                if (name.getText().toString().isEmpty()) {
                    name.setError("First Name Required");
                    name.requestFocus();
                    return;
                } else if (last_name.getText().toString().isEmpty()) {
                    last_name.setError("Last Name Required");
                    last_name.requestFocus();
                    return;
                } else if (email.getText().toString().isEmpty()) {
                    email.setError("Email Required");
                    email.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    email.setError("Valid Email Required");
                    email.requestFocus();
                    return;
                } else if (pass.getText().toString().isEmpty()) {
                    pass.setError("Password Required");
                    pass.requestFocus();
                    return;
                } else if (pass.getText().toString().length() < 6) {
                    pass.setError("Min 6 char required");
                    pass.requestFocus();
                    return;
                } else if (con_pass.getText().toString().isEmpty() || !con_pass.getText().toString().equals(pass.getText().toString())) {
                    con_pass.setError("Password does not matches !!");
                    con_pass.requestFocus();
                    return;
                }


                registerUser(emailText, passWord);
            }
        });


    }

    public void registerUser(String email, String password) {


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //SignUp success
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("Email", email);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Email ID already exists..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {


    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}