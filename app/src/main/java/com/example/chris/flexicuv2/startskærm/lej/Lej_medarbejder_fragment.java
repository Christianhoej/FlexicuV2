package com.example.chris.flexicuv2.startskærm.lej;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.database.DBManager;
import com.example.chris.flexicuv2.medarbejdere.Rediger_medarbejder_fragment_1;
import com.example.chris.flexicuv2.model.Aftale;
import com.example.chris.flexicuv2.model.Bruger;
import com.example.chris.flexicuv2.model.Singleton;
import com.example.chris.flexicuv2.startskærm.indbakke.forhandling.Forhandling_kommentar_recyclerview;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Lej_medarbejder_fragment extends Fragment implements Lej_presenter.UpdateLej, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Singleton singleton;
    private DBManager dbManager;
    private EditText timeprise_lejerET, kommentarET;
    private TextView antalArbejdsdage_lejerTV, subtotalen_lejerTV, flexicugebyr_lejerTV, totalprisen_TV, startdato_lejerTV, slutdato_lejerTV, overskrift_TV;
    private Switch egetVærktøj_switch;
    private Button anullerButton, opretUdlejningButton, kommentarButton;
    private Calendar c;
    private Lej_presenter presenter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forhandling_som_lejer_fragment, container, false);
        singleton = Singleton.getInstance();
        dbManager = new DBManager();
        c = Calendar.getInstance();
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
        TextView startDato_udlejerTV = v.findViewById(R.id.forhandling_startdato_udlejer_textview);
        startDato_udlejerTV.setText(singleton.midlertidigAftale.getStartDato());
        TextView slutDato_udlejerTV = v.findViewById(R.id.forhandling_slutdato_udlejer_textview);
        slutDato_udlejerTV.setText(singleton.midlertidigAftale.getEndDato());

        //Arbejdsdage
        TextView arbejdsdage_udlejerTV = v.findViewById(R.id.forhandling_timepris_udlejer_textview);
        arbejdsdage_udlejerTV.setText("MANGLER");

        //Prisen
        TextView timepris_udlejerTV = v.findViewById(R.id.forhandling_timepris_udlejer_textview);
        timepris_udlejerTV.setText(singleton.midlertidigAftale.getPris());
        TextView subtotal_udlejerTV = v.findViewById(R.id.forhandling_subtotalen_excl_flexicuspris_udlejer_textview);
        subtotal_udlejerTV.setText("MANGLER");
        TextView flexicupris_udlejerTV = v.findViewById(R.id.forhandling_flexicu_pris_udlejer_textview);
        flexicupris_udlejerTV.setText("MANGLER");
        TextView totalpris_udlejerTV = v.findViewById(R.id.forhandling_total_pris_udlejer_textview);
        totalpris_udlejerTV.setText("MANGLER");

        //Eget værktøj
        TextView værktøj_udlejerTV = v.findViewById(R.id.forhandling_egetværktøj_udlejer_textview);
        if(singleton.midlertidigAftale.isEgetVærktøj()){
            værktøj_udlejerTV.setText("Ja");
        }
        else {
            værktøj_udlejerTV.setText("Nej");
        }



        presenter = new Lej_presenter(this);

        //Lejer
        //Dato
        startdato_lejerTV = v.findViewById(R.id.forhandling_startdato_lejer_textview);
        startdato_lejerTV.setOnClickListener(this);
        startdato_lejerTV.setText(singleton.midlertidigAftale.getStartDato());
        slutdato_lejerTV = v.findViewById(R.id.forhandling_slutdato_lejer_textview);
        slutdato_lejerTV.setOnClickListener(this);
        slutdato_lejerTV.setText(singleton.midlertidigAftale.getEndDato());
        //slutdato_lejerTV.setEnabled(false);

        //Arbejdsdage
     //   antalArbejdsdage_lejerTV = v.findViewById(R.id.udlejning_redigerbar_antal_arbejdsdage_textview1);
      //  antalArbejdsdage_lejerTV.setText("MANGLER");
        // TODO : udregne antal arbejdsdage og sætte textView til det
        //Pris
        timeprise_lejerET = v.findViewById(R.id.forhandling_timepris_lejer_editview);
        timeprise_lejerET.setText(singleton.midlertidigAftale.getPris());
       // presenter.udregnPriser(timeprise_lejerET.getText().toString(), antalArbejdsdage_lejerTV.getText().toString());
        timeprise_lejerET.addTextChangedListener(prisTextWatch);
        subtotalen_lejerTV = v.findViewById(R.id.forhandling_subtotalen_excl_flexicuspris_lejer_textview);
        flexicugebyr_lejerTV = v.findViewById(R.id.forhandling_flexicu_pris_lejer_textview);
        totalprisen_TV = v.findViewById(R.id.forhandling_total_pris_lejer_textview);
        /*kommentarET = v.findViewById(R.id.udlejning_kommentar_edittext);

        kommentarET.setScroller(new Scroller(getActivity()));
        kommentarET.setMaxLines(3);
        kommentarET.setVerticalScrollBarEnabled(true);
        kommentarET.setMovementMethod(new ScrollingMovementMethod());

        */

        //Eget værktøj
        egetVærktøj_switch = v.findViewById(R.id.forhandling_egetværktøj_lejer_switch);
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
                //TODO : Forbinde til databasen og tilføje til singleton
                break;
            case R.id.forhandling_annuller_button:
                //TODO : popbackstack
                break;
            case R.id.forhandling_tilføj_besked_button:
                onButtonShowPopupWindowClick(v);
        }
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
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, (width-20), height, focusable);
        //popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        //final PopupWindow popupWindowt = new PopupWindow();
        popupWindow.setContentView(popupView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setElevation(20);
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        //  popupWindow.setOutsideTouchable(true);
            /*// dismiss the popup window when touched
            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    return true;
                }
            });*/
        final EditText kommentar = popupView.findViewById(R.id.skriv_kommentar_edittext);

        final Button gem = popupView.findViewById(R.id.gem_kommentar);
        gem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleton.midlertidigAftale.setKommentar(kommentar.getText().toString());
                popupWindow.dismiss();
            }
        });

        ArrayList<Aftale> aftale = new ArrayList<>();
        Aftale af = new Aftale();
        af.setKommentar("Hej");
        af.setUdlejer(singleton.getBruger());
        af.setSidstSendtAftale(singleton.getBruger());
        Aftale af1 = new Aftale();
        af1.setKommentar("Hej igen");
        Bruger br = new Bruger();
        br.setBrugerensNavn("Gunn");
        br.setBrugerID("ldjgsø");
        af1.setUdlejer(singleton.getBruger());
        af1.setSidstSendtAftale(br);

        Aftale af2 = new Aftale();
        af2.setUdlejer(singleton.getBruger());
        af2.setKommentar("Hvad laver du?");
        af2.setSidstSendtAftale(singleton.getBruger());
        aftale.add(af);
        aftale.add(af1);
        aftale.add(af2);


        RecyclerView recyclerView = popupView.findViewById(R.id.forhandling_meddelelser_recyclerview);
        Forhandling_kommentar_recyclerview adapter = new Forhandling_kommentar_recyclerview(getContext(),aftale /*singleton.getMedarbejdereTilUdlejning()*/);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private TextWatcher prisTextWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length()>0){
                presenter.udregnPriser(s.toString(), antalArbejdsdage_lejerTV.getText().toString());
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
    public void opdaterSubtotal(double værdi) {
        timeprise_lejerET.setError(null);
        subtotalen_lejerTV.setText(""+værdi);
    }

    @Override
    public void opdaterFlexicufee(double værdi) {
        flexicugebyr_lejerTV.setText(""+værdi);
    }

    @Override
    public void opdaterTotal(double værdi) {
        totalprisen_TV.setText(""+værdi);
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
        timeprise_lejerET.setText(errorMSG);
    }
}
