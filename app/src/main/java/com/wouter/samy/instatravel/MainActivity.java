package com.wouter.samy.instatravel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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

public class MainActivity extends AppCompatActivity {
    public Account test;
    public Cache cache;
    public Network network;
    public RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cache = new DiskBasedCache(getCacheDir(),1024*1024);
        network = new BasicNetwork(new HurlStack());
        //queue = Volley.newRequestQueue(this);
        //queue.start();
        MySingleton.getInstance(this);
        boolean logout = getIntent().getBooleanExtra("logout",false);
        if(logout){
            //TODO: LOG A PERSON OUT
        }
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
                Boolean found = false;
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String hold = jsonObject.getString("id");
                        int id = Integer.parseInt(hold);
                        name = jsonObject.getString("name");
                        pass = jsonObject.getString("password");
                        if(testname.equals(name) && testpass.equals(pass)){
                            found = true;
                        }
                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
                if(found){
                    startActivity(new Intent(MainActivity.this,HomeScreen.class));
                }else {
                    Toast.makeText(MainActivity.this, "betaat niet", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(this,WebAPI.class));
    }
}
