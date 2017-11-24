package com.wouter.samy.instatravel;

/**
 * Created by samy on 21/11/2017.
 */

public class Profile {
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
