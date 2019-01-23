package com.example.chris.flexicuv2.startskærm.udlej;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import com.example.chris.flexicuv2.Bekraeftelse_medarbejder_udbud_fragment;
import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.database.DBManager;
import com.example.chris.flexicuv2.model.Aftale;
import com.example.chris.flexicuv2.model.Medarbejder;
import com.example.chris.flexicuv2.model.Singleton;

import java.text.DecimalFormat;
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
    private Calendar c1, c2;
    private Singleton singleton;
    private DBManager dbManager;
    private Medarbejder medarbejderValgt;
    private DatePickerDialog datepickerdialog;
    private DatePickerDialog.OnDateSetListener datepickerListener;
    private Udlejning_Presenter presenter;
    private Bekraeftelse_medarbejder_udbud_fragment bekraeftelseFragment;

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
        c1 = Calendar.getInstance();
        c2 = Calendar.getInstance();
        startdatoET = v.findViewById(R.id.udlejning_startdato_textview1);
        startdatoET.setOnClickListener(this);
        startdatoET.addTextChangedListener(startDatoTextWatcher); //TODO måske anvendes til at lave errorcheck på datoer
        slutdatoET = v.findViewById(R.id.udlejning_slutdato_textview);
        slutdatoET.setOnClickListener(this);

        slutdatoET.addTextChangedListener(arbDageTextWatcher);
        slutdatoET.addTextChangedListener(slutDatoTextWatcher);

        c2 =Calendar.getInstance();
        c1 =Calendar.getInstance();


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
        egetVærktøj_switch.setChecked(false);
        egetVærktøj_switch.setText("Nej");

        anullerButton = v.findViewById(R.id.udlejning_annuller_button);
        anullerButton.setOnClickListener(this);
        opretUdlejningButton = v.findViewById(R.id.udlejning_udlej_button);
        opretUdlejningButton.setOnClickListener(this);
        presenter = new Udlejning_Presenter(this);

        bekraeftelseFragment = new Bekraeftelse_medarbejder_udbud_fragment();

        return v;
    }
    public void opretSpinner(View v){
        ArrayList<Medarbejder> temp = Singleton.getMedarbejdere();
        ArrayList<Medarbejder> medarbejdere = new ArrayList<>();
        Medarbejder spinneroverskrift = new Medarbejder();
        spinneroverskrift.setNavn("Vælg medarbejder");
        spinneroverskrift.setFødselsår(Calendar.getInstance().get(Calendar.YEAR));
        //medarbejdere.add(spinneroverskrift);
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
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        subtotalen_TV.setText(numberFormat.format(værdi)+ " dkk");
    }

    @Override
    public void opdaterFlexicufee(double værdi) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");

        flexicugebyr_TV.setText(numberFormat.format(værdi)+ " dkk");

    }

    @Override
    public void opdaterTotal(double værdi) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        totalprisen_TV.setText(numberFormat.format(værdi) + " dkk");

    }

    @Override
    public void opdaterAntalArbejdsdage(int dage) {
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
    public void errorArbejdsdage(String errorMSG) {
        antalArbejdsdage_TV.setError(errorMSG);
        slutdatoET.setError(errorMSG);
    }

    @Override
    public void setStartDato(String startDato) {
        startdatoET.setText(startDato);
    }

    @Override
    public void setSlutDato(String slutDato) {
        slutdatoET.setText(slutDato);
    }

    @Override
    public void setTimepris(int timepris) {
        timeprisET.setText(Integer.toString(timepris));
    }

    @Override
    public void setVærktøj(Boolean værktøj) {
        egetVærktøj_switch.setChecked(værktøj);
    }

    @Override
    public void setKommentar(ArrayList<String> kommentar) {
        kommentarET.setText(kommentar.get(1));
    }

    @Override
    public void setMedarbejder(Medarbejder medarbejder) {
        //medarbejderSpinner.setSelection(medarbejder);
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
                int arbDage = 0;
                if (antalArbejdsdage_TV.getText().equals("antal arb. dage")) {
                        arbDage = 0;
                }else
                    arbDage = Integer.parseInt(antalArbejdsdage_TV.getText().toString());

                if (presenter.checkKorrektUdfyldtInformation(
                        startdatoET.getText().toString(),
                        slutdatoET.getText().toString(),
                        arbDage,
                        pris,
                        egetVærktøj_switch.getText().toString(),
                        kommentarET.getText().toString())
                        ) {

                    singleton.midlertidigAftale = new Aftale();
                    singleton.midlertidigAftale.setUdlejer(singleton.getBruger());
                    singleton.midlertidigAftale.setAktiv(true);
                    singleton.midlertidigAftale.setMedarbejder(singleton.midlertidigMedarbejder);
                    singleton.midlertidigAftale.setEgetVærktøj(egetVærktøj_switch.isChecked());
                    singleton.midlertidigAftale.setStartDato(startdatoET.getText().toString());
                    singleton.midlertidigAftale.setSlutDato(slutdatoET.getText().toString());
                    singleton.midlertidigAftale.setTimePris(timeprisET.getText().toString());
                    singleton.midlertidigAftale.setKommentar(kommentarET.getText().toString());
                    singleton.midlertidigAftale.setTimestamp(System.currentTimeMillis());
                    singleton.addMineMedarbejderUdbud(singleton.midlertidigAftale);

                    //singleton.addLedigeMedarbejder(singleton.midlertidigForhandling);
                    dbManager.createUdbud(singleton.midlertidigAftale);

                    setFragment(bekraeftelseFragment);
                }

                break;
            case R.id.udlejning_annuller_button:
                getActivity().getSupportFragmentManager().popBackStack();
                break;

            case R.id.udlejning_slutdato_textview:
                findEnDato(false, R.id.udlejning_slutdato_textview, c2);


                break;
            case R.id.udlejning_startdato_textview1:
                findEnDato(true, R.id.udlejning_startdato_textview1, c1);


                break;
        }
    }

    public void setFragment(Fragment fragment) {
        //startskærmFrameTilDiverse.removeAllViews();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.startskærm_frame_til_diverse, fragment);
        //fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }

    /**
     * metoden starter en datepicker i dialog boks hvor der kan vælges datoer fra
     * @param start : tilkendegiver om det er start eller slutdatoen -> hvis det er startdatoen så er start true
     */
    private void findEnDato(final boolean start, int id, Calendar c) {
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

                String s = " " + dag + " / " + måned + " / " + year + " ";
                if(start) {
                    startdatoET.setText(s);
                    c1.set(year,month-1,dayOfMonth);
                    slutdatoET.setEnabled(true);
                }
                else {
                    c2.set(year, month-1,dayOfMonth);
                    slutdatoET.setText(s);
                }
            }
        };





        final int år = c.get(Calendar.YEAR);
        final int måned = c.get(Calendar.MONTH);
        final int dag = c.get(Calendar.DAY_OF_MONTH);
        datepickerdialog = new DatePickerDialog(getContext(),datepickerListener,år,måned,dag );


        if(c1.getTimeInMillis()>(System.currentTimeMillis()-1000)){
            if(id != R.id.udlejning_startdato_textview1) {
                datepickerdialog.getDatePicker().setMinDate(c1.getTimeInMillis());
            }
            else
            datepickerdialog.getDatePicker().setMinDate(System.currentTimeMillis());

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
            if (s.length()>0 && !(antalArbejdsdage_TV.getText().toString().equals("antal arb. dage"))){
                presenter.udregnPriser(Integer.parseInt(s.toString()), Integer.parseInt(antalArbejdsdage_TV.getText().toString()), 7.4);
            }
            //TODO skal også tjekke om der er værdier i de to datoer
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher arbDageTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.udregnArbejdsdage(startdatoET.getText().toString(), slutdatoET.getText().toString());


        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!(timeprisET.getText().toString().equals("")) )
                presenter.udregnPriser(Integer.parseInt(timeprisET.getText().toString()),Integer.parseInt(antalArbejdsdage_TV.getText().toString()), 7.4);
        }
    };


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
            egetVærktøj_switch.setText("Ja");
        else
            egetVærktøj_switch.setText("Nej");

    }

    private TextWatcher startDatoTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!timeprisET.getText().toString().equals("") && !slutdatoET.getText().toString().equals(" dd / mm / yyyy ")) {
                presenter.udregnArbejdsdage(startdatoET.getText().toString(), slutdatoET.getText().toString());
                presenter.udregnPriser(Integer.parseInt(timeprisET.getText().toString()), Integer.parseInt(antalArbejdsdage_TV.getText().toString()), 7.4);
            }
            if(!slutdatoET.getText().toString().equals(" dd / mm / yyyy ")){
                presenter.udregnArbejdsdage(startdatoET.getText().toString(), slutdatoET.getText().toString());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        startdatoET.setError(null);
        }
    };
    private TextWatcher slutDatoTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //presenter.udregnArbejdsdage(startdatoET.getText().toString(), slutdatoET.getText().toString());
            presenter.udregnArbejdsdage(startdatoET.getText().toString(), slutdatoET.getText().toString());

            if(!timeprisET.getText().toString().equals("")) {
                presenter.udregnPriser(Integer.parseInt(timeprisET.getText().toString()), Integer.parseInt(antalArbejdsdage_TV.getText().toString()), 7.4);
            }


        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!antalArbejdsdage_TV.getText().toString().equals("0")) {
                slutdatoET.setError(null);
                antalArbejdsdage_TV.setError(null);
            }
        }
    };




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
