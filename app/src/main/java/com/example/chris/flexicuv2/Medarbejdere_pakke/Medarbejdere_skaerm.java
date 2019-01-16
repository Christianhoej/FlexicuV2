package com.example.chris.flexicuv2.Medarbejdere_pakke;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;

import java.util.ArrayList;

/**
 *@Author Christian
 * Aktivitet til at se alle ens medarbejdere.
 * Medarbejderene vil vises i et popup-vindue hvis de trykkes på.
 *
 */
public class Medarbejdere_skaerm extends AppCompatActivity {
    private FrameLayout medarbejdereFrame;
    Medarbejder_Skaerm_fragment fragment;


    //Til test færdigt




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medarbejdere_skaerm);
        medarbejdereFrame = findViewById(R.id.medarbejdere_frame);
        fragment = new Medarbejder_Skaerm_fragment();
        setFragment(fragment);
    }




    /**
     * setFragment metode
     * @param fragment
     */
    public void setFragment(android.support.v4.app.Fragment fragment) {
        //medarbejdereFrame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.medarbejdere_frame, fragment);
        //fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }
}
