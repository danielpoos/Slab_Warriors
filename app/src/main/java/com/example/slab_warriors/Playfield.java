package com.example.slab_warriors;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.slab_warriors.databinding.ActivityPlayfieldBinding;

public class Playfield extends AppCompatActivity {
    private ActivityPlayfieldBinding binding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayfieldBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}