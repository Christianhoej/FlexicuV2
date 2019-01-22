package com.example.chris.flexicuv2.startskærm.lej;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.database.DBManager;
import com.example.chris.flexicuv2.model.Forhandling;
import com.example.chris.flexicuv2.model.Bruger;
import com.example.chris.flexicuv2.model.Singleton;
import com.example.chris.flexicuv2.startskærm.indbakke.forhandling.Forhandling_kommentar_recyclerview;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Lej_medarbejder_fragment extends Fragment implements Lej_presenter.UpdateLej, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Singleton singleton;
    private DBManager dbManager;
    private EditText timepris_lejerET, kommentarET;
    private TextView antalArbejdsdage_lejerTV, subtotalen_lejerTV, flexicugebyr_lejerTV, totalprisen_TV, startdato_lejerTV, slutdato_lejerTV, overskrift_TV;
    private Switch egetVærktøj_switch;
    private Button anullerButton, opretUdlejningButton, kommentarButton;
    private Calendar c;
    private Lej_presenter presenter;
    private TextView arbejdsdage_udlejerTV, totalpris_udlejerTV, flexicupris_udlejerTV, subtotal_udlejerTV;
    private Calendar c1, c2;
    private DatePickerDialog datepickerdialog;
    private DatePickerDialog.OnDateSetListener datepickerListener;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forhandling_som_lejer_fragment, container, false);
        singleton = Singleton.getInstance();
        dbManager = new DBManager();
        presenter = new Lej_presenter(this);
        c1 = Calendar.getInstance();
        c2 = Calendar.getInstance();
        overskrift_TV = v.findViewById(R.id.aftaleforhandling_overskrift);
        overskrift_TV.setText("Giv mig en overskrift");
        TextView udlejer = v.findViewById(R.id.udlejer_navn_textview);
        udlejer.setText(singleton.midlertidigForhandling.getUdlejer().getVirksomhedsnavn());
        TextView lejer = v.findViewById(R.id.lejer_navn_textview);
        lejer.setText(singleton.getBruger().getVirksomhedsnavn());

        TextView medarbejderNavnTV = v.findViewById(R.id.forhandling_medarbejder);
        medarbejderNavnTV.setText(singleton.midlertidigForhandling.getMedarbejder().getNavn());


        //Udlejer
        //Dato
        TextView startDato_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_startdato_udlejer_textview);
        startDato_udlejerTV.setText(singleton.midlertidigForhandling.getStartDato());
        TextView slutDato_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_slutdato_udlejer_textview);
        slutDato_udlejerTV.setText(singleton.midlertidigForhandling.getEndDato());

        //Arbejdsdage
        arbejdsdage_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_antal_arbejdsdage_udlejer_textview);
        presenter.udregnArbejdsdage(startDato_udlejerTV.getText().toString(), slutDato_udlejerTV.getText().toString(), false);

        //Prisen
        TextView timepris_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_timepris_udlejer_textview);
        timepris_udlejerTV.setText(singleton.midlertidigForhandling.getPris());
        subtotal_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_subtotalen_excl_flexicuspris_udlejer_textview);
        subtotal_udlejerTV.setText("MANGLER");
        flexicupris_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_flexicu_pris_udlejer_textview);
        flexicupris_udlejerTV.setText("MANGLER");
        totalpris_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_total_pris_udlejer_textview);
        totalpris_udlejerTV.setText("MANGLER");

        presenter.udregnPriser(Integer.parseInt(singleton.midlertidigForhandling.getPris()), Integer.parseInt(arbejdsdage_udlejerTV.getText().toString()), 7.4, false);
        //Eget værktøj
        TextView værktøj_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_egetværktøj_udlejer_textview);
        if(singleton.midlertidigForhandling.isEgetVærktøj()){
            værktøj_udlejerTV.setText("Ja");
        }
        else {
            værktøj_udlejerTV.setText("Nej");
        }





        //Lejer
        //Dato
        startdato_lejerTV = v.findViewById(R.id.forhandling_som_lejer_startdato_lejer_textview);
        startdato_lejerTV.setOnClickListener(this);

        slutdato_lejerTV = v.findViewById(R.id.forhandling_som_lejer_slutdato_lejer_textview);
        slutdato_lejerTV.setOnClickListener(this);



        //Arbejdsdage
        antalArbejdsdage_lejerTV = v.findViewById(R.id.forhandling_som_lejer_antal_arbejdsdage_lejer_textview);
        antalArbejdsdage_lejerTV.setText(arbejdsdage_udlejerTV.getText().toString());

        //Pris
        timepris_lejerET = v.findViewById(R.id.forhandling_som_lejer_timepris_lejer_editview);
        timepris_lejerET.setText(timepris_udlejerTV.getText().toString());
        timepris_lejerET.addTextChangedListener(prisTextWatch);
        subtotalen_lejerTV = v.findViewById(R.id.forhandling_som_lejer_subtotalen_excl_flexicuspris_lejer_textview);
        subtotalen_lejerTV.setText(subtotal_udlejerTV.getText().toString()); ;
        flexicugebyr_lejerTV = v.findViewById(R.id.forhandling_som_lejer_flexicu_pris_lejer_textview);
        flexicugebyr_lejerTV.setText(flexicupris_udlejerTV.getText().toString());
        totalprisen_TV = v.findViewById(R.id.forhandling_som_lejer_total_pris_lejer_textview);
        totalprisen_TV.setText(totalpris_udlejerTV.getText().toString());
        //presenter.udregnPriser(Integer.parseInt(singleton.midlertidigAftale.getPris()), Integer.parseInt(antalArbejdsdage_lejerTV.getText().toString()), 7.4, true);

        slutdato_lejerTV.addTextChangedListener(arbDageTextWatcher);
        slutdato_lejerTV.addTextChangedListener(slutDatoTextWatcher);
        startdato_lejerTV.addTextChangedListener(startDatoTextWatcher);
        startdato_lejerTV.setText(singleton.midlertidigForhandling.getStartDato());
        slutdato_lejerTV.setText(singleton.midlertidigForhandling.getEndDato());

        //Eget værktøj
        egetVærktøj_switch = v.findViewById(R.id.forhandling_som_lejer_egetværktøj_lejer_switch);
        egetVærktøj_switch.setOnCheckedChangeListener(this);
        anullerButton = v.findViewById(R.id.forhandling_annuller_button);
        anullerButton.setOnClickListener(this);
        opretUdlejningButton = v.findViewById(R.id.forhandling_send_eller_accepter_tilbud_button);
        opretUdlejningButton.setOnClickListener(this);

        kommentarButton = v.findViewById(R.id.forhandling_tilføj_besked_button);
        kommentarButton.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forhandling_send_eller_accepter_tilbud_button:
                int pris = 0;
                if(!timepris_lejerET.getText().toString().equals("")){
                    pris = Integer.parseInt(timepris_lejerET.getText().toString());
                }
                int arbDage;
                if (antalArbejdsdage_lejerTV.getText().equals("antal arb. dage")) {
                    arbDage = 0;
                }else
                    arbDage = Integer.parseInt(antalArbejdsdage_lejerTV.getText().toString());
                System.out.println("Timepris: " + timepris_lejerET.getText().toString() + ", den anden pris: " + pris);
                if (presenter.checkKorrektUdfyldtInformation(
                        startdato_lejerTV.getText().toString(),
                        slutdato_lejerTV.getText().toString(),
                        pris,
                        arbDage)) {

                    Forhandling forhandling = new Forhandling();
                    if(singleton.midlertidigForhandling.getKommentar()!=null){
                        forhandling.setKommentar(singleton.midlertidigForhandling.getKommentar());
                    }
                    forhandling.setStartDato(startdato_lejerTV.getText().toString());
                    forhandling.setEndDato(slutdato_lejerTV.getText().toString());
                    forhandling.setPris(timepris_lejerET.getText().toString());
                    forhandling.setEgetVærktøj(egetVærktøj_switch.isChecked());
                    forhandling.setMedarbejder(singleton.midlertidigForhandling.getMedarbejder());
                    forhandling.setLejer(singleton.getBruger());
                    forhandling.setOprindeligUdlejID(singleton.midlertidigForhandling.getOprindeligUdlejID());
                    forhandling.setUdlejer(singleton.midlertidigForhandling.getUdlejer());
                    forhandling.setAktiv(true);
                   // forhandling.setTimestamp(Long.parseLong(ServerValue.TIMESTAMP.get("timestamp")));
                    singleton.addMineLejForhandlinger(forhandling);
                    dbManager.createForhandling(forhandling);
                    getActivity().getSupportFragmentManager().popBackStack();
                }
                System.out.println("timepris efter check: " + timepris_lejerET.getText().toString() + ", den anden pris: " + pris);
                break;
            case R.id.forhandling_annuller_button:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.forhandling_tilføj_besked_button:
                onButtonShowPopupWindowClick(v);
                break;
            case R.id.forhandling_som_lejer_slutdato_lejer_textview:
                findEnDato(false, R.id.forhandling_som_lejer_startdato_lejer_textview, c2);
                break;
            case R.id.forhandling_som_lejer_startdato_lejer_textview:
                findEnDato(true, R.id.forhandling_som_lejer_startdato_lejer_textview, c1);
        }
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
                    startdato_lejerTV.setText(s);
                    c1.set(year,month-1,dayOfMonth);
                    slutdato_lejerTV.setEnabled(true);
                }
                else {
                    c2.set(year, month-1,dayOfMonth);
                    slutdato_lejerTV.setText(s);
                }
            }
        };


        final int år = c.get(Calendar.YEAR);
        final int måned = c.get(Calendar.MONTH);
        final int dag = c.get(Calendar.DAY_OF_MONTH);
        datepickerdialog = new DatePickerDialog(getContext(),datepickerListener,år,måned,dag );


        if(c1.getTimeInMillis()>(System.currentTimeMillis()-1000)){
            if(id != R.id.forhandling_som_lejer_startdato_lejer_textview) {
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

    public void onButtonShowPopupWindowClick(View view) {
        //tilPopUp = (View) findViewById(R.id.tilPopUp);
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.forhandling_meddelelser_popup_fragment, null);


        //get width
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;

        // create the popup window
        int height = displaymetrics.heightPixels;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, (width-100), (height-200), focusable);

        //final PopupWindow popupWindowt = new PopupWindow();
        popupWindow.setContentView(popupView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setElevation(20);
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        final EditText kommentar = popupView.findViewById(R.id.skriv_kommentar_edittext);

        final Button gem = popupView.findViewById(R.id.gem_kommentar);
        gem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleton.midlertidigForhandling.setKommentar(kommentar.getText().toString());
                popupWindow.dismiss();
            }
        });

        ArrayList<Forhandling> forhandling = new ArrayList<>();
        Forhandling af = new Forhandling();
        af.setKommentar("Hej");
        af.setUdlejer(singleton.getBruger());
        af.setSidstSendtAftale(singleton.getBruger());
        Forhandling af1 = new Forhandling();
        af1.setKommentar("Hej igen");
        Bruger br = new Bruger();
        br.setBrugerensNavn("Gunn");
        br.setBrugerID("ldjgsø");
        af1.setUdlejer(singleton.getBruger());
        af1.setSidstSendtAftale(br);

        Forhandling af2 = new Forhandling();
        af2.setUdlejer(singleton.getBruger());
        af2.setKommentar("Hvad laver du?");
        af2.setSidstSendtAftale(singleton.getBruger());
        forhandling.add(af);
        forhandling.add(af1);
        forhandling.add(af2);


        RecyclerView recyclerView = popupView.findViewById(R.id.forhandling_meddelelser_recyclerview);
        Forhandling_kommentar_recyclerview adapter = new Forhandling_kommentar_recyclerview(getContext(), forhandling /*singleton.getMedarbejdereTilUdlejning()*/);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private TextWatcher prisTextWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length()>0 && !(antalArbejdsdage_lejerTV.getText().toString().equals("antal arb. dage"))){
                presenter.udregnPriser(Integer.parseInt(s.toString()), Integer.parseInt(antalArbejdsdage_lejerTV.getText().toString()), 7.4, true);
            }
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
            presenter.udregnArbejdsdage(startdato_lejerTV.getText().toString(), slutdato_lejerTV.getText().toString(), true);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!(timepris_lejerET.getText().toString().equals("")) )
                presenter.udregnPriser(Integer.parseInt(timepris_lejerET.getText().toString()),Integer.parseInt(antalArbejdsdage_lejerTV.getText().toString()), 7.4, true);
        }
    };

    private TextWatcher startDatoTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!timepris_lejerET.getText().toString().equals("") && !slutdato_lejerTV.getText().toString().equals(" dd / mm / yyyy ")) {
                presenter.udregnArbejdsdage(startdato_lejerTV.getText().toString(), slutdato_lejerTV.getText().toString(), true);
                presenter.udregnPriser(Integer.parseInt(timepris_lejerET.getText().toString()), Integer.parseInt(antalArbejdsdage_lejerTV.getText().toString()), 7.4, true);
            }
            if(!slutdato_lejerTV.getText().toString().equals(" dd / mm / yyyy ")){
                presenter.udregnArbejdsdage(startdato_lejerTV.getText().toString(), slutdato_lejerTV.getText().toString(), true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            startdato_lejerTV.setError(null);
        }
    };

    private TextWatcher slutDatoTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            presenter.udregnArbejdsdage(startdato_lejerTV.getText().toString(), slutdato_lejerTV.getText().toString(), true);

            if (!timepris_lejerET.getText().toString().equals("")) {
                presenter.udregnPriser(Integer.parseInt(timepris_lejerET.getText().toString()), Integer.parseInt(antalArbejdsdage_lejerTV.getText().toString()), 7.4, true);
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

    @Override
    public void opdaterSubtotalLejer(double værdi) {
        timepris_lejerET.setError(null);
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        subtotalen_lejerTV.setText(numberFormat.format(værdi)+ " dkk");
    }

    @Override
    public void opdaterFlexicufeeLejer(double værdi) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");

        flexicugebyr_lejerTV.setText(numberFormat.format(værdi)+ " dkk");
    }

    @Override
    public void opdaterTotalLejer(double værdi) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        totalprisen_TV.setText(numberFormat.format(værdi) + " dkk");

    }

    @Override
    public void errorStartdato(String errorMSG) {
        startdato_lejerTV.setError(errorMSG);
    }

    @Override
    public void errorSlutdato(String errorMSG) {
        slutdato_lejerTV.setError(errorMSG);
    }

    @Override
    public void errorTimepris(String errorMSG) {
        timepris_lejerET.setError(errorMSG);
    }

    @Override
    public void errorArbejdsdage(String errorMSG) {
        antalArbejdsdage_lejerTV.setError(errorMSG);
    }

    @Override
    public void opdaterAntalArbejdsdageLejer(int dage) {
        antalArbejdsdage_lejerTV.setText(""+dage);
    }

    @Override
    public void opdaterAntalArbejdsdageUdlejer(int dage) {
        arbejdsdage_udlejerTV.setText(""+dage);
    }

    @Override
    public void opdaterSubtotalUdlejer(double værdi) {
        subtotal_udlejerTV.setText(""+værdi);
    }

    @Override
    public void opdaterFlexicufeeUdlejer(double værdi) {
        flexicupris_udlejerTV.setText(""+værdi);
    }

    @Override
    public void opdaterTotalUdlejer(double værdi) {
        totalpris_udlejerTV.setText(""+værdi);
    }
}
