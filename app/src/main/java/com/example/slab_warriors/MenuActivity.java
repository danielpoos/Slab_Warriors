package com.example.slab_warriors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    private AppCompatButton playBtn, charactersBtn, settingsBtn, exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initialize();
        playBtn.setOnClickListener(v -> {
//TODO matchmaking
        });
        charactersBtn.setOnClickListener(v->{
//TODO characters
        });
        settingsBtn.setOnClickListener(v->{
//TODO settings
        });
        exitBtn.setOnClickListener(v->{

        });

    }
    public void initialize(){
        playBtn = findViewById(R.id.play);
        charactersBtn = findViewById(R.id.characters);
        settingsBtn = findViewById(R.id.settings);
        exitBtn = findViewById(R.id.exit);

    }
}