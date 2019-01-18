
package com.example.chris.flexicuv2.medarbejdere;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.udlej.Udlejning;


/**
 *@Author Christian
 * Aktivitet til at se alle ens medarbejdere.
 * Medarbejderene vil vises i et popup-vindue hvis de trykkes på.
 *
 */
public class Medarbejdere_skaerm extends AppCompatActivity {
    private FrameLayout medarbejdereFrame;

    Medarbejder_Skaerm_fragment fragment;

    Udlejning ud;


    //Til test færdigt




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medarbejdere_skaerm);

        medarbejdereFrame = findViewById(R.id.medarbejdere_frame);

        fragment = new Medarbejder_Skaerm_fragment();

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
