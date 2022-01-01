package com.projectpertama.suitmediaapps.Interface;

import com.projectpertama.suitmediaapps.ListUserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonAPI {


    @GET("api/users?page=1")
    Call<ListUserResponse> getList();

    @GET("api/users?page=2")
    Call<ListUserResponse> getListSecond();
}
