package com.example.chris.flexicuv2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Udlej_akt extends AppCompatActivity {

    private BottomNavigationView startskaermNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udlej);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startskaermNav = findViewById(R.id.startskaerm_nav);
        startskaermNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        openSkaerm(Startskaerm_akt.class);
                        return true;
                    case R.id.nav_udlej:
                        openSkaerm(Udlej_akt.class);
                        return true;
                    case R.id.nav_lej:
                        openSkaerm(Lej_akt.class);
                        return true;
                    case R.id.nav_indbakke:
                        openSkaerm(Indbakke_akt.class);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public void openSkaerm(Class a){
        Intent intent = new Intent(this,a);
        startActivity(intent);
    }


}
