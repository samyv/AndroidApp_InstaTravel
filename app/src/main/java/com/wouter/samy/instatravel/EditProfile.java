package com.wouter.samy.instatravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Profile test = Profile.test;

        TextView firstN,lastN,userN,email;
        firstN = findViewById(R.id.editprofile_firstname);
        lastN = findViewById(R.id.editprofile_lastname);
        userN = findViewById(R.id.editprofile_username);
        email = findViewById(R.id.editprofile_email);

        firstN.setText(test.getFirstname());
        lastN.setText(test.getLastname());
        userN.setText(test.getUsername());
        email.setText(test.getEmail());


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editprofile_menu, menu);
        return true;
    }

    public boolean saveButton(MenuItem menu){
        //TODO: send values to databank
        return true;
    }
}
