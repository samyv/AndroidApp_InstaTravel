package com.wouter.samy.instatravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by samy on 21/11/2017.
 */

public class Profile extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //for testing purpose
        Profile test = new Profile(1,"samyvv","WouterIsLelijk","Samy","Van Vooren");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return true;
    }

    public Profile(){
    }

    public void editButton(View view){
        Intent intent = new Intent(this, EditProfile.class);
    }
    String username,password,firstname,lastname;
    int id;


    public Profile(int id, String username, String password,String firstname, String lastname){
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname  = firstname;
        this.lastname = lastname;
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
}
