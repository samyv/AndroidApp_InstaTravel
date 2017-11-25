package com.wouter.samy.instatravel;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void registerButtonR(View view){
        TextView usernameTV = findViewById(R.id.registerUsername);
        final String username = usernameTV.getText().toString();

        TextView passwordTV = findViewById(R.id.registerPassword);
        final String password = passwordTV.getText().toString();

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
                        name = jsonObject.getString("name");
                        if(username.equals(name)){
                            found = true;
                        }
                    } catch (JSONException e) {
                        Toast.makeText(Register.this, "error", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
                if(found){

                    Toast.makeText(getApplicationContext(),"Username already exists!", Toast.LENGTH_LONG).show();
                }else {
                    if(createAccount(username,password)){
                        startActivity(new Intent(Register.this,MainActivity.class));
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, "kieke", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        // Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(request);
    }
    public boolean createAccount(String name,String pass){
        String url ="http://api.a17-sd510.studev.groept.be/CreateAccount/" + name +"/"+pass;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        // Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(request);
        return true;
    }
}
