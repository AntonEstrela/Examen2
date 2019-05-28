package com.example.restclient;

import com.example.restclient.models.Museums;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MuseumsService {

    @GET("/api/dataset/museus/format/json/pag-ini/{ini}/pag-fi/{fi}")
    Call<Museums> museums(@Path("ini") int inici, @Path("fi") int fi);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://do.diba.cat")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
