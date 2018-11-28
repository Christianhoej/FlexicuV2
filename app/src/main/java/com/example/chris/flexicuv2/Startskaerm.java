package com.example.chris.flexicuv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
<<<<<<< HEAD:app/src/main/java/com/example/chris/flexicuv2/Startskaerm_akt.java
=======

import Indlejninger.Lej;
import Medarbejdere.Medarbejdere;
>>>>>>> 2c3542bfa65595afaf952f0e7a19b3503ab99d2f:app/src/main/java/com/example/chris/flexicuv2/Startskaerm.java

import com.example.chris.flexicuv2.fragments.Startskaerm_Udlejede_medarbejder_fragment;
import com.example.chris.flexicuv2.fragments.Indbakke_fragment;
import com.example.chris.flexicuv2.fragments.Lej_fragment;
import com.example.chris.flexicuv2.fragments.Startskaerm_lejede_Medarbejdere_fragment;
import com.example.chris.flexicuv2.fragments.Udlej_Fragment;

public class Startskaerm_akt extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView startskaermNav;
    private String valg;
    //private FrameLayout startskaermFrame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startskaerm);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("Hjem");
        Startskaerm_Udlejede_medarbejder_fragment fragment = new Startskaerm_Udlejede_medarbejder_fragment();
        Startskaerm_lejede_Medarbejdere_fragment fragmentLej = new Startskaerm_lejede_Medarbejdere_fragment();
        FragmentTransaction fragTrans_hjem = getSupportFragmentManager().beginTransaction();

        fragTrans_hjem.add(R.id.startskaermUdlejFrame, fragment);
        fragTrans_hjem.add(R.id.startskaermLejFrame, fragmentLej);
        fragTrans_hjem.commit();

        /*
        Til at highlighte
        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
         */

        //startskaermFrame = (FrameLayout) findViewById(R.id.startskaermFrame);
        startskaermNav = findViewById(R.id.startskaerm_nav);
        startskaermNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
<<<<<<< HEAD:app/src/main/java/com/example/chris/flexicuv2/Startskaerm_akt.java
                        setTitle("Hjem");
                        Startskaerm_Udlejede_medarbejder_fragment fragmentUdlej = new Startskaerm_Udlejede_medarbejder_fragment();
                        Startskaerm_lejede_Medarbejdere_fragment fragmentLej = new Startskaerm_lejede_Medarbejdere_fragment();
                        FragmentTransaction fragTrans_hjem = getSupportFragmentManager().beginTransaction();
                        fragTrans_hjem.add(R.id.startskaermUdlejFrame, fragmentUdlej);
                        fragTrans_hjem.add(R.id.startskaermLejFrame, fragmentLej);
                        fragTrans_hjem.commit();

                        //openSkaerm(Startskaerm_akt.class);
=======
>>>>>>> 2c3542bfa65595afaf952f0e7a19b3503ab99d2f:app/src/main/java/com/example/chris/flexicuv2/Startskaerm.java
                        return true;
                    case R.id.nav_udlej:
                        setTitle("Udlej");
                        Udlej_Fragment fragment1 = new Udlej_Fragment();
                        FragmentTransaction fragTrans_udlej = getSupportFragmentManager().beginTransaction();
                        fragTrans_udlej.replace(R.id.startskaermUdlejFrame, fragment1);
                        fragTrans_udlej.commit();
                       // openSkaerm(Udlej_akt.class);
                        return true;
                    case R.id.nav_lej:
                        setTitle("Lej");
                        Lej_fragment fragment2 = new Lej_fragment();
                        FragmentTransaction fragTrans_lej = getSupportFragmentManager().beginTransaction();
                        fragTrans_lej.replace(R.id.startskaermUdlejFrame, fragment2);
                        fragTrans_lej.commit();
                       // openSkaerm(Udlej_akt.class);
                        return true;
                    case R.id.nav_indbakke:
                        setTitle("Indbakke");
                        Indbakke_fragment fragment3 = new Indbakke_fragment();
                        FragmentTransaction fragTrans_indbakke = getSupportFragmentManager().beginTransaction();
                        fragTrans_indbakke.replace(R.id.startskaermUdlejFrame, fragment3);
                        fragTrans_indbakke.commit();
                      //  openSkaerm(Indbakke_akt.class);
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

    /*public void openMedarbejdere(){
        Intent intent = new Intent(this, Medarbejdere_akt.class);
        startActivity(intent);
    }*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.medarbejdere) {
            openSkaerm(Medarbejdere_akt.class);
        } else if (id == R.id.søg) {

        } else if (id == R.id.indstillinger) {

        } else if (id == R.id.historik) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
