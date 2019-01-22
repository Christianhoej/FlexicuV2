package com.example.chris.flexicuv2.startskærm.lej;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.hjælpeklasser.Afstandsberegner;
import com.example.chris.flexicuv2.hjælpeklasser.MultiSelectionSpinner;
import com.example.chris.flexicuv2.model.Singleton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * @Author Christian
 * A simple {@link Fragment} subclass.
 */
public class Lej_filtrer_fragment extends Fragment implements View.OnClickListener, Lej_filtrer_Presenter.UpdateFiltrer {

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
    private Lej_filtrer_Presenter presenter;

    private DatePickerDialog.OnDateSetListener datepickerListener;
    private TextView startdatoET, slutdatoET;

    private Afstandsberegner afstandsberegner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lej_filtrer_fragment, container, false);

        singleton = Singleton.getInstance();
        presenter = new Lej_filtrer_Presenter(this);

        arbejdsområder_spinner = (MultiSelectionSpinner)v.findViewById(R.id.filtrer_arbejdsområder);

        postnr = (EditText) v.findViewById(R.id.edit_postnr);
        postnr.addTextChangedListener(adresseTextWatch);

        by = (EditText) v.findViewById(R.id.edit_by);
        by.addTextChangedListener(adresseTextWatch);

        vej = (EditText) v.findViewById(R.id.edit_vej);
        vej.addTextChangedListener(adresseTextWatch);

        nummer = (EditText) v.findViewById(R.id.edit_nummer);
        nummer.addTextChangedListener(adresseTextWatch);



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
        startdatoET.addTextChangedListener(datoTextWatch);
        slutdatoET = v.findViewById(R.id.lej_filtrer_slutdato_textview);
        slutdatoET.setOnClickListener(this);
        slutdatoET.addTextChangedListener(datoTextWatch);

        c = Calendar.getInstance();

        afstandsberegner = new Afstandsberegner();
        //a= afstandsberegner.calculateDistanceInKilometer(55.779292, 12.521402,55.753635  ,12.452214);

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
                //navigation skal måske
                getActivity().onBackPressed();
                //TODO check
                break;
            case R.id.annuller_knap:
                singleton.søgeFiltrering = new Filter();
                System.out.println(singleton.søgeFiltrering.getStartdato() + " Dette er startdato");
                getActivity().onBackPressed();
                break;
            case R.id.nulstil_knap:
                singleton.søgeFiltrering = new Filter();
                getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.startskærm_frame_til_diverse, new Lej_filtrer_fragment())
                    .commit();

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
        datepickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String måned = ""+month;
                if(month < 10){

                    måned = "0" + month;
                }
                String dag = ""+dayOfMonth;
                if(dayOfMonth < 10){

                    dag  = "0" + dayOfMonth ;
                }
                String s = " " + dayOfMonth + " / " + måned + " / " + year + " ";
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


    private TextWatcher datoTextWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            slutdatoET.setError(null);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!slutdatoET.getText().toString().equals(" dd / mm / yyyy ")){
            if(!startdatoET.getText().toString().equals(" dd / mm / yyyy "))
        presenter.checkDatoerErOK(startdatoET.getText().toString(),slutdatoET.getText().toString());
        }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher adresseTextWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //hvis der er noget i alle felterne
            String s1 = vej.getText().toString();
            String s2  = nummer.getText().toString();
            String s3 = postnr.getText().toString();
            String s4 = by.getText().toString();
            if(s1.length()>0 && s2.length()>0 && s3.length()>3 && s4.length()>0)
                postnr.setError(null);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


        }

        @Override
        public void afterTextChanged(Editable s) {
            String s1 = vej.getText().toString();
            String s2  = nummer.getText().toString();
            String s3 = postnr.getText().toString();
            String s4 = by.getText().toString();
            if(s1.length()>0 && s2.length()>0 && s3.length()>3 && s4.length()>0){
                presenter.checkAdresse(getContext(),s1,s2,s3,s4);
            }
        }
    };


    @Override
    public void errorKronologiskDato(String errorMSG) {
        slutdatoET.setError(errorMSG);
    }

    @Override
    public void errorIngenArbejdsDage(String errorMSG) {
        slutdatoET.setError(errorMSG);
    }

    @Override
    public void errorAdresse(String errorMSG) {
        postnr.setError(errorMSG);
    }

    @Override
    public void sendToast(String msg) {
        Toast.makeText(getContext(), msg,
                Toast.LENGTH_LONG).show();
    }
}
