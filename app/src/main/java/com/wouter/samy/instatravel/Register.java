package com.wouter.samy.instatravel;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void registerButtonR(View view){
        TextView usernameTV = findViewById(R.id.registerUsername);
        String username = usernameTV.getText().toString();

        TextView passwordTV = findViewById(R.id.registerPassword);
        String password = passwordTV.getText().toString();
        Profile regProfile = new Profile(username,password);
        ProfileMapper.UNIQUEINSTANCE.createProfile(regProfile);
        Toast.makeText(getApplicationContext(),"Succeeded!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
    }
}
