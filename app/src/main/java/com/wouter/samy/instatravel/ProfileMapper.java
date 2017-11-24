package com.wouter.samy.instatravel;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by samy on 21/11/2017.
 */

public enum ProfileMapper {

    UNIQUEINSTANCE;

    private ProfileMapper(){

    }

    public Profile getProfileById(int id) {
        String select = "SELECT id, name, pass FROM Profiles where id = ?";
        Profile Profile = null;
        try {
            PreparedStatement prepstat = DatabaseTest.UNIQUEINSTANCE.getConnection().prepareStatement(select);
            prepstat.setInt(1, id);
            Profile = queryProfile(prepstat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Profile;
    }

    public Profile getProfileByName(Profile Profile) {
        String select = "SELECT id, name, password FROM Profiles where name = ?";
        Profile Profile1 = null;
        try {
            PreparedStatement prepstat = DatabaseTest.UNIQUEINSTANCE.getConnection().prepareStatement(select);
            prepstat.setString(1, Profile.getUsername());
            Profile1 = queryProfile(prepstat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Profile1;
    }
    public Profile getProfileByName(String name) {
        String select = "SELECT id, name, password FROM Profiles where name = ?";
        Profile Profile1 = null;
        try {
            PreparedStatement prepstat = DatabaseTest.UNIQUEINSTANCE.getConnection().prepareStatement(select);
            prepstat.setString(1, name);
            Profile1 = queryProfile(prepstat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Profile1;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int createProfile(Profile Profile) {
        int id = -1;
        Profile Profile1 = getProfileByName(Profile);
        if(Profile1 == null) {
            String sql = "INSERT INTO Profiles (id, name, password) VALUES (?,?,?)";
            try (PreparedStatement pstmt = DatabaseTest.UNIQUEINSTANCE.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, Profile.getId());
                pstmt.setString(2, Profile.getUsername());
                pstmt.setString(3, Profile.getPassword());
                // executeUpdate() should be called to change something in the database
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs != null) {
                        if (rs.next()) {
                            id = rs.getInt(1);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {

            id = Profile1.getId();
        }
        return id;

    }

    private Profile queryProfile(PreparedStatement prepstat) {
        Profile Profile = null;
        ResultSet rs = null;
        try {
            rs = prepstat.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                Profile = new Profile(id, name, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                prepstat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return Profile;
    }
    public Boolean loginProfile(String name, String password) {
        Profile Profile = getProfileByName(name);
        if (password.equals(Profile.getPassword())){
            return true;
        } else {
            return false;
        }
    }

}
