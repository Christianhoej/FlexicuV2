package com.example.chris.flexicuv2.startskærm.indbakke.aftaler;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.startskærm.indbakke.forhandling.Forhandling_som_lejer;

/**
 * A simple {@link Fragment} subclass.
 */
public class Aftaler_forhandlinger_fragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    public Aftaler_forhandlinger_fragment() {}

    private RecyclerView recyclerViewIndlejninger;
    private RecyclerView recyclerViewUdlejninger;
    private Forhandling_som_lejer_recyclerview_adapter adapter_lej;
    private Forhandling_som_udlejer_recyclerview_adapter adapter_udlej;
    //private Button åben_forhandling;
    private RadioGroup radioGroupForhandlinger;
    private RadioButton radioButtonIndlejninger;
    private RadioButton radioButtonUdlejninger;

    Forhandling_som_lejer forhandlingSomlejerFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.aftaler_forhandlinger_fragment, container, false);

        recyclerViewIndlejninger = (RecyclerView) v.findViewById(R.id.forhandlinger_recyclerview_indlejninger);
        recyclerViewIndlejninger.setOnClickListener(this);
        recyclerViewUdlejninger = (RecyclerView) v.findViewById(R.id.forhandlinger_recyclerview_udlejninger);
        recyclerViewUdlejninger.setOnClickListener(this);

        //åben_forhandling = v.findViewById(R.id.vis_forhandling);
        //åben_forhandling.setOnClickListener(this);

        radioGroupForhandlinger = v.findViewById(R.id.radioGroup_forhandlinger);
        radioGroupForhandlinger.setOnCheckedChangeListener(this);
        radioButtonIndlejninger = v.findViewById(R.id.radio_forhandlinger_indlejninger);
        radioButtonUdlejninger = v.findViewById(R.id.radio_forhandlinger_udlejninger);

        forhandlingSomlejerFragment = new Forhandling_som_lejer();

        fyldRecyclerViewIndlejninger(v);
        fyldRecyclerViewUdlejninger(v);

        radioButtonIndlejninger.setChecked(true);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*case R.id.vis_forhandling:
                setFragment(forhandlingSomlejerFragment);
                break;*/
            /*case R.id.radio_forhandlinger_indlejninger:
                System.out.println("ÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆ");
                break;*/
        }

    }

    private void fyldRecyclerViewIndlejninger(View v){
        //Log.d(TAG, "fyldRecyclerView: Fylder Recyclerview");
        RecyclerView recyclerView = v.findViewById(R.id.forhandlinger_recyclerview_indlejninger);
        adapter_lej = new Forhandling_som_lejer_recyclerview_adapter(getContext());
        recyclerView.setAdapter(adapter_lej);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    //TODO Skal måske have lavet sin egen adapter, det ser vi lige til.
    private void fyldRecyclerViewUdlejninger(View v){
        //Log.d(TAG, "fyldRecyclerView: Fylder Recyclerview");
        RecyclerView recyclerView = v.findViewById(R.id.forhandlinger_recyclerview_udlejninger);
        adapter_udlej= new Forhandling_som_udlejer_recyclerview_adapter(getContext());
        recyclerView.setAdapter(adapter_udlej);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void setFragment(Fragment fragment) {
        //startskærmFrameTilDiverse.removeAllViews();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.startskærm_frame_til_diverse, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (radioGroupForhandlinger.getCheckedRadioButtonId()== R.id.radio_forhandlinger_indlejninger){
            recyclerViewUdlejninger.setVisibility(View.GONE);
            recyclerViewIndlejninger.setVisibility(View.VISIBLE);
        }
        else if(radioGroupForhandlinger.getCheckedRadioButtonId()== R.id.radio_forhandlinger_udlejninger){
            recyclerViewIndlejninger.setVisibility(View.GONE);
            recyclerViewUdlejninger.setVisibility(View.VISIBLE);
        }

    }
}
