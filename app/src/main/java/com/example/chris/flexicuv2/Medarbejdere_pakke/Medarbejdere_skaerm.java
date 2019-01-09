package com.example.chris.flexicuv2.Medarbejdere_pakke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;

public class Medarbejdere_skaerm extends AppCompatActivity implements View.OnClickListener {

    LinearLayout søren;
    Button opretMedarbejdereKnap;


    //Test
    TextView tiltest;
    EditText medarbejder_navn;
    String navnet;
    FrameLayout medarbejdereFrame;
    private opret_medarbejder_fragment_1 opretMedarbejderFragment1;

    Button vispopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medarbejdere_skaerm);

        LinearLayout søren = (LinearLayout) findViewById(R.id.søren_layout);
        søren.setOnClickListener(this);

        opretMedarbejdereKnap = (Button) findViewById(R.id.opret_medarbejder_knap);
        opretMedarbejdereKnap.setOnClickListener(this);

        vispopup = (Button) findViewById(R.id.vispopup);
        vispopup.setOnClickListener(this);

        //Test

        opretMedarbejderFragment1 = new opret_medarbejder_fragment_1();
        medarbejdereFrame = (FrameLayout) findViewById(R.id.medarbejdere_frame);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.opret_medarbejder_knap:
            //openMedarbejder();
                setFragment(opretMedarbejderFragment1);

            case R.id.vispopup:
                onButtonShowPopupWindowClick(v);
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
        fragmentTransaction.commit();
    }

    public void onButtonShowPopupWindowClick(View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.vis_medarbejder_fragment, null);

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
