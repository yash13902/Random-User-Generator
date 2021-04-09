package com.example.randomusergenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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

        getInformation();
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
                
            }

            @Override
            public void onFailure(Call<Information> call, Throwable t) {
                textView.setText("Could not find the place!");
            }
        });

    }
}
