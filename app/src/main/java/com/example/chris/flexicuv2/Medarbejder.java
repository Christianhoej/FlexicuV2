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
        String[] datoer = new String[] {"Dato","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        ArrayAdapter<String> adapter_dato = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, datoer);
        dato.setAdapter(adapter_dato);

        Spinner måned = findViewById(R.id.spinner_måned);
        String[] måneder = new String[] {"Måned","Januar","Februar", "Marts", "April", "Maj", "Juni", "Juli", "August", "September", "Oktober", "November", "December"};
        ArrayAdapter<String> adapter_måned = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, måneder);
        måned.setAdapter(adapter_måned);

        Spinner år = findViewById(R.id.spinner_år);
        String[] år_A = new String[] {"År","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000"};
        ArrayAdapter<String> adapter_år = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, år_A);
        år.setAdapter(adapter_år);
    }
}
