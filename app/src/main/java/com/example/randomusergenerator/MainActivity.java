package com.example.randomusergenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView gender;
    TextView title;
    TextView firstName;
    TextView lastName;
    TextView dob;
    TextView age;
    TextView streetName;
    TextView streetNumber;
    TextView city;
    TextView state;
    TextView country;
    TextView postCode;
    TextView nationality;
    TextView email;
    TextView loginUsername;
    TextView loginPassword;
    TextView phoneNumber;
    TextView cellNumber;
    TextView firstGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gender = findViewById(R.id.gender);
        title = findViewById(R.id.title);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        dob = findViewById(R.id.dob);
        streetName = findViewById(R.id.streetName);
        age = findViewById(R.id.age);
        streetNumber = findViewById(R.id.streetNumber);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        country = findViewById(R.id.country);
        postCode = findViewById(R.id.postCode);
        nationality = findViewById(R.id.nationality);
        email = findViewById(R.id.email);
        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.password);
        phoneNumber = findViewById(R.id.phoneNumber);
        cellNumber = findViewById(R.id.cellNumber);
        firstGenerate = findViewById(R.id.firstGenerate);

        setAlpha0();


    }

    public void getInformation(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonPlaceHolderAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderAPI jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderAPI.class);
        Call<Information> informationCall = jsonPlaceHolderApi.getInformation();
        informationCall.enqueue(new Callback<Information>() {
            @Override
            public void onResponse(Call<Information> call, Response<Information> response) {

                setAlpha0();

                String output = " ";
                Information inf = response.body();
                output = inf.results.get(0).getDob().getDate().toString().substring(0,11) + inf.results.get(0).getDob().getDate().toString().substring(30);
                gender.setText("Gender:\t" + inf.results.get(0).getGender());
                title.setText("Title:\t" + inf.results.get(0).getName().getTitle());
                firstName.setText("First name:\t" + inf.results.get(0).getName().getFirst());
                lastName.setText("Last Name:\t" + inf.results.get(0).getName().getLast());
                streetName.setText("Street Name:\t" + inf.results.get(0).getLocation().getStreet().getName());
                streetNumber.setText("Street Number:\t" + Integer.toString(inf.results.get(0).getLocation().getStreet().getNumber()));
                city.setText("City:\t" + inf.results.get(0).getLocation().getCity());
                state.setText("State:\t" + inf.results.get(0).getLocation().getState());
                country.setText("Country:\t" + inf.results.get(0).getLocation().getCountry());
                postCode.setText("Postcode:\t" + Integer.toString(inf.results.get(0).getLocation().getPostcode()));
                email.setText("Email:\t" + inf.results.get(0).getEmail());
                loginUsername.setText("Login username:\t" + inf.results.get(0).getLogin().getUsername());
                loginPassword.setText("Login Password:\t" + inf.results.get(0).getLogin().getPassword());
                dob.setText("Date of Birth:\t" + output);
                age.setText("Age:\t" + inf.results.get(0).getDob().getAge());
                phoneNumber.setText("Phone Number:\t" + inf.results.get(0).getPhone());
                cellNumber.setText("Cell Number:\t" + inf.results.get(0).getCell());
                nationality.setText("Nationality:\t" + inf.results.get(0).getNat());

                animation();
            }

            @Override
            public void onFailure(Call<Information> call, Throwable t) {
                getInformation();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.generate:
                firstGenerate.setVisibility(View.GONE);
                getInformation();
                return true;
            case R.id.signOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, StartActivity.class));
                return true;
            default:
                return false;
        }
    }

    public void setAlpha0(){

        gender.setAlpha(0f);
        title.setAlpha(0f);
        firstName.setAlpha(0f);
        lastName.setAlpha(0f);
        dob.setAlpha(0f);
        streetName.setAlpha(0f);
        age.setAlpha(0f);
        streetNumber.setAlpha(0f);
        city.setAlpha(0f);
        state.setAlpha(0f);
        country.setAlpha(0f);
        postCode.setAlpha(0f);
        nationality.setAlpha(0f);
        email.setAlpha(0f);
        loginUsername.setAlpha(0f);
        loginPassword.setAlpha(0f);
        phoneNumber.setAlpha(0f);
        cellNumber.setAlpha(0f);
    }

    public void animation(){

        gender.animate().alpha(1.0f).setDuration(500).start();
        title.animate().alpha(1.0f).setDuration(1000).start();
        firstName.animate().alpha(1.0f).setDuration(1500).start();
        lastName.animate().alpha(1.0f).setDuration(2000).start();
        dob.animate().alpha(1.0f).setDuration(2500).start();
        streetName.animate().alpha(1.0f).setDuration(3000).start();
        age.animate().alpha(1.0f).setDuration(3500).start();
        streetNumber.animate().alpha(1.0f).setDuration(4000).start();
        city.animate().alpha(1.0f).setDuration(4500).start();
        state.animate().alpha(1.0f).setDuration(5000).start();
        country.animate().alpha(1.0f).setDuration(5500).start();
        postCode.animate().alpha(1.0f).setDuration(6000).start();
        nationality.animate().alpha(1.0f).setDuration(6500).start();
        email.animate().alpha(1.0f).setDuration(7000).start();
        loginUsername.animate().alpha(1.0f).setDuration(7500).start();
        loginPassword.animate().alpha(1.0f).setDuration(8000).start();
        phoneNumber.animate().alpha(1.0f).setDuration(8500).start();
        cellNumber.animate().alpha(1.0f).setDuration(9000).start();
    }
}
