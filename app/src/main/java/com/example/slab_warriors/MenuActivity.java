package com.example.slab_warriors;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.slab_warriors.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {
    private ActivityMenuBinding binding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.play.setOnClickListener(v -> {
            Intent toPlay = new Intent(MenuActivity.this,Playfield.class);
            startActivity(toPlay);
            finish();
        });
        /*binding.characters.setOnClickListener(v->{
            Intent toCharacters = new Intent(MenuActivity.this,CharactersActivity.class);
            startActivity(toCharacters);
            finish();
        });*/
        binding.settings.setOnClickListener(v->{
            Intent toSettings = new Intent(MenuActivity.this,SettingsActivity.class);
            startActivity(toSettings);
        });
        binding.exit.setOnClickListener(v->{
            AlertDialog alert;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to quit?");
            builder.setPositiveButton("Yes", (dialog, which) -> finish());
            builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
            builder.setCancelable(false);
            alert = builder.create();
            alert.show();
        });
    }
}