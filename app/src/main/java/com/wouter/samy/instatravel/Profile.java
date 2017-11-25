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
        test = new Profile(1,"samyvv","WouterIsLe-m "lijk","Samy","Van Vooren","samyvanv@hotmail.com");

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

    public static boolean isValid(String username, String password){
        if(ProfileMapper.UNIQUEINSTANCE.loginProfile(username, password)){
            Profile user = ProfileMapper.UNIQUEINSTANCE.getProfileByName(username);
            return true;
        } else {
            return false;
        }
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

    public static Profile getCurrentLogedIn(){
        //TODO: find a way to get the current one
        return test;
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
