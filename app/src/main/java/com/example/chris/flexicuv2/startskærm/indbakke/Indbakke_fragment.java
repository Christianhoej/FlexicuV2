package com.example.chris.flexicuv2.startskærm.indbakke;


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
import com.example.chris.flexicuv2.startskærm.indbakke.aftaler.Aftaler_forhandlinger_fragment;
import com.example.chris.flexicuv2.startskærm.indbakke.aftaler.Forhandling_som_lejer_recyclerview_adapter;


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
    private Aftaler_forhandlinger_fragment aftaler_forhandlinger_fragment;


    private String senesteFrag;


    private RecyclerView recyclerViewIndlejninger;
    private Forhandling_som_lejer_recyclerview_adapter adapter_lej;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.indbakke_fragment, container, false);



        recyclerViewIndlejninger = (RecyclerView) v.findViewById(R.id.forhandlinger_recyclerview_indlejninger);
        recyclerViewIndlejninger.setOnClickListener(this);


        fyldRecyclerViewIndlejninger(v);

        //viewPager = (ViewPager) v.findViewById(R.id.indbakke_viewPager);
        //pagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
        //viewPager.setAdapter(pagerAdapter);

        /*til_aftaler_frame = (FrameLayout) v.findViewById(R.id.til_aftaler_frame);

        aftaler_forhandlinger_fragment = new Aftaler_forhandlinger_fragment();
        ledige_fragment = new Aftaler_ledige_fragment();

        indbakke_nav = (BottomNavigationView) v.findViewById(R.id.indbakke_nav);*/

        //senesteFrag = "nuværende";
        //setFragment(getFragmentManager().findFragmentByTag(senesteFrag), senesteFrag);
        //TODO setfragment - er det den der skal komme, når vi åbner indbakke?
        /*if (senesteFrag==null){
            setFragment(aftaler_nuvaerende_fragment, "nuværende");
        }
        else {
            setFragment(getFragmentManager().findFragmentByTag(senesteFrag), senesteFrag);
        }*/

        //fjernFragmenter1(aftaler_forhandlinger_fragment);
        //fjernFragmenter1(aftaler_ledige_fragment);
        //setNavigation();


/*
        if(senesteFrag=="ledige"){
            setFragment(ledige_fragment, "ledige");
        }
        else
            setFragment(aftaler_forhandlinger_fragment, "forhandlinger");



        TextView udbud = indbakke_nav.findViewById(R.id.indbakke_forhandlinger).findViewById(R.id.largeLabel);
       // udbud.setTextSize(30);


        TextView bud = indbakke_nav.findViewById(R.id.indbakke_ledige).findViewById(R.id.largeLabel);
       // bud.setTextSize(30);

        //indbakke_nav.getMenu().getItem(0).setChecked(true);
        //onNavigationItemSelected(indbakke_nav.getMenu().findItem(R.id.indbakke_nuværende));

        /*indbakke_nav.getMenu().getItem(0).setChecked(true);
        indbakke_nav.setOnNavigationItemSelectedListener(BottomNavigationView);
        onN*/
        //TODO Når indbakke åbnes 2. gang skal det fragment der vises, også være det der highlightes
        return v;
    }

/*
    public void setNavigation(){
        //indbakke_nav.getMenu().getItem(0).setChecked(true);

        indbakke_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //onNavigationItemSelected(indbakke_nav.getMenu().getItem(0));
                //setFragment(aftaler_nuvaerende_fragment, "nuværende");
                switch (menuItem.getItemId()){
                    case R.id.indbakke_ledige:
                        if(!(getFragmentManager().findFragmentByTag("ledige") != null && getFragmentManager().findFragmentByTag("ledige").isVisible())) {
                            senesteFrag = "ledige";

                            fjernFragmenter1(aftaler_forhandlinger_fragment);
                            setFragment(ledige_fragment,"ledige");

                        }
                        return true;
                    case R.id.indbakke_forhandlinger:
                        if(!(getFragmentManager().findFragmentByTag("forhandlinger") != null && getFragmentManager().findFragmentByTag("forhandlinger").isVisible())) {
                            senesteFrag = "forhandlinger";
                            //fjernFragmenter1();
                            fjernFragmenter1(ledige_fragment);
                            setFragment(aftaler_forhandlinger_fragment,"forhandlinger" );
                        }
                        return true;
                    default:
                        return false;
                }
            }

        });

    }
*/


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
        adapter_lej = new Forhandling_som_lejer_recyclerview_adapter(getContext());
        recyclerView.setAdapter(adapter_lej);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClick(View v) {

    }
/*
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new testIndbakke_fragment();
        }

        @Override
        public int getCount() {
            return antal_sider;
        }
    }
*/


}
