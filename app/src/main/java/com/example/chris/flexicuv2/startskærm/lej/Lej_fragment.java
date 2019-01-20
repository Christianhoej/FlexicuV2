package com.example.chris.flexicuv2.startskærm.lej;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import com.example.chris.flexicuv2.database.DBManager;
import com.example.chris.flexicuv2.R;

//import com.example.chris.flexicuv2.Startskaerm;
import com.example.chris.flexicuv2.model.Singleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Lej_fragment extends Fragment implements View.OnClickListener {


    public Lej_fragment() {
        // Required empty public constructor
    }

    Lej_filtrer_fragment filtrer_fragment;
    FrameLayout lej_frame;
    private RecyclerView recyclerView;
    private DBManager test;
    private Singleton singleton;
    private Button filtrer, opretSøgeagent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.lej_fragment, container, false);

        singleton = Singleton.getInstance();
        filtrer_fragment = new Lej_filtrer_fragment();
        lej_frame = (FrameLayout) v.findViewById(R.id.lej_fragment_frame);

        filtrer = (Button) v.findViewById(R.id.filtrer_knap);
        filtrer.setOnClickListener(this);
        opretSøgeagent = v.findViewById(R.id.søgeagent);
        opretSøgeagent.setOnClickListener(this);

        recyclerView = v.findViewById(R.id.ledig_arbejdskraft_recyclerview);

        //TODO nødvendig?
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
        // Inflate the layout for this fragment

        RecyclerViewAdapter_Ledig_Arbejdskraft mAdapter = new RecyclerViewAdapter_Ledig_Arbejdskraft(getContext());

        recyclerView.setAdapter(mAdapter);
        //setUpspinner(v);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.filtrer_knap:
                setFragment(filtrer_fragment);
                break;
            case R.id.søgeagent:
                Toast.makeText(getContext(), "Dette er ikke lavet endnu",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }



    public void setFragment(Fragment fragment) {
        //startskærmFrameTilDiverse.removeAllViews();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.startskærm_frame_til_diverse, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }


    /**
     * Metode til at vise et popupvindue af medarbejderen, med flere informationer om medarbejderen.
     * Udarbejdet med inspiration fra:
     * https://stackoverflow.com/questions/5944987/how-to-create-a-popup-window-popupwindow-in-android
     * @param view
     */
    public void onButtonShowPopupWindowClick1(View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.lej_filtrer_fragment, null);

        // create the popup window
        int width = 1000;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
}
