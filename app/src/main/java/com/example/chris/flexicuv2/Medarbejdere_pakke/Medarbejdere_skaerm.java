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
public class Medarbejdere_skaerm extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Medarbejdere_skaerm";

    private Button opretMedarbejdereKnap;

    private FrameLayout medarbejdereFrame;
    private opret_medarbejder_fragment_1 opretMedarbejderFragment1;
    private RecyclerView recyclerView;

    //Til en test
    private ArrayList<String > navneTest = new ArrayList<>();
    private ArrayList<String > arbejdsområderTest = new ArrayList<>();
    //Til test færdigt




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medarbejdere_skaerm);
        Log.d(TAG, "onCreate: started");

        recyclerView = (RecyclerView) findViewById(R.id.medarbejder_recyclerview);
        recyclerView.setOnClickListener(this);

        //Til test
        navneTest.add("Carl");
        navneTest.add("Mig");
        arbejdsområderTest.add("Smed");
        arbejdsområderTest.add("Håndværker");
        //Til test færdigt

        opretMedarbejdereKnap = (Button) findViewById(R.id.opret_medarbejder_knap);
        opretMedarbejdereKnap.setOnClickListener(this);

        //Test af recyclerview
        opretMedarbejderFragment1 = new opret_medarbejder_fragment_1();
        medarbejdereFrame = (FrameLayout) findViewById(R.id.medarbejdere_frame);
        fyldRecyclerView();
    }

    /**
     * Metode til at "fylde" recyclerViewet, med den dta der lægges i Test Arraylisterne
     * Denne data skal ændres fra testen til når databasen er opsat.
     */
    private void fyldRecyclerView(){
        Log.d(TAG, "fyldRecyclerView: Fylder Recyclerview");
        RecyclerView recyclerView = findViewById(R.id.medarbejder_recyclerview);
        medarbejder_recyclerView_adapter adapter = new medarbejder_recyclerView_adapter(this, navneTest, arbejdsområderTest);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.opret_medarbejder_knap:
                setFragment(opretMedarbejderFragment1);
                break;
        }
    }

    /**
     * setFragment metode
     * @param fragment
     */
    public void setFragment(android.support.v4.app.Fragment fragment) {
        medarbejdereFrame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.medarbejdere_frame, fragment);
        //fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }
}
