package com.wouter.samy.instatravel;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class HomeScreen extends AppCompatActivity {

    private DrawerLayout nDrawerLayout;
    private ActionBarDrawerToggle nToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        nDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle = new ActionBarDrawerToggle(this, nDrawerLayout, R.string.open, R.string.close);

        nDrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_menu);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case (R.id.nav_account):
                        Intent profileActivity = new Intent(getApplicationContext(), Profile.class);
                        startActivity(profileActivity);
                        break;
                    case (R.id.nav_settings):
                        Intent settingsActivity = new Intent(getApplicationContext(), SettingsAct.class);
                        startActivity(settingsActivity);
                        break;
                    case (R.id.nav_logout):
                        Intent logOutActivity = new Intent(getApplicationContext(), MainActivity.class);
                        logOutActivity.putExtra("logout", true);
                        startActivity(logOutActivity);
                    case (R.id.nav_maps):
                        Intent mapsActivity = new Intent(getApplicationContext(),TravelTest.class);
                        startActivity(mapsActivity);
                }
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(nToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
