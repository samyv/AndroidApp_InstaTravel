package com.wouter.samy.instatravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by samy on 21/11/2017.
 */

public class Profile extends AppCompatActivity{
     public static Profile test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //for testing purpose

        TextView firstN,lastN,userN,email;
        firstN = findViewById(R.id.profile_firstname);
        lastN = findViewById(R.id.profile_lastname);
        userN = findViewById(R.id.profile_username);
        email = findViewById(R.id.profile_email);

        firstN.setText(test.getFirstname());
        lastN.setText(test.getLastname());
        userN.setText(test.getUsername());
        email.setText(test.getEmail());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return true;
    }

    public Profile(){
    }
    public boolean editButton(MenuItem item) {
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
        return true;
    }

    String username,password,firstname,lastname,email;
    Boolean Updated = true;
    int id;

    public Profile(int id, String username, String password,String firstname, String lastname){
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname  = firstname;
        this.lastname = lastname;
    }
    public Profile(int id, String username, String password,String firstname, String lastname,String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname  = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public Profile(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public Profile(String username, String password){
        this.username = username;
        this.password = password;
    }


    public static void setLoggedIn(Profile profile){
        test = profile;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getUpdated() {
        return Updated;
    }

    public void setUpdated(Boolean updated) {
        Updated = updated;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }
}
