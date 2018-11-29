package com.example.chris.flexicuv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
/*
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
*/
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.example.chris.flexicuv2.fragments.Hjem_fragment;
import com.example.chris.flexicuv2.fragments.Lej_fragment;
import com.example.chris.flexicuv2.fragments.Startskaerm_Udlejede_medarbejder_fragment;
import com.example.chris.flexicuv2.fragments.Indbakke_fragment;
import com.example.chris.flexicuv2.fragments.Startskaerm_alle_medarbejdere_fragment;
import com.example.chris.flexicuv2.fragments.Startskaerm_lejede_Medarbejdere_fragment;
import com.example.chris.flexicuv2.fragments.Udlej_Fragment;

public class Startskaerm extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView startskaermNav;
    private String valg;
    //private FrameLayout startskaermFrame;
    /*

    FragmentManager fragMan;
    FragmentTransaction fragTrans_hjem;*/

    private FrameLayout startskaermFrame;
    Udlej_Fragment fragmentUdlej;
    Lej_fragment fragmentLej;
    Indbakke_fragment fragmentIndbakke;
    Hjem_fragment fragmentHjem;


    Startskaerm_Udlejede_medarbejder_fragment fragmentUdlejedeMed;
    Startskaerm_lejede_Medarbejdere_fragment fragmentLejedeMed;
    Startskaerm_alle_medarbejdere_fragment fragmentAlleMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startskaerm);

        /**
         * Til at køre mellem fragments
         */
        startskaermFrame = (FrameLayout) findViewById(R.id.startskaerm_frame);
        fragmentUdlej = new Udlej_Fragment();
        fragmentLej = new Lej_fragment();
        fragmentIndbakke = new Indbakke_fragment();
        fragmentHjem = new Hjem_fragment();

        /*fragmentUdlejedeMed = new Startskaerm_Udlejede_medarbejder_fragment();
        fragmentLejedeMed = new Startskaerm_lejede_Medarbejdere_fragment();
        fragmentAlleMed = new Startskaerm_alle_medarbejdere_fragment();*/



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("Hjem");

        fjernFragmenter();
        setFragment(fragmentHjem);

//        opretHjemFragmenter();
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
                        /*setTitle("Hjem");
                        fjernFragmenter();
                        opretHjemFragmenter();

                        //openSkaerm(Startskaerm_akt.class);*/
                        fjernFragmenter();
                        setFragment(fragmentHjem);

                        return true;
                    case R.id.nav_udlej:
                        /*setTitle("Udlej");
                        fjernFragmenter();
                        Udlej_Fragment fragment1 = new Udlej_Fragment();
                        FragmentTransaction fragTrans_udlej = getSupportFragmentManager().beginTransaction();
                        fragTrans_udlej.replace(R.id.startskaermUdlejFrame, fragment1);
                        fragTrans_udlej.commit();
                       // openSkaerm(Udlej_akt.class);*/
                        fjernFragmenter();
                        setFragment(fragmentUdlej);
                        return true;
                    case R.id.nav_lej:
                        /*setTitle("Lej");
                        Lej_fragment fragment2 = new Lej_fragment();
                        FragmentTransaction fragTrans_lej = getSupportFragmentManager().beginTransaction();
                        fragTrans_lej.replace(R.id.startskaermUdlejFrame, fragment2);
                        fragTrans_lej.commit();
                        openSkaerm(Udlej_akt.class);*/
                        fjernFragmenter();
                        setFragment(fragmentLej);
                        return true;
                    case R.id.nav_indbakke:
                        /*setTitle("Indbakke");
                        Indbakke_fragment fragment3 = new Indbakke_fragment();
                        FragmentTransaction fragTrans_indbakke = getSupportFragmentManager().beginTransaction();
                        fragTrans_indbakke.replace(R.id.startskaermUdlejFrame, fragment3);
                        fragTrans_indbakke.commit();
                      //  openSkaerm(Indbakke_akt.class);*/
                        fjernFragmenter();
                        setFragment(fragmentIndbakke);
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

    public void setFragment(Fragment fragment) {
        startskaermFrame.removeAllViews();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.startskaerm_frame, fragment);
        fragmentTransaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.medarbejdere) {
            openSkaerm(Medarbejdere.class);
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
/*
    public void opretHjemFragmenter(){
        fragmentUdlejedeMed = new Startskaerm_Udlejede_medarbejder_fragment();
        fragmentLejedeMed = new Startskaerm_lejede_Medarbejdere_fragment();
        fragmentAlleMed = new Startskaerm_alle_medarbejdere_fragment();
        FragmentManager fragMan = getSupportFragmentManager();
        FragmentTransaction fragTrans_hjem = fragMan.beginTransaction();

        fragTrans_hjem.add(R.id.startskaermUdlejFrame, fragmentUdlejedeMed);
        fragTrans_hjem.add(R.id.startskaermLejFrame, fragmentLejedeMed);
        fragTrans_hjem.add(R.id.startskaermAlleFrame, fragmentAlleMed);
        fragTrans_hjem.commit();
    }
*/
    public void fjernFragmenter(){

        for(Fragment fragment : getSupportFragmentManager().getFragments()){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }
}
