package com.example.chris.flexicuv2.Medarbejdere_pakke;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.model.Singleton;
import com.example.chris.flexicuv2.udlej.Udlejning;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *@Author Christian
 * Aktivitet til at se alle ens medarbejdere.
 * Medarbejderene vil vises i et popup-vindue hvis de trykkes på.
 *
 */
public class Medarbejdere_skaerm extends AppCompatActivity {
    private FrameLayout medarbejdereFrame;

    opret_medarbejder_fragment_1.Medarbejder_Skaerm_fragment fragment;
    Udlejning ud;


    //Til test færdigt




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medarbejdere_skaerm);

        medarbejdereFrame = findViewById(R.id.medarbejdere_frame);
        fragment = new opret_medarbejder_fragment_1.Medarbejder_Skaerm_fragment();
        ud = new Udlejning();
        //setFragment(fragment);
        setFragment(ud);
    }

    public void setFragment(android.support.v4.app.Fragment fragment) {
        //medarbejdereFrame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.medarbejdere_frame, fragment);
        //fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }





}
