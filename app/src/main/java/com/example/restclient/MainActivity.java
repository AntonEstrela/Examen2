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

import static com.example.restclient.GitHubService.retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){
        GitHubService gitHubService = retrofit.create(GitHubService.class);
        Call<List<Contributor>> call = gitHubService.repoContributors("square", "retrofit");
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Response<List<Contributor>> response, Retrofit retrofit) {
                foreach (Contributor contr:response.body()){
                    RecyclerView.
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // handle failure
            }
        });
    }
}
