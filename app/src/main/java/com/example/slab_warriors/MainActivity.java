package com.example.slab_warriors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private AppCompatImageView imageView;
    private int timeout = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ini();
    }
    public void ini(){
        imageView = findViewById(R.id.first_sreen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent toLoading = new Intent(MainActivity.this, LoadingActivity1.class);
                startActivity(toLoading);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        },timeout);
    }
}