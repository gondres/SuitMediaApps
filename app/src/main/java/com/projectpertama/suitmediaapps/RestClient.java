package com.projectpertama.suitmediaapps;

import com.projectpertama.suitmediaapps.Interface.PersonAPI;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static PersonAPI service;

    public static PersonAPI getService() {

        if (service == null) {
            // Membuat base URL
            String BASE_URL = "https://reqres.in/";

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.client(httpClient.build()).build();

            service = retrofit.create(PersonAPI.class);
        }
        return service;
    }
}
