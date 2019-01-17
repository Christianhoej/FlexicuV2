package com.example.chris.flexicuv2.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.chris.flexicuv2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Indbakke_fragment extends Fragment {


    public Indbakke_fragment() {
        // Required empty public constructor
    }

    private static final int antal_sider=3;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private BottomNavigationView indbakke_nav;
    private FrameLayout til_aftaler_frame;
    private aftaler_afsluttede_fragment aftaler_afsluttede_fragment;
    private aftaler_forhandlinger_fragment aftaler_forhandlinger_fragment;
    private aftaler_nuvaerende_fragment aftaler_nuvaerende_fragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_indbakke_fragment, container, false);

        //viewPager = (ViewPager) v.findViewById(R.id.indbakke_viewPager);
        //pagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
        //viewPager.setAdapter(pagerAdapter);

        til_aftaler_frame = (FrameLayout) v.findViewById(R.id.til_aftaler_frame);
        aftaler_nuvaerende_fragment = new aftaler_nuvaerende_fragment();
        aftaler_forhandlinger_fragment = new aftaler_forhandlinger_fragment();
        aftaler_afsluttede_fragment = new aftaler_afsluttede_fragment();

        indbakke_nav = (BottomNavigationView) v.findViewById(R.id.indbakke_nav);


        //TODO setfragment - er det den der skal komme, når vi åbner indbakke?
        setFragment(aftaler_nuvaerende_fragment, "nuværende");

        indbakke_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.indbakke_afsluttede:
                        if(!(getFragmentManager().findFragmentByTag("afsluttede") != null && getFragmentManager().findFragmentByTag("afsluttede").isVisible())) {
                            //fjernFragmenter();
                            setFragment(aftaler_afsluttede_fragment,"afsluttede" );
                        }
                        return true;
                    case R.id.indbakke_forhandlinger:
                        if(!(getFragmentManager().findFragmentByTag("forhandlinger") != null && getFragmentManager().findFragmentByTag("forhandlinger").isVisible())) {
                            //fjernFragmenter();
                            setFragment(aftaler_forhandlinger_fragment,"forhandlinger" );
                        }
                        return true;
                    case R.id.indbakke_nuværende:
                        if(!(getFragmentManager().findFragmentByTag("nuværende") != null && getFragmentManager().findFragmentByTag("nuværende").isVisible())) {
                            //fjernFragmenter();
                            setFragment(aftaler_nuvaerende_fragment, "nuværende");
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });



        return v;
    }

    public void fjernFragmenter(){
        for(Fragment fragment : getFragmentManager().getFragments()){
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    public void setFragment(android.support.v4.app.Fragment fragment, String tag) {
        til_aftaler_frame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.til_aftaler_frame, fragment, tag);
        fragmentTransaction.commit();
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
