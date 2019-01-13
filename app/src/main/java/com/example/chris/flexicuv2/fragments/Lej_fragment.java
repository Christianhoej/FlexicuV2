package com.example.chris.flexicuv2.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.chris.flexicuv2.DBManager;
import com.example.chris.flexicuv2.Indlejninger.Lej_filtrer;
import com.example.chris.flexicuv2.Indlejninger.MultiSelectionSpinner;
import com.example.chris.flexicuv2.R;

//import com.example.chris.flexicuv2.Startskaerm;
import com.example.chris.flexicuv2.model.Singleton;

import com.example.chris.flexicuv2.model.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Lej_fragment extends Fragment implements View.OnClickListener {


    public Lej_fragment() {
        // Required empty public constructor
    }

    Lej_filtrer filtrer_fragment;
    FrameLayout lej_frame;
    private RecyclerView recyclerView;
    private DBManager test;
    private Singleton singleton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lej_fragment, container, false);

        singleton = Singleton.getInstance();
        filtrer_fragment = new Lej_filtrer();
        lej_frame = (FrameLayout) v.findViewById(R.id.lej_fragment_frame);

        Button filtrer = (Button) v.findViewById(R.id.filtrer_knap);
        filtrer.setOnClickListener(this);

        recyclerView = v.findViewById(R.id.ledig_arbejdskraft_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
        // Inflate the layout for this fragment

        RecyclerViewAdapter_Ledig_Arbejdskraft mAdapter = new RecyclerViewAdapter_Ledig_Arbejdskraft(getContext(), singleton.getMedarbejdere());

        recyclerView.setAdapter(mAdapter);
        //setUpspinner(v);

        return v;
    }

    @Override
    public void onClick(View v) {
        //setFragment(filtrer_fragment);
        onButtonShowPopupWindowClick1(v);
    }



    public void setFragment(Fragment fragment) {
        lej_frame.removeAllViews();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.lej_fragment_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test = new DBManager();
    }

/*
    public void setUpspinner(View v){
        MultiSelectionSpinner spinner=(MultiSelectionSpinner) v.findViewById(R.id.input1);

        List<String> list = new ArrayList<String>();
        list.add("Vælg arbejdsområde");
        list.add("Håndværker");
        list.add("Smed");
        list.add("Lagermand");
        list.add("Elektriker");
        spinner.setItems(list);
    }*/
    /**
     * Metode til at vise et popupvindue af medarbejderen, med flere informationer om medarbejderen.
     * Udarbejdet med inspiration fra:
     * https://stackoverflow.com/questions/5944987/how-to-create-a-popup-window-popupwindow-in-android
     * @param view
     */
    public void onButtonShowPopupWindowClick1(View view) {
        //tilPopUp = (View) findViewById(R.id.tilPopUp);
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.fragment_lej_filtrer, null);

        // create the popup window
        int width = 1000;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        /*// dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });*/
    }
}
