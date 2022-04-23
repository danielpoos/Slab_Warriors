package com.example.slab_warriors;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import com.example.slab_warriors.api.RequestTask;
import com.example.slab_warriors.data.User;

public class LoadingActivity1 extends AppCompatActivity {
    private SharedPreferences sharedPref;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading1);
        splashScreenActivation();
    }
    private void splashScreenActivation() {
        int timeout = 2000;
        new Handler().postDelayed(() -> {
            sharedPref = getSharedPreferences("settings", Context.MODE_PRIVATE);
            boolean isLoggedIn = sharedPref.getBoolean("remember", false);
            String username = sharedPref.getString("username", "");
            if (isLoggedIn) {
                if (!username.equals("")){
                    RequestTask getUserInsteadLogin = new RequestTask(getString(R.string.baseurl)+"/api/users", "get");
                    getUserInsteadLogin.execute();
                    getUserInsteadLogin.setFinalTask(() -> {
                        User.getUser(getUserInsteadLogin.response.getContent(),username);
                        startActivity(new Intent(LoadingActivity1.this, MenuActivity.class));
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    });
                    return;
                }
            }
            User.loggedInUser = null;
            startActivity(new Intent(LoadingActivity1.this, LoginActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }, timeout);
    }
}