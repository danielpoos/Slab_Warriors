package com.example.slab_warriors;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.slab_warriors.api.RequestTask;
import com.example.slab_warriors.api.Response;
import com.example.slab_warriors.data.User;
import com.example.slab_warriors.databinding.ActivityRegisterBinding;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private String name;
    private String email;
    private String pass;
    private String passAgain;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent toMenu = new Intent(RegisterActivity.this, LoginActivity.class);
        binding.registerText.setText(getString(R.string.register));
        binding.registerButton.setOnClickListener(v->{
            binding.registerButton.setEnabled(false);
            name = binding.userName.getText().toString();
            email = binding.emailAddress.getText().toString();
            pass = binding.password.getText().toString();
            passAgain = binding.passwordAgain.getText().toString();
            if(name.isEmpty() || email.isEmpty() || pass.isEmpty() || passAgain.isEmpty()) {
                registerError(getString(R.string.fill_fields));
                onRestart();
                return;
            }
            else if(!pass.equals(passAgain)) {
                registerError(getString(R.string.not_matching_passwords));
                onRestart();
                return;
            }
            else{
                Gson convert = new Gson();
                RequestTask registerUser = new RequestTask(getString(R.string.baseurl)+"/api/users/register","post",convert.toJson(new User(name, email, pass, passAgain), User.class));
                registerUser.execute();
                registerUser.setFinalTask(()->{
                    if (registerUser.response.getResponseCode()>=400){
                        registerError(getString(R.string.register_already));
                        onRestart();
                        return;
                    }
                    Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show();
                    startActivity(toMenu);
                    finish();
                });
            }
            binding.registerButton.setEnabled(true);
        });
        binding.backButton.setOnClickListener(v->{
            startActivity(toMenu);
            finish();
        });
    }
    @Override protected void onRestart() {
        super.onRestart();
    }
    @Override public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
    private void registerError(String error) {
        binding.registerButton.setEnabled(true);
        binding.registerText.setText(getString(R.string.register_failed));
        binding.errorText.setText(error);
        binding.userName.setText("");
        binding.emailAddress.setText("");
        binding.password.setText("");
        binding.passwordAgain.setText("");
    }
}