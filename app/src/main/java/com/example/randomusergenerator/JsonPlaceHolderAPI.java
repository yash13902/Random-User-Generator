package com.example.randomusergenerator;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    String BASE_URL = "https://randomuser.me/";
    @GET("api")
    Call<Information> getInformation();




}
