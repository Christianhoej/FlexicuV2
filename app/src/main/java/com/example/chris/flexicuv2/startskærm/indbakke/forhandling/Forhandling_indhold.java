package com.example.chris.flexicuv2.startskærm.indbakke.forhandling;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.chris.flexicuv2.Bekraeftelse_bud_medarbejder_fragment;
import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.database.DBManager;
import com.example.chris.flexicuv2.model.Aftale;
import com.example.chris.flexicuv2.model.Forhandling;
import com.example.chris.flexicuv2.model.Singleton;
import com.example.chris.flexicuv2.startskærm.indbakke.aftaler.Forhandling_recyclerview;
import com.example.chris.flexicuv2.startskærm.lej.Lej_presenter;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class Forhandling_indhold extends Fragment implements View.OnClickListener, Forhandling_presenter.UpdateForhandling, CompoundButton.OnCheckedChangeListener {

    private Singleton singleton;
    private DBManager dbManager;
    private Bekraeftelse_bud_medarbejder_fragment bekræftelse;
    private Calendar c1;
    private Calendar c2;
    private DatePickerDialog.OnDateSetListener datepickerListener;
    private DatePickerDialog datepickerdialog;

    //Redigerbar
    private TextView startdato_redigerTV, slutdato_redigerTV, antalArbejdsdage_redigerTV, subtotalen_redigerTV, flexicugebyr_redigerTV, totalprisen_redigerTV;
    private EditText timepris_redigerET;
    private Switch egetVærktøj_rediger_switch;
    //Faste
    private TextView startdato_fastTV, slutdato_fastTV, antalArbejdsdage_fastTV, timePris_fastTV, subtotalen_fastTV, flexicugebyr_fastTV, totalprisen_fastTV;
    private TextView egetVærktøj_fast_switch;

    private Button anullerButton, opretUdlejningButton, kommentarButton;
    private Forhandling_presenter presenter;

    private TextView startdatoET, slutdatoET, timepris;
    public Forhandling_indhold() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.forhandling_indhold_fragment, container, false);
        singleton = Singleton.getInstance();
        dbManager = new DBManager();
        bekræftelse = new Bekraeftelse_bud_medarbejder_fragment();
        presenter = new Forhandling_presenter(this);
        c1 = Calendar.getInstance();
        c2 = Calendar.getInstance();

        TextView udlejerNavn = v.findViewById(R.id.forhandling_udlejer_navn_textview);
        udlejerNavn.setText(singleton.midlertidigAftale.getUdlejer().getVirksomhedsnavn());
        TextView lejerNavn = v.findViewById(R.id.forhandling_lejer_navn_textview);
        lejerNavn.setText(singleton.midlertidigForhandling.getLejer().getVirksomhedsnavn());

        TextView medarbejderNavnTV = v.findViewById(R.id.forhandling_medarbejder);
        medarbejderNavnTV.setText(singleton.midlertidigForhandling.getMedarbejder().getNavn());


        TextView titel_fastTV = v.findViewById(R.id.forhandling_lejer_udlejer_fast_textview);
        TextView titel_redigerTV = v.findViewById(R.id.forhandling_lejer_udlejer_rediger_textview);
        TextView overskrift_TV = v.findViewById(R.id.forhandling_overskrift);



        //Fastlagt
        //Dato
        startdato_fastTV = v.findViewById(R.id.forhandling_startdato_fast_textview);
        slutdato_fastTV = v.findViewById(R.id.forhandling_slutdato_fast_textview);

        //Arbejdsdage
        antalArbejdsdage_fastTV = v.findViewById(R.id.forhandling_antal_arbejdsdage_fast_textview);

        //Prisen
        timePris_fastTV = v.findViewById(R.id.forhandling_timepris_fast_textview);
        subtotalen_fastTV = v.findViewById(R.id.forhandling_subtotalen_excl_flexicuspris_fast_textview);
        flexicugebyr_fastTV = v.findViewById(R.id.forhandling_flexicu_pris_fest_textview);
        totalprisen_fastTV = v.findViewById(R.id.forhandling_total_pris_fast_textview);

        //Eget værktøj
        egetVærktøj_fast_switch = v.findViewById(R.id.forhandling_egetværktøj_fast_textview);


        //Rediger
        //Dato
        startdato_redigerTV = v.findViewById(R.id.forhandling_startdato_rediger_textview);
        startdato_redigerTV.setOnClickListener(this);

        slutdato_redigerTV = v.findViewById(R.id.forhandling_slutdato_rediger_textview);
        slutdato_redigerTV.setOnClickListener(this);



        //Arbejdsdage
        antalArbejdsdage_redigerTV = v.findViewById(R.id.forhandling_antal_arbejdsdage_rediger_textview);

        //Pris
        timepris_redigerET = v.findViewById(R.id.forhandling_timepris_rediger_editview);
        timepris_redigerET.addTextChangedListener(prisTextWatch);
        subtotalen_redigerTV = v.findViewById(R.id.forhandling_subtotalen_excl_flexicuspris_rediger_textview);
        flexicugebyr_redigerTV = v.findViewById(R.id.forhandling_flexicu_pris_rediger_textview);
        totalprisen_redigerTV = v.findViewById(R.id.forhandling_total_pris_rediger_textview);
        //presenter.udregnPriser(Integer.parseInt(singleton.midlertidigAftale.getUdlejPris()), Integer.parseInt(antalArbejdsdage_lejerTV.getText().toString()), 7.4, true);

        slutdato_redigerTV.addTextChangedListener(arbDageTextWatcher);
        slutdato_redigerTV.addTextChangedListener(slutDatoTextWatcher);
        startdato_redigerTV.addTextChangedListener(startDatoTextWatcher);

        //Eget værktøj
        egetVærktøj_rediger_switch = v.findViewById(R.id.forhandling_egetværktøj_rediger_switch);
        egetVærktøj_rediger_switch.setOnCheckedChangeListener(this);
        anullerButton = v.findViewById(R.id.forhandling_annuller_button);
        anullerButton.setOnClickListener(this);
        opretUdlejningButton = v.findViewById(R.id.forhandling_send_eller_accepter_tilbud_button);
        opretUdlejningButton.setOnClickListener(this);

        kommentarButton = v.findViewById(R.id.forhandling_tilføj_besked_button);
        kommentarButton.setOnClickListener(this);





        startdatoET = v.findViewById(R.id.forhandling_startdato_rediger_textview);
        startdatoET.setOnClickListener(this);
        slutdatoET = v.findViewById(R.id.forhandling_slutdato_rediger_textview);
        slutdatoET.setOnClickListener(this);
        timepris = v.findViewById(R.id.forhandling_timepris_rediger_editview);


        //Hvis lejer har redigeret sidst:
        if(singleton.midlertidigForhandling.getSidstSendtAftale().getBrugerID().equals(singleton.midlertidigForhandling.getLejer().getBrugerID())){
            presenter.updateFaste(true, singleton.midlertidigForhandling.getLejerStartDato(), singleton.midlertidigForhandling.getLejerSlutDato(), singleton.midlertidigForhandling.getLejPris(), singleton.midlertidigForhandling.isLejEgetVærktøj());
            presenter.updateRediger(false,singleton.midlertidigForhandling.getUdlejerStartDato(), singleton.midlertidigForhandling.getUdlejerSlutDato(), singleton.midlertidigForhandling.getUdlejPris(), singleton.midlertidigForhandling.isUdlejEgetVærktøj());
            titel_fastTV.setText("Lejer");
            titel_redigerTV.setText("Udlejer");
            overskrift_TV.setText("Udlej medarbejder");
        }
        else{
            presenter.updateFaste(false,singleton.midlertidigForhandling.getUdlejerStartDato(), singleton.midlertidigForhandling.getUdlejerSlutDato(), singleton.midlertidigForhandling.getUdlejPris(), singleton.midlertidigForhandling.isUdlejEgetVærktøj());
            presenter.updateRediger(true,singleton.midlertidigForhandling.getLejerStartDato(), singleton.midlertidigForhandling.getLejerSlutDato(), singleton.midlertidigForhandling.getLejPris(), singleton.midlertidigForhandling.isLejEgetVærktøj());
            titel_fastTV.setText("Udlejer");
            titel_redigerTV.setText("Lejer");
            overskrift_TV.setText("Lej medarbejder");
        }

    return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.forhandling_send_eller_accepter_tilbud_button:
                int pris = 0;
                if(!timepris_redigerET.getText().toString().equals("")){
                    pris = Integer.parseInt(timepris_redigerET.getText().toString());
                }
                int arbDage = 0;
                if (antalArbejdsdage_redigerTV.getText().equals("antal arb. dage")) {
                    arbDage = 0;
                }else
                    arbDage = Integer.parseInt(antalArbejdsdage_redigerTV.getText().toString());

                if (presenter.checkKorrektUdfyldtInformation(startdatoET.getText().toString(),
                        slutdatoET.getText().toString(), arbDage, pris)) {

                    Forhandling forhandling = singleton.midlertidigForhandling;
                    if(singleton.midlertidigForhandling.getSidstSendtAftale().getBrugerID().equals(singleton.midlertidigForhandling.getLejer().getBrugerID())){
                        //Jeg er udlejer
                        if(singleton.midlertidigForhandling.getUdlejKommentar()!=null){
                            forhandling.setUdlejKommentar(singleton.midlertidigForhandling.getUdlejKommentar());
                        }
                        forhandling.setUdlejPris(timepris_redigerET.getText().toString());
                        forhandling.setUdlejEgetVærktøj(egetVærktøj_rediger_switch.isChecked());
                        forhandling.setUdlejerSlutDato(slutdato_redigerTV.getText().toString());
                        forhandling.setUdlejerStartDato(startdato_redigerTV.getText().toString());
                    }
                    else{
                        //Jeg er lejer
                        if(singleton.midlertidigForhandling.getLejKommentar()!=null){
                            forhandling.setLejKommentar(singleton.midlertidigForhandling.getLejKommentar());
                        }
                        forhandling.setLejPris(timepris_redigerET.getText().toString());
                        forhandling.setLejEgetVærktøj(egetVærktøj_rediger_switch.isChecked());
                        forhandling.setLejerSlutDato(slutdato_redigerTV.getText().toString());
                        forhandling.setLejerStartDato(startdato_redigerTV.getText().toString());
                    }
                    int index = 0;
                    for(Aftale aftale : singleton.getAlleMineAftalerMedForhandling()){
                        if(aftale.getAftaleID().equals(forhandling.getAftaleID())){
                            index = singleton.getAlleMineAftalerMedForhandling().indexOf(aftale);
                            break;
                        }
                    }
                    singleton.getAlleMineAftalerMedForhandling().get(index).addForhandlinger(forhandling);
                    dbManager.addForhandling(forhandling);

                    setFragment(bekræftelse);
                }
                break;

            case R.id.forhandling_annuller_button:
                getActivity().getSupportFragmentManager().popBackStack();
                break;

            case R.id.forhandling_slutdato_rediger_textview:
                findEnDato(false, R.id.forhandling_slutdato_rediger_textview, c2);
                break;

            case R.id.forhandling_startdato_rediger_textview:
                findEnDato(true, R.id.forhandling_startdato_rediger_textview, c1);
                break;
        }

    }

    public void setFragment(Fragment fragment) {
        //startskærmFrameTilDiverse.removeAllViews();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.startskærm_frame_til_diverse, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }

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
            if (s.length()>0 && !(antalArbejdsdage_redigerTV.getText().toString().equals("antal arb. dage"))){
                presenter.udregnPriser(Integer.parseInt(s.toString()), Integer.parseInt(antalArbejdsdage_redigerTV.getText().toString()), 7.4, true);
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
            presenter.udregnArbejdsdage(startdato_redigerTV.getText().toString(), slutdato_redigerTV.getText().toString(), true);


        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!(timepris_redigerET.getText().toString().equals("")) )
                presenter.udregnPriser(Integer.parseInt(timepris_redigerET.getText().toString()),Integer.parseInt(antalArbejdsdage_redigerTV.getText().toString()), 7.4, true);
        }
    };

    private TextWatcher slutDatoTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //presenter.udregnArbejdsdage(startdatoET.getText().toString(), slutdatoET.getText().toString());
            presenter.udregnArbejdsdage(startdato_redigerTV.getText().toString(), slutdato_redigerTV.getText().toString(), true);

            if(!timepris_redigerET.getText().toString().equals("")) {
                presenter.udregnPriser(Integer.parseInt(timepris_redigerET.getText().toString()), Integer.parseInt(antalArbejdsdage_redigerTV.getText().toString()), 7.4, true);
            }


        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!antalArbejdsdage_redigerTV.getText().toString().equals("0")) {
                slutdatoET.setError(null);
                antalArbejdsdage_redigerTV.setError(null);
            }
        }
    };

    private TextWatcher startDatoTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!timepris_redigerET.getText().toString().equals("") && !slutdatoET.getText().toString().equals(" dd / mm / yyyy ")) {
                presenter.udregnArbejdsdage(startdato_redigerTV.getText().toString(), slutdato_redigerTV.getText().toString(), true);
                presenter.udregnPriser(Integer.parseInt(timepris_redigerET.getText().toString()), Integer.parseInt(antalArbejdsdage_redigerTV.getText().toString()), 7.4, true);
            }
            if(!slutdatoET.getText().toString().equals(" dd / mm / yyyy ")){
                presenter.udregnArbejdsdage(startdato_redigerTV.getText().toString(), slutdato_redigerTV.getText().toString(),true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            startdatoET.setError(null);
        }
    };

    @Override
    public void errorStartdato(String errorMSG) {
        startdato_redigerTV.setError(errorMSG);
    }

    @Override
    public void errorSlutdato(String errorMSG) {
        slutdato_redigerTV.setError(errorMSG);
    }

    @Override
    public void errorTimepris(String errorMSG) {
        timepris_redigerET.setError(errorMSG);
    }

    @Override
    public void errorArbejdsdage(String errorMSG) {
        antalArbejdsdage_redigerTV.setError(errorMSG);
    }


    @Override
    public void opdaterAntalArbejdsdageRediger(int dage) {
        antalArbejdsdage_redigerTV.setText(""+dage);
    }

    @Override
    public void opdaterSubtotalRediger(double værdi) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        subtotalen_redigerTV.setText(numberFormat.format(værdi) + " dkk");
    }

    @Override
    public void opdaterFlexicufeeRediger(double værdi) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        flexicugebyr_redigerTV.setText(numberFormat.format(værdi) + " dkk");
    }

    @Override
    public void opdaterTotalRediger(double værdi) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        totalprisen_redigerTV.setText(numberFormat.format(værdi) + " dkk");
    }

    @Override
    public void opdaterStartDatoRediger(String startDato) {
        startdato_redigerTV.setText(startDato);
    }

    @Override
    public void opdaterSlutDatoRediger(String slutdato) {
        slutdato_redigerTV.setText(slutdato);
    }

    @Override
    public void opdaterTimePrisRediger(String timepris) {
        timepris_redigerET.setText(timepris);
    }

    @Override
    public void opdaterEgetVærktøjRediger(String egetVærktøj) {
        if(egetVærktøj.equals("Ja")) {
            egetVærktøj_rediger_switch.setChecked(true);
            egetVærktøj_rediger_switch.setText("Ja");
        }
        else {
            egetVærktøj_rediger_switch.setChecked(false);
            egetVærktøj_rediger_switch.setText("Nej");
        }
    }

    @Override
    public void opdaterAntalArbejdsdageFast(int dage) {
        antalArbejdsdage_fastTV.setText(""+dage);
    }

    @Override
    public void opdaterSubtotalFast(double værdi) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        subtotalen_fastTV.setText(numberFormat.format(værdi) + " dkk");
    }

    @Override
    public void opdaterFlexicufeeFast(double værdi) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        flexicugebyr_fastTV.setText(numberFormat.format(værdi) + " dkk");
    }

    @Override
    public void opdaterTotalFast(double værdi) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        totalprisen_fastTV.setText(numberFormat.format(værdi) + " dkk");
    }

    @Override
    public void opdaterStartDatoFast(String startDato) {
        startdato_fastTV.setText(startDato);
    }

    @Override
    public void opdaterSlutDatoFast(String slutdato) {
        slutdato_fastTV.setText(slutdato);
    }

    @Override
    public void opdaterTimePrisFast(String timepris) {
        timePris_fastTV.setText(timepris);
    }

    @Override
    public void opdaterEgetVærktøjFast(String egetVærktøj) {
        egetVærktøj_fast_switch.setText(egetVærktøj);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
            egetVærktøj_rediger_switch.setText("Ja");
        else
            egetVærktøj_rediger_switch.setText("Nej");


    }
}
