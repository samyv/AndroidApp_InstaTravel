package com.wouter.samy.instatravel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

            }


    public void loginButton(View view){
       /* TextView loginName = findViewById(R.id.loginName);
        String username = loginName.getText().toString();
        TextView loginPassword = findViewById(R.id.loginPassword);
        String password = loginPassword.getText().toString();
        if(Profile.isValid(username,password)){
            Toast toast = Toast.makeText(getApplicationContext(),"Login succesfully!",Toast.LENGTH_LONG);
            Intent intent = new Intent(this,HomeScreen.class);
            intent.putExtra("username", username);
            intent.putExtra("password",password);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),"username and password don't match",Toast.LENGTH_LONG);
        }*/
        startActivity(new Intent(this, TestMaps.class));

    }

    public void registerButton(View view){
        startActivity(new Intent(this,Register.class));
    }
}
