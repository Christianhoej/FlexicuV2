package com.example.chris.flexicuv2.startskærm.indbakke;

/**
 * @Author Christian
 */
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.startskærm.indbakke.aftaler.Aftaler_ledige_fragment;
import com.example.chris.flexicuv2.startskærm.indbakke.aftaler.Forhandling_recyclerview;


/**
 * A simple {@link Fragment} subclass.
 */
public class Indbakke_fragment extends Fragment implements View.OnClickListener {


    public Indbakke_fragment() {
        // Required empty public constructor
    }

    private static final int antal_sider=3;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private BottomNavigationView indbakke_nav;
    private FrameLayout til_aftaler_frame;
    private Aftaler_ledige_fragment ledige_fragment;


    private String senesteFrag;


    private RecyclerView recyclerViewIndlejninger;
    private Forhandling_recyclerview adapter_lej;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.indbakke_fragment, container, false);



        recyclerViewIndlejninger = (RecyclerView) v.findViewById(R.id.forhandlinger_recyclerview_indlejninger);
        recyclerViewIndlejninger.setOnClickListener(this);


        fyldRecyclerViewIndlejninger(v);


        return v;
    }



    public void fjernFragmenter(){
        for(Fragment fragment : getFragmentManager().getFragments()){
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    public void fjernFragmenter1(android.support.v4.app.Fragment fragment){
        getFragmentManager().beginTransaction().remove(fragment).commit();
    }

    public void setFragment(android.support.v4.app.Fragment fragment, String tag) {
        til_aftaler_frame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.til_udlej_frame, fragment, tag);
        fragmentTransaction.commit();
    }

    private void fyldRecyclerViewIndlejninger(View v){
        //Log.d(TAG, "fyldRecyclerView: Fylder Recyclerview");
        RecyclerView recyclerView = v.findViewById(R.id.forhandlinger_recyclerview_indlejninger);
        adapter_lej = new Forhandling_recyclerview(getContext());
        recyclerView.setAdapter(adapter_lej);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClick(View v) {

    }
}
