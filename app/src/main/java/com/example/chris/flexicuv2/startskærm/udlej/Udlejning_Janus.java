package com.example.chris.flexicuv2.startskærm.udlej;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.database.DBManager;
import com.example.chris.flexicuv2.model.Aftale;
import com.example.chris.flexicuv2.model.Medarbejder;
import com.example.chris.flexicuv2.model.Singleton;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class Udlejning_Janus extends Fragment implements Udlejning_Presenter.UpdateUdlejning, View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    private Spinner medarbejderSpinner;
    private EditText timeprisET, kommentarET;
    private TextView antalArbejdsdage_TV, subtotalen_TV, flexicugebyr_TV, totalprisen_TV, startdatoET, slutdatoET, overskrift_TV;
    private Switch egetVærktøj_switch;
    private Button anullerButton, opretUdlejningButton;
    private ArrayAdapter<String> adapter_medarbejderbeskrivelse;
    private Calendar c;
    private Singleton singleton;
    private DBManager dbManager;
    private Medarbejder medarbejderValgt;



    private DatePickerDialog.OnDateSetListener datepickerListener;

    private Udlejning_Presenter presenter;
    public Udlejning_Janus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.udlej_fragment, container, false);
        singleton = Singleton.getInstance();
        dbManager = new DBManager();
        overskrift_TV = v.findViewById(R.id.aftaleudlejning_overskrift);
        overskrift_TV.setText("Giv mig en overskrift");
        TextView udlejer = v.findViewById(R.id.udlejer_navn_textview);
        udlejer.setText(singleton.getBruger().getVirksomhedsnavn());
        medarbejderSpinner = v.findViewById(R.id.udlejning_medarbejder_spinner);
        opretSpinner(medarbejderSpinner);
        medarbejderSpinner.setOnItemSelectedListener(this);
        c = Calendar.getInstance();
        startdatoET = v.findViewById(R.id.udlejning_startdato_textview1);
        startdatoET.setOnClickListener(this);
        slutdatoET = v.findViewById(R.id.udlejning_slutdato_textview);
        slutdatoET.setOnClickListener(this);

        slutdatoET.setEnabled(false);
        
        timeprisET = v.findViewById(R.id.udlejning_timepris_textview1);
        timeprisET.addTextChangedListener(prisTextWatch);
        kommentarET = v.findViewById(R.id.udlejning_kommentar_edittext);

        kommentarET.setScroller(new Scroller(getActivity()));
        kommentarET.setMaxLines(3);
        kommentarET.setVerticalScrollBarEnabled(true);
        kommentarET.setMovementMethod(new ScrollingMovementMethod());

        antalArbejdsdage_TV = v.findViewById(R.id.udlejning_redigerbar_antal_arbejdsdage_textview1);
        subtotalen_TV = v.findViewById(R.id.udlejning_subtotalen_excl_flexicuspris_textview1);
        flexicugebyr_TV = v.findViewById(R.id.udlejning_flexicu_pris_textview);
        totalprisen_TV = v.findViewById(R.id.udlejning_total_pris_textview1);
        egetVærktøj_switch = v.findViewById(R.id.udlejning_egetværktøj_switch1);
        egetVærktøj_switch.setOnCheckedChangeListener(this);
        anullerButton = v.findViewById(R.id.udlejning_annuller_button);
        anullerButton.setOnClickListener(this);
        opretUdlejningButton = v.findViewById(R.id.udlejning_udlej_button);
        opretUdlejningButton.setOnClickListener(this);
        presenter = new Udlejning_Presenter(this);


        return v;
    }
    public void opretSpinner(View v){
        ArrayList<Medarbejder> temp = Singleton.getMedarbejdere();
        ArrayList<Medarbejder> medarbejdere = new ArrayList<>();
        Medarbejder spinneroverskrift = new Medarbejder();
        spinneroverskrift.setNavn("Vælg medarbejder");
        spinneroverskrift.setFødselsår(Calendar.getInstance().get(Calendar.YEAR));
        medarbejdere.add(spinneroverskrift);
        for(Medarbejder m : temp){
            medarbejdere.add(m);
        }
        adapter_medarbejderbeskrivelse = new Spinner_adapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, medarbejdere);
        medarbejderSpinner.setAdapter(adapter_medarbejderbeskrivelse);
        //medarbejderSpinner.setSelection((ArrayAdapter)medarbejderSpinner.getAdapter().getP.(singleton.midlertidigMedarbejder.toString()));
        System.out.println(singleton.midlertidigMedarbejder.toString());
        medarbejderSpinner.setSelection(medarbejdere.indexOf(singleton.midlertidigMedarbejder)/*-1*/);
    }


    @Override
    public void opdaterSubtotal(double værdi) {
        timeprisET.setError(null);
        subtotalen_TV.setText(""+værdi);
    }

    @Override
    public void opdaterFlexicufee(double værdi) {
        flexicugebyr_TV.setText(""+værdi);

    }

    @Override
    public void opdaterTotal(double værdi) {
        totalprisen_TV.setText(""+værdi);

    }

    @Override
    public void opdaterAtalArbejdsdage(int dage) {
        antalArbejdsdage_TV.setText(""+dage);
    }

    @Override
    public void errorMedarbejder(String errorMSG) {
        ((TextView)medarbejderSpinner.getSelectedView()).setError(errorMSG);
    }

    @Override
    public void errorStartdato(String errorMSG) {
        startdatoET.setError(errorMSG);
    }

    @Override
    public void errorSlutdato(String errorMSG) {
    slutdatoET.setError(errorMSG);
    }

    @Override
    public void errorTimepris(String errorMSG) {
    timeprisET.setError(errorMSG);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.udlejning_udlej_button:
                //TODO henter man en string eller et dato-format for start og slutdato?
                //TODO fyld medarbejderSpinner med objekterne
                int pris = 0;
                if(!timeprisET.getText().toString().equals("")){
                    pris = Integer.parseInt(timeprisET.getText().toString());
                }
                if (presenter.checkKorrektUdfyldtInformation(
                        startdatoET.getText().toString(),
                        slutdatoET.getText().toString(),
                        antalArbejdsdage_TV.getInputType(),
                        pris,
                        egetVærktøj_switch.getText().toString(),
                        kommentarET.getText().toString())
                        ) {
                    Aftale ledig = new Aftale();
                    ledig.setKommentar(kommentarET.getText().toString());
                    ledig.setStartDato(startdatoET.getText().toString());
                    ledig.setEndDato(slutdatoET.getText().toString());
                    ledig.setPris(timeprisET.getText().toString());
                    ledig.setEgetVærktøj(egetVærktøj_switch.getShowText());
                    ledig.setMedarbejder(singleton.midlertidigMedarbejder);
                    ledig.setUdlejer(singleton.getBruger());
                    singleton.addLedigeMedarbejder(ledig);
                    dbManager.createUdlej(ledig);
                }

                break;
            case R.id.udlejning_annuller_button:
                getActivity().getSupportFragmentManager().popBackStack();
                break;

            case R.id.udlejning_slutdato_textview:
                findEnDato(false);
                break;
            case R.id.udlejning_startdato_textview1:
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

    private TextWatcher prisTextWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length()>0){
                presenter.udregnPriser(Integer.parseInt(s.toString()), antalArbejdsdage_TV.getInputType());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
            egetVærktøj_switch.setText("Ja");
        else
            egetVærktøj_switch.setText("Nej");

    }
        //Metoder til valgt spinner listener
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        singleton.midlertidigMedarbejder = singleton.getMedarbejdere().get(position/*-1*/);
        System.out.println(singleton.midlertidigMedarbejder.getNavn());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
