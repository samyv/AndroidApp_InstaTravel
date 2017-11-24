package com.wouter.samy.instatravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Spinner spinner = (Spinner) findViewById(R.id.HomeScreenSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.HomeScreen_dropDown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        startActivity(new Intent(HomeScreen.this, MyTrips.class));
                        break;
                    case 2:
                        startActivity(new Intent(HomeScreen.this, Friends.class));
                        break;
                    case 3:
                        startActivity(new Intent(HomeScreen.this, Pictures.class));
                        break;
                    case 4:
                        startActivity(new Intent(HomeScreen.this, SettingsAct.class));
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
