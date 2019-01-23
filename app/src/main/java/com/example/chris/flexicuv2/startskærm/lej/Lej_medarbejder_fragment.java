package com.example.chris.flexicuv2.startskærm.lej;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import com.example.chris.flexicuv2.Bekraeftelse_bud_medarbejder_fragment;
import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.database.DBManager;
import com.example.chris.flexicuv2.model.Aftale;
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
    private final String JA = "Ja";
    private final String NEJ = "Nej";
    private Bekraeftelse_bud_medarbejder_fragment bekræftelse;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forhandling_som_lejer_fragment, container, false);
        bekræftelse = new Bekraeftelse_bud_medarbejder_fragment();
        singleton = Singleton.getInstance();
        dbManager = new DBManager();
        presenter = new Lej_presenter(this);
        singleton.midlertidigForhandling = new Forhandling();
        c1 = Calendar.getInstance();
        c2 = Calendar.getInstance();
        overskrift_TV = v.findViewById(R.id.aftaleforhandling_overskrift);
        overskrift_TV.setText("Giv mig en overskrift");
        TextView udlejer = v.findViewById(R.id.udlejer_navn_textview);
        udlejer.setText(singleton.midlertidigAftale.getUdlejer().getVirksomhedsnavn());
        TextView lejer = v.findViewById(R.id.lejer_navn_textview);
        lejer.setText(singleton.getBruger().getVirksomhedsnavn());

        TextView medarbejderNavnTV = v.findViewById(R.id.forhandling_medarbejder);
        medarbejderNavnTV.setText(singleton.midlertidigAftale.getMedarbejder().getNavn());


        //Udlejer
        //Dato
        TextView startDato_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_startdato_udlejer_textview);
        startDato_udlejerTV.setText(singleton.midlertidigAftale.getStartDato());
        TextView slutDato_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_slutdato_udlejer_textview);
        slutDato_udlejerTV.setText(singleton.midlertidigAftale.getSlutDato());

        //Arbejdsdage
        arbejdsdage_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_antal_arbejdsdage_udlejer_textview);
        presenter.udregnArbejdsdage(startDato_udlejerTV.getText().toString(), slutDato_udlejerTV.getText().toString(), false);

        //Prisen
        TextView timepris_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_timepris_udlejer_textview);
        timepris_udlejerTV.setText(singleton.midlertidigAftale.getTimePris());
        subtotal_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_subtotalen_excl_flexicuspris_udlejer_textview);
        flexicupris_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_flexicu_pris_udlejer_textview);
        totalpris_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_total_pris_udlejer_textview);

        presenter.udregnPriser(Integer.parseInt(singleton.midlertidigAftale.getTimePris()), Integer.parseInt(arbejdsdage_udlejerTV.getText().toString()), 7.4, false);
        //Eget værktøj
        TextView værktøj_udlejerTV = v.findViewById(R.id.forhandling_som_lejer_egetværktøj_udlejer_textview);

        if(singleton.midlertidigAftale.isEgetVærktøj()){
            værktøj_udlejerTV.setText(JA);
        }
        else {
            værktøj_udlejerTV.setText(NEJ);
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
        //presenter.udregnPriser(Integer.parseInt(singleton.midlertidigAftale.getUdlejPris()), Integer.parseInt(antalArbejdsdage_lejerTV.getText().toString()), 7.4, true);

        slutdato_lejerTV.addTextChangedListener(arbDageTextWatcher);
        slutdato_lejerTV.addTextChangedListener(slutDatoTextWatcher);
        startdato_lejerTV.addTextChangedListener(startDatoTextWatcher);
        startdato_lejerTV.setText(singleton.midlertidigAftale.getStartDato());
        slutdato_lejerTV.setText(singleton.midlertidigAftale.getSlutDato());

        //Eget værktøj
        egetVærktøj_switch = v.findViewById(R.id.forhandling_som_lejer_egetværktøj_lejer_switch);
        egetVærktøj_switch.setOnCheckedChangeListener(this);
        anullerButton = v.findViewById(R.id.forhandling_annuller_button);
        anullerButton.setOnClickListener(this);
        opretUdlejningButton = v.findViewById(R.id.forhandling_som_lejer_send_eller_accepter_tilbud_button);
        opretUdlejningButton.setOnClickListener(this);

        kommentarButton = v.findViewById(R.id.forhandling_tilføj_besked_button);
        kommentarButton.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forhandling_som_lejer_send_eller_accepter_tilbud_button:
                int pris = 0;
                if(!timepris_lejerET.getText().toString().equals("")){
                    pris = Integer.parseInt(timepris_lejerET.getText().toString());
                }
                int arbDage;
                if (antalArbejdsdage_lejerTV.getText().equals("antal arb. dage")) {
                    arbDage = 0;
                }else
                    arbDage = Integer.parseInt(antalArbejdsdage_lejerTV.getText().toString());
                if (presenter.checkKorrektUdfyldtInformation(
                        startdato_lejerTV.getText().toString(),
                        slutdato_lejerTV.getText().toString(),
                        pris,
                        arbDage)) {

                    Forhandling forhandling = new Forhandling();
                    if(singleton.midlertidigForhandling.getLejKommentar()!=null){
                        forhandling.setLejKommentar(singleton.midlertidigForhandling.getLejKommentar());
                    }
                    System.out.println("KOMMET HER TIL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    forhandling.setLejerStartDato(startdato_lejerTV.getText().toString());
                    forhandling.setLejerSlutDato(slutdato_lejerTV.getText().toString());
                    forhandling.setLejPris(timepris_lejerET.getText().toString());
                    forhandling.setLejEgetVærktøj(egetVærktøj_switch.isChecked());
                    forhandling.setMedarbejder(singleton.midlertidigAftale.getMedarbejder());
                    forhandling.setLejer(singleton.getBruger());
                    forhandling.setAktiv(true);
                    forhandling.setTimestamp(System.currentTimeMillis());
                    forhandling.setAftaleID(singleton.midlertidigAftale.getAftaleID());
                    forhandling.setUdlejerStartDato(singleton.midlertidigAftale.getStartDato());
                    forhandling.setUdlejerSlutDato(singleton.midlertidigAftale.getSlutDato());
                    forhandling.setUdlejEgetVærktøj(singleton.midlertidigAftale.isEgetVærktøj());
                    if(singleton.midlertidigAftale.getKommentar()!=null) {
                        forhandling.addUdlejKommentar(singleton.midlertidigAftale.getKommentar());
                    }
                    forhandling.setUdlejPris(singleton.midlertidigAftale.getTimePris());

                    singleton.addMineLejAftalerMedForhandling(singleton.midlertidigAftale);
                    /*for(Aftale aftale : singleton.getMineLejAftalerMedForhandling()){
                        if(aftale.getAftaleID().equals(singleton.midlertidigForhandling.getAftaleID())){
                            index = singleton.getMineLejAftalerMedForhandling().indexOf(forhandling);
                        }
                    }*/

                    singleton.getMineLejAftalerMedForhandling().get(singleton.getMineLejAftalerMedForhandling().size()-1).addForhandlinger(forhandling);
                    dbManager.addForhandling(forhandling);
                    setFragment(bekræftelse);
                    //getActivity().getSupportFragmentManager().popBackStack();
                }
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
                singleton.midlertidigForhandling.addLejKommentar(kommentar.getText().toString());
                popupWindow.dismiss();
            }
        });


        RecyclerView recyclerView = popupView.findViewById(R.id.forhandling_meddelelser_recyclerview);
        Forhandling_kommentar_recyclerview adapter = new Forhandling_kommentar_recyclerview(getContext(), singleton.midlertidigForhandling /*singleton.setAndresMedarbejderUdbud()*/);
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
