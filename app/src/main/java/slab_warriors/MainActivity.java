package slab_warriors;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import slab_warriors.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        ini();
    }
    public void ini(){
        int timeout = 1500;
        new Handler().postDelayed(() -> {
            Intent toLoading = new Intent(MainActivity.this, LoadingActivity1.class);
            startActivity(toLoading);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }, timeout);
    }
}