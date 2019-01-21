package com.example.chris.flexicuv2.startskærm.lej;


import android.app.DatePickerDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.hjælpeklasser.MultiSelectionSpinner;
import com.example.chris.flexicuv2.model.Singleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * @Author Christian
 * A simple {@link Fragment} subclass.
 */
public class Lej_filtrer_fragment extends Fragment implements View.OnClickListener {

    ArrayList<String> valgte_arbejdsområder = new ArrayList<>();
    //String[] arbejdsområder = {"Lagermand"};
    public Lej_filtrer_fragment() {
        // Required empty public constructor
    }

    private EditText postnr;
    private EditText by;
    private EditText vej;
    private EditText nummer;
    private RadioButton ja_værktøj;
    private RadioButton nej_værktøj;
    private EditText min_timepris;
    private EditText max_timepris;
    private MultiSelectionSpinner arbejdsområder_spinner;
    private Button anvend;
    private Button annuller;
    private Button nulstil;
    private Calendar c;
    private Singleton singleton;

    private DatePickerDialog.OnDateSetListener datepickerListener;
    private TextView startdatoET, slutdatoET;

    private Afstand_koordinater afstand_koordinater;
    double a;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lej_filtrer_fragment, container, false);

        singleton = Singleton.getInstance();

        arbejdsområder_spinner = (MultiSelectionSpinner)v.findViewById(R.id.filtrer_arbejdsområder);
        postnr = (EditText) v.findViewById(R.id.edit_postnr);
        by = (EditText) v.findViewById(R.id.edit_by);
        vej = (EditText) v.findViewById(R.id.edit_vej);
        nummer = (EditText) v.findViewById(R.id.edit_nummer);
        ja_værktøj = (RadioButton) v.findViewById(R.id.ja_værktøj);
        nej_værktøj = (RadioButton) v.findViewById(R.id.nej_værktøj);
        min_timepris = (EditText)v.findViewById(R.id.min_timepris);
        max_timepris = (EditText) v.findViewById(R.id.max_timepris);
        anvend = v.findViewById(R.id.anvend_knap);
        anvend.setOnClickListener(this);
        annuller = v.findViewById(R.id.annuller_knap);
        annuller.setOnClickListener(this);
        nulstil = v.findViewById(R.id.nulstil_knap);
        nulstil.setOnClickListener(this);

        startdatoET = v.findViewById(R.id.lej_filtrer_startdato_textview);
        startdatoET.setOnClickListener(this);
        slutdatoET = v.findViewById(R.id.lej_filtrer_slutdato_textview);
        slutdatoET.setOnClickListener(this);

        c = Calendar.getInstance();

        afstand_koordinater = new Afstand_koordinater();
        a= afstand_koordinater.calculateDistanceInKilometer(55.779292, 12.521402,55.753635  ,12.452214);
        System.out.println("ÆÆÆÆÆÆÆÆÆ " + a);

        opretSpinner();
        return v;
    }

    public void opretSpinner(){
        List<String> arbejdsområde_listen = new ArrayList<String>();
        arbejdsområde_listen.add("Vælg arbejdsområde");
        arbejdsområde_listen.add("Arbejdsmand");
        arbejdsområde_listen.add("Butiksekspedition");
        arbejdsområde_listen.add("Chauffør - over 3.5 ton");
        arbejdsområde_listen.add("Chauffør - under 3.5 ton");
        arbejdsområde_listen.add("Ejendomsservice");
        arbejdsområde_listen.add("Elektriker");
        arbejdsområde_listen.add("Film og TV");
        arbejdsområde_listen.add("Flyttemand");
        arbejdsområde_listen.add("HR");
        arbejdsområde_listen.add("IT");
        arbejdsområde_listen.add("Jura");
        arbejdsområde_listen.add("Kantinearbejde");
        arbejdsområde_listen.add("Kontor- og sekretærarbejde");
        arbejdsområde_listen.add("Lager");
        arbejdsområde_listen.add("Maler");
        arbejdsområde_listen.add("Maskinfører");
        arbejdsområde_listen.add("Murer");
        arbejdsområde_listen.add("Online marketing");
        arbejdsområde_listen.add("Rengøring");
        arbejdsområde_listen.add("Smed");
        arbejdsområde_listen.add("Svejsning");
        arbejdsområde_listen.add("Telemarketing");
        arbejdsområde_listen.add("Tjener");
        arbejdsområde_listen.add("Tolk");
        arbejdsområde_listen.add("Tømrer");
        arbejdsområde_listen.add("VVS");
        arbejdsområde_listen.add("Økonomi");
        arbejdsområder_spinner.setItems(arbejdsområde_listen);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.anvend_knap:
                //TODO Hvad skal knapperne gøre
                break;
            case R.id.annuller_knap:
                getActivity().onBackPressed();
                break;
            case R.id.nulstil_knap:
                //TODO Nulstil knap opsættes
                break;
            case R.id.lej_filtrer_slutdato_textview:
                findEnDato(false);
                break;
            case R.id.lej_filtrer_startdato_textview:
                findEnDato(true);
                break;
        }

    }

    /**
     * metoden starter en datepicker i dialog boks hvor der kan vælges datoer fra
     * @param start : tilkendegiver om det er start eller slutdatoen -> hvis det er startdatoen så er start true
     */
    private void findEnDato(final boolean start) {
        //final Calendar c = Calendar.getInstance();
        datepickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String s = " " + dayOfMonth + " / " + month + " / " + year + " ";
                if(start) {
                    startdatoET.setText(s);
                    c.set(year,month-1,dayOfMonth);
                    slutdatoET.setEnabled(true);
                }
                else {
                    slutdatoET.setText(s);
                }
            }
        };


        Calendar calendar = Calendar.getInstance();
        final int år = calendar.get(Calendar.YEAR);
        final int måned = calendar.get(Calendar.MONTH);
        final int dag = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datepickerdialog = new DatePickerDialog(getContext(),datepickerListener,år,måned,dag );

        if(c.getTimeInMillis()>(System.currentTimeMillis()-1000)){
            datepickerdialog.getDatePicker().setMinDate(c.getTimeInMillis());
        }
        else{
            datepickerdialog.getDatePicker().setMinDate(System.currentTimeMillis());
        }

        datepickerdialog.show();

    }

    public void goelocate(View v) throws IOException {
        String vejnavn = vej.getText().toString();
        String nummer = this.nummer.getText().toString();
        String postnr = this.postnr.getText().toString();

        String fuldAdresse = vejnavn + " " + nummer + " " + postnr;

        Geocoder gc = new Geocoder(getContext());
        List<Address> list = gc.getFromLocationName(fuldAdresse,1);
        Address address = list.get(0);
        String lokation = address.getLocality();


        singleton.søgeFiltrering.setLatitude(Double.toString(address.getLatitude()));
        singleton.søgeFiltrering.setLongitude(Double.toString(address.getLongitude()));


    }



}
