package com.example.restclient;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.example.restclient.GithubService.retrofit;

public class PreActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context con = this;
    private String name;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);
        Intent intent = getIntent();
        name = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        imageView = findViewById(R.id.imageView2);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        update();
                    }
                }
        );
        update();
    }

    public void update(){
        final GithubService service = retrofit.create(GithubService.class);
        Call<List<Follower>> call = service.followers(name);
        Call<User> call1 = service.user(name);

        call.enqueue(new Callback<List<Follower>>() {
            @Override
            public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                mAdapter = new MyAdapter(response.body(), con);
                recyclerView.setAdapter(mAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Follower>> call, Throwable t) {
                Toast.makeText(PreActivity.this,"Cannot get list: " + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                TextView textView = findViewById(R.id.textView);
                TextView textView1 = findViewById(R.id.textView2);
                textView.setText("Repositories: " + response.body().public_repos);
                textView1.setText("Following: " + response.body().following);
                Picasso.with(con).load(response.body().avatar_url).into(imageView);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(PreActivity.this,"Cannot get User info: " + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
