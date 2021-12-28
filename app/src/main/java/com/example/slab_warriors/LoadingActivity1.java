package com.example.slab_warriors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingActivity1 extends AppCompatActivity {

    private int timout = 3500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading1);
        splashScreenActivation();
    }

    private void splashScreenActivation() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toMenu = new Intent(LoadingActivity1.this, MenuActivity.class);
                startActivity(toMenu);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }, timout);
    }
}