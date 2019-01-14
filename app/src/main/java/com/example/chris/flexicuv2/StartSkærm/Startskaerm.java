package com.example.chris.flexicuv2.StartSkærm;

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
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.example.chris.flexicuv2.DBManager;
import com.example.chris.flexicuv2.Medarbejdere_pakke.Medarbejdere_skaerm;
import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.fragments.Hjem_fragment;
import com.example.chris.flexicuv2.fragments.Lej_fragment;
import com.example.chris.flexicuv2.fragments.Startskaerm_Udlejede_medarbejder_fragment;
import com.example.chris.flexicuv2.fragments.Indbakke_fragment;
import com.example.chris.flexicuv2.fragments.Startskaerm_alle_medarbejdere_fragment;
import com.example.chris.flexicuv2.fragments.Startskaerm_lejede_Medarbejdere_fragment;
import com.example.chris.flexicuv2.fragments.Udlej_Fragment;
import com.example.chris.flexicuv2.model.Singleton;
import com.google.firebase.auth.FirebaseAuth;

public class Startskaerm extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, StartSkærmPresenter.UpdateStartskærm {

    private BottomNavigationView startskaermNav;

    private FrameLayout startskaermFrame;
    private Udlej_Fragment fragmentUdlej;
    private Lej_fragment fragmentLej;
    private Indbakke_fragment fragmentIndbakke;
    private Hjem_fragment fragmentHjem;

    private StartSkærmPresenter presenter;
    private FirebaseAuth mAuth;

    private Startskaerm_Udlejede_medarbejder_fragment fragmentUdlejedeMed;
    private Startskaerm_lejede_Medarbejdere_fragment fragmentLejedeMed;
    private Startskaerm_alle_medarbejdere_fragment fragmentAlleMed;
    private DBManager dbManager;
    private Singleton singleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startskaerm);
        presenter = new StartSkærmPresenter(this);
        //dbManager = new DBManager();
        //dbManager.readBruger();
        singleton = Singleton.getInstance();
        /**
         * Til at køre mellem fragments
         */

        startskaermFrame = (FrameLayout) findViewById(R.id.startskaerm_frame);
        fragmentUdlej = new Udlej_Fragment();
        fragmentLej = new Lej_fragment();
        fragmentIndbakke = new Indbakke_fragment();
        fragmentHjem = new Hjem_fragment();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        TextView drawer_top = findViewById(R.id.left_menu_title);
        //drawer_top.setText(singleton.getBruger().getVirksomhedsnavn());

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("Virksomhedens navn");

        fjernFragmenter();
        setFragment(fragmentHjem, "hjem");

        startskaermNav = findViewById(R.id.startskaerm_nav);
        startskaermNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        //fragmentHjem = (Hjem_fragment) getSupportFragmentManager().findFragmentByTag("hjem");
                        if(!(getSupportFragmentManager().findFragmentByTag("hjem") != null && getSupportFragmentManager().findFragmentByTag("hjem").isVisible())) {
                            fjernFragmenter();
                            setFragment(fragmentHjem, "hjem");
                        }
                        return true;
                    case R.id.nav_udlej:
                       // fragmentUdlej = (Udlej_Fragment) getSupportFragmentManager().findFragmentByTag("udlej");
                        if(!(getSupportFragmentManager().findFragmentByTag("udlej") != null && getSupportFragmentManager().findFragmentByTag("udlej").isVisible())) {
                            fjernFragmenter();
                            setFragment(fragmentUdlej, "udlej");
                        }
                        return true;
                    case R.id.nav_lej:
                        //fragmentLej = (Lej_fragment) getSupportFragmentManager().findFragmentByTag("lej");
                        if(!(getSupportFragmentManager().findFragmentByTag("lej") != null && getSupportFragmentManager().findFragmentByTag("lej").isVisible())) {
                            fjernFragmenter();
                            setFragment(fragmentLej, "lej");
                        }
                        return true;
                    case R.id.nav_indbakke:
                        //fragmentIndbakke = (Indbakke_fragment) getSupportFragmentManager().findFragmentByTag("indbakke");
                        if(!(getSupportFragmentManager().findFragmentByTag("indbakke") != null && getSupportFragmentManager().findFragmentByTag("indbakke").isVisible())) {
                            fjernFragmenter();
                            setFragment(fragmentIndbakke, "indbakke");
                        }
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setFragment(Fragment fragment, String tag) {
        startskaermFrame.removeAllViews();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.startskaerm_frame, fragment, tag);
        fragmentTransaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.medarbejdere) {
            openSkaerm(Medarbejdere_skaerm.class);
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

    public void fjernFragmenter(){
        for(Fragment fragment : getSupportFragmentManager().getFragments()){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    @Override
    public void updateRecyclerView() {
        //TODO hvordan recyclerview1 skal opdateres
    }

    @Override
    public void updateRecyclerView2() {
        //TODO hvordan recyclerview 2 skal opdateres
    }

    @Override
    public void updateRecyclerView3() {
        //TODO hvordan recyclerview 3 skal opdateres
    }
}
