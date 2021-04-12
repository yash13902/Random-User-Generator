package com.example.randomusergenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    TextView MainInformation;
    TextView LivingInformation;
    TextView AgeInformation;
    TextView ContactInformation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainInformation = findViewById(R.id.MainInformation);
        LivingInformation = findViewById(R.id.LivingInformation);
        AgeInformation = findViewById(R.id.AgeInformation);
        ContactInformation = findViewById(R.id.ContactInformation);
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
                String output1 = " ";
                String output2 = " ";
                String output3 = " ";
                String output4 = " ";
                output1 += "Gender:\t" + inf.results.get(0).getGender() + "\n";
                output1 += "Title:\t" + inf.results.get(0).getName().getTitle() + "\n";
                output1 += "First name:\t" + inf.results.get(0).getName().getFirst() + "\n";
                output1 += "Last Name:\t" + inf.results.get(0).getName().getLast() + "\n";
                output2 += "Street Name:\t" + inf.results.get(0).getLocation().getStreet().getName() + "\n";
                output2 += "Street Number:\t" + Integer.toString(inf.results.get(0).getLocation().getStreet().getNumber()) + "\n";
                output2 += "City:\t" + inf.results.get(0).getLocation().getCity() + "\n";
                output2 += "State:\t" + inf.results.get(0).getLocation().getState() + "\n";
                output2 += "Country:\t" + inf.results.get(0).getLocation().getCountry() + "\n";
                output2 += "Postcode:\t" + Integer.toString(inf.results.get(0).getLocation().getPostcode()) + "\n";
                output3 += "Email:\t" + inf.results.get(0).getEmail() + "\n";
                output3 += "Login username:\t" + inf.results.get(0).getLogin().getUsername() + "\n";
                output3 += "Login Password:\t" + inf.results.get(0).getLogin().getPassword() + "\n";
                output4 += "Date of Birth:\t" + inf.results.get(0).getDob().getDate() + "\n";
                output4 += "Age:\t" + inf.results.get(0).getDob().getAge() + "\n";
                output3 += "Phone Number:\t" + inf.results.get(0).getPhone() + "\n";
                output3 += "Cell Number:\t" + inf.results.get(0).getCell() + "\n";
                output2 += "Nationality:\t" + inf.results.get(0).getNat() + "\n";

                MainInformation.setText(output1.trim());
                LivingInformation.setText(output2.trim());
                AgeInformation.setText(output4.trim());
                ContactInformation.setText(output3.trim());

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
