package com.example.chris.flexicuv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Medarbejder extends AppCompatActivity {

    //Spinner arbejsområder;
    //Spinner dato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medarbejder);

        //get the spinner from the xml.
        Spinner arbejdsområder = findViewById(R.id.spinner_arbejdsområde);
        //create a list of items for the spinner.
        String[] arbejdsområder_array = new String[]{"Vælg arbejdsområde","Smed", "Tømrer", "Murer", "Lagermand"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arbejdsområder_array);
        //set the spinners adapter to the previously created one.
        arbejdsområder.setAdapter(adapter);

        Spinner dato = findViewById(R.id.spinner_fødselsdato);
        String[] ting = new String[] {"Dato","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        ArrayAdapter<String> adapter_dato = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ting);
        dato.setAdapter(adapter_dato);



    }
}
