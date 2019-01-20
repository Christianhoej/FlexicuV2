package com.example.chris.flexicuv2.startskærm.indbakke.forhandling;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.startskærm.indbakke.aftaler.Forhandling_som_lejer_recyclerview_adapter;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class Forhandling_som_lejer extends Fragment implements View.OnClickListener {

    private Forhandling_som_lejer_recyclerview_adapter adapter;
    private Calendar c;
    private DatePickerDialog.OnDateSetListener datepickerListener;

    private TextView startdatoET, slutdatoET, timepris;
    public Forhandling_som_lejer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.forhandling_som_lejer_fragment, container, false);

        startdatoET = v.findViewById(R.id.forhandling_som_lejer_startdato_lejer_textview);
        startdatoET.setOnClickListener(this);
        slutdatoET = v.findViewById(R.id.forhandling_som_lejer_slutdato_lejer_textview);
        slutdatoET.setOnClickListener(this);
        c = Calendar.getInstance();
        timepris = v.findViewById(R.id.forhandling_som_lejer_timepris_lejer_editview);

        //fyldRecyclerView(v);
    return v;
    }
/*
    private void fyldRecyclerView(View v){
        Log.d(TAG, "fyldRecyclerView: Fylder Recyclerview");
        RecyclerView recyclerView = v.findViewById(R.id.forhandlinger_recyclerview);
        adapter = new Forhandling_som_lejer_recyclerview_adapter(getContext()/*, navneTest, arbejdsområderTest*/    //);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.forhandling_som_lejer_slutdato_lejer_textview:
                findEnDato(false);
                break;
            case R.id.forhandling_som_lejer_startdato_lejer_textview:
                findEnDato(true);
                break;
        }

    }

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


}
