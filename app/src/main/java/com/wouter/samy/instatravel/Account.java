package com.wouter.samy.instatravel;

/**
 * Created by wouter on 25/11/2017.
 */

public class Account {

    String name;
    String password;
    int id;

    public Account(int id,String name,String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
