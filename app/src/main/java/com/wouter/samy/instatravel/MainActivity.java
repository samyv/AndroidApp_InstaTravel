package com.wouter.samy.instatravel;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import junit.framework.Test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {
    Profile test = new Profile();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MySingleton.getInstance(this);
        boolean logout = getIntent().getBooleanExtra("logout",false);
        if(logout){
            //TODO: LOG A PERSON OUT
        }
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        int ACCESS_FINE_LOCATION = 0b00001111;
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        ACCESS_FINE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }


    public void loginButton(View view){
        EditText text = (EditText) findViewById(R.id.loginName);
        EditText textpass = (EditText) findViewById(R.id.loginPassword);
        final String testname = text.getText().toString();
        final String testpass = textpass.getText().toString();
        String url ="http://api.a17-sd510.studev.groept.be/getAccount";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String name = "";
                String pass = "";
                String firstname = "";
                String lastname = "";
                String email = "";
                Boolean found = false;
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String hold = jsonObject.getString("id");
                        int id = Integer.parseInt(hold);
                        name = jsonObject.getString("name");
                        pass = jsonObject.getString("password");
                        firstname = jsonObject.getString("firstname");
                        lastname = jsonObject.getString("lastname");
                        email = jsonObject.getString("email");
                        if(testname.equals(name) && testpass.equals(pass)){
                            test.setId(id);
                            test.setUsername(name);
                            test.setEmail(email);
                            test.setFirstname(firstname);
                            test.setLastname(lastname);
                            found = true;
                            Profile.setLoggedIn(test);
                        }
                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
                if(found){
                    startActivity(new Intent(MainActivity.this,HomeScreen.class));
                }else {
                    Toast.makeText(MainActivity.this, "bestaat niet", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "kieke", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        // Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(request);
    }

    public void registerButton(View view){

        startActivity(new Intent(this,Register.class));
    }
}
