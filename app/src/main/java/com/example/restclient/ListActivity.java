package com.example.restclient;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restclient.models.Museums;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.example.restclient.MuseumsService.retrofit;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private Context con = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        progressBar = findViewById(R.id.indeterminateBar);
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
        //swipeRefreshLayout.setRefreshing(true);
        Handler handler = new Handler(); //El internet va muy rapido como para ver bien al progress bar
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                update();
            }
        }, 1000);
    }
    private void showProgress(boolean status){
        progressBar.setVisibility(View.GONE);
    }
    public void update(){
        final MuseumsService service = retrofit.create(MuseumsService.class);
        Call<Museums> call = service.museums(1, 1000);

        call.enqueue(new Callback<Museums>() {
            @Override
            public void onResponse(Call<Museums> call, Response<Museums> response) {
                if(response.isSuccessful()) {
                    Museums museums = response.body();
                    showProgress(false);
                    recyclerView.setAdapter(new MyMuseumsRecyclerViewAdapter(museums.getElements(), con));
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<Museums> call, Throwable t) {
                Toast.makeText(ListActivity.this,"Cannot get list: " + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                showProgress(false);
            }
        });
    }
}
