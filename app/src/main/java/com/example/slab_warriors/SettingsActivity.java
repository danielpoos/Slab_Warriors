package com.example.slab_warriors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.slab_warriors.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {
    private ActivitySettingsBinding binding;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private final Uri webpageUrl = Uri.parse("https://www.github.com/TotpalIstvan/SlabWarriorsFrontend");
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPref = getSharedPreferences("settings", Context.MODE_PRIVATE);
        String name = sharedPref.getString("name", "Default Name");
        boolean remember = sharedPref.getBoolean("remember", false);
        boolean email = sharedPref.getBoolean("email", false);
        boolean token = sharedPref.getBoolean("token", false);
        binding.displayedName.setText(name);
        binding.rememberMe.setChecked(remember);
        binding.email.setChecked(email);
        binding.token.setChecked(token);
        binding.accountSettings.setOnClickListener(v->{
            if (binding.displayedName.getVisibility() == View.VISIBLE){
                binding.displayedName.setVisibility(View.GONE);
                binding.rememberMe.setVisibility(View.GONE);
            }
            else{
                binding.displayedName.setVisibility(View.VISIBLE);
                binding.rememberMe.setVisibility(View.VISIBLE);
            }
        });
        binding.otherSettings.setOnClickListener(v->{
            if (binding.token.getVisibility() == View.VISIBLE){
                binding.email.setVisibility(View.GONE);
                binding.token.setVisibility(View.GONE);
            }
            else{
                binding.email.setVisibility(View.VISIBLE);
                binding.token.setVisibility(View.VISIBLE);
            }
        });
        binding.aboutSettings.setOnClickListener(v->{
            if (binding.toTheWebpage.getVisibility() == View.VISIBLE){
                binding.toTheWebpage.setVisibility(View.GONE);
            }
            else{
                binding.toTheWebpage.setVisibility(View.VISIBLE);
            }
        });
        binding.save.setOnClickListener(v->{
            sharedPref = getSharedPreferences("settings", Context.MODE_PRIVATE);
            editor = sharedPref.edit();
            editor.putString("name", binding.displayedName.getText().toString());
            editor.putBoolean("remember", binding.rememberMe.isChecked());
            editor.putBoolean("email", binding.email.isChecked());
            editor.putBoolean("token", binding.token.isChecked());
            editor.commit();
        });
        binding.toTheWebpage.setOnClickListener(v-> {
            startActivity(new Intent(Intent.ACTION_VIEW, webpageUrl));
        });
    }
}