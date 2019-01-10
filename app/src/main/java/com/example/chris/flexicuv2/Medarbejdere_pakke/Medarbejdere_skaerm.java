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

public class Medarbejdere_skaerm extends AppCompatActivity implements View.OnClickListener {

    LinearLayout søren;
    Button opretMedarbejdereKnap;

    private static final String TAG = "Medarbejdere_skaerm";
    private ArrayList<String> mMedarbejderNavne = new ArrayList<>();
    //private ArrayList<String> mMedarbejderBilleder = new ArrayList<>();
    private ArrayList<String> getmMedarbejderArbejdsområder = new ArrayList<>();


    //Test
    TextView tiltest;
    EditText medarbejder_navn;
    String navnet;
    FrameLayout medarbejdereFrame;
    private opret_medarbejder_fragment_1 opretMedarbejderFragment1;

    Button vispopup;
    vis_medarbejder_fragment vis_medarbejder_fragment;

    View tilPopUp;

    //Til en test
    ArrayList<String > navneTest = new ArrayList<>();
    ArrayList<String > arbejdsområderTest = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medarbejdere_skaerm);

        Log.d(TAG, "onCreate: started");

        tilPopUp = (View) findViewById(R.id.tilPopUp);

        //Til test
        navneTest.add("Carl");
        navneTest.add("Mig");
        arbejdsområderTest.add("Smed");
        arbejdsområderTest.add("Håndværker");


        //LinearLayout søren = (LinearLayout) findViewById(R.id.søren_layout);
        //søren.setOnClickListener(this);

        opretMedarbejdereKnap = (Button) findViewById(R.id.opret_medarbejder_knap);
        opretMedarbejdereKnap.setOnClickListener(this);

        vispopup = (Button) findViewById(R.id.vispopup);
        vispopup.setOnClickListener(this);

        vis_medarbejder_fragment = new vis_medarbejder_fragment();

        //Test af recyclerview
        opretMedarbejderFragment1 = new opret_medarbejder_fragment_1();
        medarbejdereFrame = (FrameLayout) findViewById(R.id.medarbejdere_frame);
        fyldRecyclerView();
    }

    /**
     * Metode til at "fylde" recyclerViewet, med den dta der lægges i Arraylisterne
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
        //tilPopUp = (View) findViewById(R.id.tilPopUp);
        switch (v.getId()) {
            case R.id.opret_medarbejder_knap:
            //openMedarbejder();
                setFragment(opretMedarbejderFragment1);
                break;

            case R.id.vispopup:
                onButtonShowPopupWindowClick(v);
                System.out.println("hejehjrwheæ");
                break;
        }
    }

    private void openMedarbejder() {
        Intent intent = new Intent(this, opret_medarbejder.class);
        startActivity(intent);
    }

    public void setFragment(android.support.v4.app.Fragment fragment) {
        medarbejdereFrame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.medarbejdere_frame, fragment);
        //fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }



    public void onButtonShowPopupWindowClick(View view) {
        //tilPopUp = (View) findViewById(R.id.tilPopUp);
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.vis_medarbejder_popup_fragment, null);

        // create the popup window
        int width = 1000;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        /*// dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });*/
    }


}
