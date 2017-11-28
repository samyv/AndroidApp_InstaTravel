package com.wouter.samy.instatravel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebAPI extends AppCompatActivity {
    //pls kill me now
    //lel
    Cache cache;
    Network network;
    RequestQueue queue;
    String url;
    TextView mTextView;
    String name;
    String string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_api);
        mTextView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button2);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
        cache = new DiskBasedCache(getCacheDir(),1024*1024);
        network = new BasicNetwork(new HurlStack());
        queue = new RequestQueue(cache, network);
        queue.start();
    }

    public void Connect(View view){
        EditText text = (EditText) findViewById(R.id.editText2);
        name = text.getText().toString();
        // Request a string response from the provided URL.
        url ="http://api.a17-sd510.studev.groept.be/createRegister/"+ name;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        string = jsonObject.getString("id");
                        string += '\n';
                        string += jsonObject.getString("name");
                        mTextView.setText(string);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WebAPI.this, "kieke", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(request);
    }

}
