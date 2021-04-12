package com.example.randomusergenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView2);


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
                Information inf = response.body();
                String output = " ";
                output += "Gender:\t" + inf.results.get(0).getGender() + "\n";
                output += "Title:\t" + inf.results.get(0).getName().getTitle() + "\n";
                output += "First name:\t" + inf.results.get(0).getName().getFirst() + "\n";
                output += "Last Name:\t" + inf.results.get(0).getName().getLast() + "\n";
                output += "Street Name:\t" + inf.results.get(0).getLocation().getStreet().getName() + "\n";
                output += "Street Number:\t" + Integer.toString(inf.results.get(0).getLocation().getStreet().getNumber()) + "\n";
                output += "City:\t" + inf.results.get(0).getLocation().getCity() + "\n";
                output += "State:\t" + inf.results.get(0).getLocation().getState() + "\n";
                output += "Country:\t" + inf.results.get(0).getLocation().getCountry() + "\n";
                output += "Postcode:\t" + Integer.toString(inf.results.get(0).getLocation().getPostcode()) + "\n";
                output += "Email:\t" + inf.results.get(0).getEmail() + "\n";
                output += "Login username:\t" + inf.results.get(0).getLogin().getUsername() + "\n";
                output += "Login Password:\t" + inf.results.get(0).getLogin().getPassword() + "\n";
                output += "Date of Birth:\t" + inf.results.get(0).getDob().getDate() + "\n";
                output += "Age:\t" + inf.results.get(0).getDob().getAge() + "\n";
                output += "Phone Number:\t" + inf.results.get(0).getPhone() + "\n";
                output += "Cell Number:\t" + inf.results.get(0).getCell() + "\n";
                output += "Nationality:\t" + inf.results.get(0).getNat() + "\n";

                textView.setText(output.trim());

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
