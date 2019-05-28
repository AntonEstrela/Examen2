package com.example.restclient;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setVisibility(View.GONE);
        Picasso.with(this).load("https://images-cdn.9gag.com/photo/aOVW88v_700b.jpg").into((ImageView) findViewById(R.id.imageView));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onClick(null);
                button.setVisibility(View.VISIBLE);
            }
        }, 8000);
    }
    public void onClick(View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
