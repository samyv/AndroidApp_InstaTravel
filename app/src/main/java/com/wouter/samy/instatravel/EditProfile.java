package com.wouter.samy.instatravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;

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
        String id = String.valueOf(Profile.test.getId());
        TextView username = findViewById(R.id.editprofile_username);
        String usernametext = username.getText().toString();
        Profile.test.setUsername(username.getText().toString());
        TextView firstname = findViewById(R.id.editprofile_firstname);
        String firstnametext = firstname.getText().toString();
        Profile.test.setFirstname(firstname.getText().toString());
        TextView lastname = findViewById(R.id.editprofile_lastname);
        String lastnametext = lastname.getText().toString();
        Profile.test.setLastname(lastname.getText().toString());
        TextView email = findViewById(R.id.editprofile_email);
        String emailtext = email.getText().toString();
        Profile.test.setEmail(email.getText().toString());
        String url ="http://api.a17-sd510.studev.groept.be/UpdateAccount/"+usernametext+"/"+firstnametext+"/"+lastnametext+"/"+emailtext+"/"+id;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditProfile.this, "kieke", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        // Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(request);

        Profile.test.setUpdated(false);
        final String idc = id;
        final String username1 = usernametext;
        final String firstname1 = firstnametext;
        final String lastname1 = lastnametext;
        final String email1 = emailtext;

        url ="http://api.a17-sd510.studev.groept.be/getAccount";
        request = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String name = "";
                String first = "";
                String last = "";
                String emal = "";

                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String hold = jsonObject.getString("id");
                        name = jsonObject.getString("name");
                        first = jsonObject.getString("firstname");
                        last = jsonObject.getString("lastname");
                        emal = jsonObject.getString("email");
                        if(hold.equals(idc) && username1.equals(name) && firstname1.equals(first) && lastname1.equals(last) && email1.equals(emal)){
                            Toast.makeText(EditProfile.this, "Save successful", Toast.LENGTH_SHORT).show();
                            Profile.test.setUpdated(true);
                            startActivity(new Intent(EditProfile.this,Profile.class));
                        }
                    } catch (JSONException e) {
                        Toast.makeText(EditProfile.this, "error", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditProfile.this, "kieke", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        // Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(request);
        if (Profile.test.getUpdated()){
            Toast.makeText(EditProfile.this, "Updated", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    /*public void checkUpdate(final String username, final String firstname, final String lastname, final String email, final String id){
        Profile.test.setUpdated(false);
        final String idc = id;
        Boolean found = false;
        String url ="http://api.a17-sd510.studev.groept.be/getAccount";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String name = "";
                String first = "";
                String last = "";
                String emal = "";

                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String hold = jsonObject.getString("id");
                        name = jsonObject.getString("name");
                        first = jsonObject.getString("firstname");
                        last = jsonObject.getString("lastname");
                        emal = jsonObject.getString("email");
                        if(hold.equals(idc) && username.equals(name) && firstname.equals(first) && lastname.equals(last) && email.equals(emal)){
                            Toast.makeText(EditProfile.this, "Save successful", Toast.LENGTH_SHORT).show();
                            Profile.test.setUpdated(true);
                        }
                    } catch (JSONException e) {
                        Toast.makeText(EditProfile.this, "error", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditProfile.this, "kieke", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        // Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(request);
    }*/
}
