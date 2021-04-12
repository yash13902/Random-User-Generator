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

        gender.setVisibility(View.INVISIBLE);
        title.setVisibility(View.INVISIBLE);
        firstName.setVisibility(View.INVISIBLE);
        lastName.setVisibility(View.INVISIBLE);
        dob.setVisibility(View.INVISIBLE);
        streetName.setVisibility(View.INVISIBLE);
        age.setVisibility(View.INVISIBLE);
        streetNumber.setVisibility(View.INVISIBLE);
        city.setVisibility(View.INVISIBLE);
        state.setVisibility(View.INVISIBLE);
        country.setVisibility(View.INVISIBLE);
        postCode.setVisibility(View.INVISIBLE);
        nationality.setVisibility(View.INVISIBLE);
        email.setVisibility(View.INVISIBLE);
        loginUsername.setVisibility(View.INVISIBLE);
        loginPassword.setVisibility(View.INVISIBLE);
        phoneNumber.setVisibility(View.INVISIBLE);
        cellNumber.setVisibility(View.INVISIBLE);


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

                firstGenerate.setVisibility(View.INVISIBLE);

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

                gender.setVisibility(View.VISIBLE);
                title.setVisibility(View.VISIBLE);
                firstName.setVisibility(View.VISIBLE);
                lastName.setVisibility(View.VISIBLE);
                dob.setVisibility(View.VISIBLE);
                streetName.setVisibility(View.VISIBLE);
                age.setVisibility(View.VISIBLE);
                streetNumber.setVisibility(View.VISIBLE);
                city.setVisibility(View.VISIBLE);
                state.setVisibility(View.VISIBLE);
                country.setVisibility(View.VISIBLE);
                postCode.setVisibility(View.VISIBLE);
                nationality.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                loginUsername.setVisibility(View.VISIBLE);
                loginPassword.setVisibility(View.VISIBLE);
                phoneNumber.setVisibility(View.VISIBLE);
                cellNumber.setVisibility(View.VISIBLE);

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
}
