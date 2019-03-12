package com.example.restclient;
import com.example.restclient.Track;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface TrackService {
    @GET("dsaApp/tracks")
    Call<List<Track>> tracks();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://147.83.7.203:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}