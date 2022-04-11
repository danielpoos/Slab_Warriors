package com.example.slab_warriors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.slab_warriors.data.User;
import com.example.slab_warriors.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {
    private ActivityMenuBinding binding;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (!(getIntent().getStringExtra("welcome") == null))
        Toast.makeText(this, getIntent().getStringExtra("welcome"), Toast.LENGTH_SHORT).show();
        binding.play.setOnClickListener(v->{
            Intent toPlay = new Intent(MenuActivity.this,Playfield.class);
            startActivity(toPlay);
            finish();
        });
        binding.characters.setOnClickListener(v->{
            Intent toCharacters = new Intent(MenuActivity.this,CharactersActivity.class);
            startActivity(toCharacters);
        });
        binding.settings.setOnClickListener(v->{
            Intent toSettings = new Intent(MenuActivity.this,SettingsActivity.class);
            startActivity(toSettings);
        });
        binding.exit.setOnClickListener(v->{
            AlertDialog alert;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.you_sure));
            builder.setPositiveButton(getString(R.string.yes), (dialog, which) -> finish());
            builder.setNeutralButton(getString(R.string.log_out), (dialog, which) -> {
                sharedPref = getSharedPreferences("settings", Context.MODE_PRIVATE);
                editor = sharedPref.edit();
                editor.putBoolean("remember", false);
                editor.commit();
                User.loggedInUser = null;
                startActivity(new Intent(MenuActivity.this, LoginActivity.class));
            });
            builder.setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.cancel());
            builder.setCancelable(false);
            alert = builder.create();
            alert.show();
        });
    }
}