package com.example.slab_warriors;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.slab_warriors.api.RequestTask;
import com.example.slab_warriors.api.Response;
import com.example.slab_warriors.data.User;
import com.example.slab_warriors.databinding.ActivityLoginBinding;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private final String userUrl = "http://192.168.1.94:8000/api/users";
    private final String loginUrl = userUrl+"/login";
    private User currentUser;
    private String name;
    private String pass;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.loginOrRegister.setText(getString(R.string.log_or_reg));
        binding.error.setText("");
        binding.rememberCheckBox.setChecked(false);
        binding.loginButton.setOnClickListener(v->{
            name = binding.userName.getText().toString();
            pass = binding.password.getText().toString();
            if (name.isEmpty() || pass.isEmpty()) {
                loginError(getString(R.string.login_empty));
                onRestart();
                return;
            }
            Gson convert = new Gson();
            RequestTask loginUser = new RequestTask(loginUrl,"post", convert.toJson(new User(name,pass), User.class));
            loginUser.execute();
            loginUser.setFinalTask(()->{
                RequestTask userTask = new RequestTask(userUrl,"get");
                userTask.execute();
                userTask.setFinalTask(()-> {
                    currentUser = User.getUser(userTask.response.getContent(),name);
                    if (currentUser == null) {
                        loginError(getString(R.string.login_invalid));
                        onRestart();
                        return;
                    }
                    if (loginUser.response.getResponseCode()>=400){
                        loginError(convert.fromJson(loginUser.response.getContent(), Response.class).getContent());
                        onRestart();
                        return;
                    }
                    sharedPref = getSharedPreferences("settings", Context.MODE_PRIVATE);
                    editor = sharedPref.edit();
                    editor.putBoolean("remember", binding.rememberCheckBox.isChecked());
                    editor.putString("username", User.loggedInUser.getUsername());
                    editor.commit();
                    Intent toMenu = new Intent(LoginActivity.this, MenuActivity.class);
                    toMenu.putExtra("welcome", getString(R.string.welcome)+" "+User.loggedInUser.getUsername()+ "!");
                    startActivity(toMenu);
                    finish();
                });
            });
        });
        binding.registerButton.setOnClickListener(v->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        });
    }
    @Override protected void onRestart() {
        super.onRestart();
        binding.rememberCheckBox.setChecked(false);
    }
    private void loginError(String error) {
        if (error.isEmpty()) error = "We ran into some problems";
        binding.loginOrRegister.setText(getString(R.string.login_failed));
        binding.error.setText(error);
        binding.userName.setText("");
        binding.password.setText("");
    }
}