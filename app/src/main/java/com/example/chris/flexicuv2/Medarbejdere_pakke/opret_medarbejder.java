package com.example.chris.flexicuv2.Medarbejdere_pakke;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.chris.flexicuv2.Indlejninger.MultiSelectionSpinner;
import com.example.chris.flexicuv2.R;

import java.util.ArrayList;
import java.util.List;

public class opret_medarbejder extends AppCompatActivity {

    MultiSelectionSpinner arbejdsområde_spinner;
    Spinner fødselsår_spinner;
    EditText medarbejder_navn;
    EditText medarbejder_email;
    EditText medarbejder_tlf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opret_medarbejder);

        //Edit texts
        medarbejder_navn = (EditText) findViewById(R.id.editText_navn);
        medarbejder_email = (EditText) findViewById(R.id.editText_email);
        medarbejder_tlf = (EditText) findViewById(R.id.editText_tlf);

        //Spinnere
        arbejdsområde_spinner=(MultiSelectionSpinner) findViewById(R.id.arbejdsområde);
        fødselsår_spinner = findViewById(R.id.fødselsår);
        opretSpinnere();








    }

    /**
     * Giver resultaterne fra Spinnerne, og lægger dem i en String og en ArrayList.
     */
    public void getResultater(){
        ArrayList<String> valgteArbejdsområder = new ArrayList<>();
        valgteArbejdsområder.add(arbejdsområde_spinner.getSelectedItemsAsString());

        String valgteFødselsår = new String();
        valgteFødselsår = fødselsår_spinner.getSelectedItem().toString();
    }

    public void opretSpinnere(){
        List<String> arbejdsområde_liste = new ArrayList<String>();
        arbejdsområde_liste.add("Vælg arbejdsområde");
        arbejdsområde_liste.add("Håndværker");
        arbejdsområde_liste.add("Smed");
        arbejdsområde_liste.add("Lagermand");
        arbejdsområde_liste.add("Elektriker");
        arbejdsområde_spinner.setItems(arbejdsområde_liste);

        //Fødselsår spinner
        ArrayList<String> år= new ArrayList<>();
        år.add("Vælg fødselsår");
        for (Integer i=2004; i>1940; i--) {
            år.add(i.toString());
        }
        ArrayAdapter<String> adapter_år = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, år);
        fødselsår_spinner.setAdapter(adapter_år);
    }
}
