package com.example.restclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import com.example.restclient.Track;
import com.example.restclient.TrackService;

import static com.example.restclient.TrackService.retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){
        TrackService trackService = retrofit.create(TrackService.class);
        Call<List<Track>> call = trackService.tracks();
        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                for (Track t: response.body()) {

                }
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                // handle failure
            }
        });
    }
}
