package com.example.chris.flexicuv2.startskærm.indbakke;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.startskærm.indbakke.aftaler.Aftaler_afsluttede_fragment;
import com.example.chris.flexicuv2.startskærm.indbakke.aftaler.Aftaler_forhandlinger_fragment;
import com.example.chris.flexicuv2.startskærm.indbakke.aftaler.Aftaler_nuvaerende_fragment;


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
    private Aftaler_afsluttede_fragment aftaler_afsluttede_fragment;
    private Aftaler_forhandlinger_fragment aftaler_forhandlinger_fragment;
    private Aftaler_nuvaerende_fragment aftaler_nuvaerende_fragment;

    private String senesteFrag;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.indbakke_fragment, container, false);

        //viewPager = (ViewPager) v.findViewById(R.id.indbakke_viewPager);
        //pagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
        //viewPager.setAdapter(pagerAdapter);

        til_aftaler_frame = (FrameLayout) v.findViewById(R.id.til_aftaler_frame);
        aftaler_nuvaerende_fragment = new Aftaler_nuvaerende_fragment();
        aftaler_forhandlinger_fragment = new Aftaler_forhandlinger_fragment();
        aftaler_afsluttede_fragment = new Aftaler_afsluttede_fragment();

        indbakke_nav = (BottomNavigationView) v.findViewById(R.id.indbakke_nav);

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
        //fjernFragmenter1(aftaler_afsluttede_fragment);
        setNavigation();
        setFragment(aftaler_nuvaerende_fragment, "nuværende");
        //indbakke_nav.getMenu().getItem(0).setChecked(true);
        //onNavigationItemSelected(indbakke_nav.getMenu().findItem(R.id.indbakke_nuværende));

        /*indbakke_nav.getMenu().getItem(0).setChecked(true);
        indbakke_nav.setOnNavigationItemSelectedListener(BottomNavigationView);
        onN*/
        //TODO Når indbakke åbnes 2. gang skal det fragment der vises, også være det der highlightes
        return v;
    }


    public void setNavigation(){
        //indbakke_nav.getMenu().getItem(0).setChecked(true);
        indbakke_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //onNavigationItemSelected(indbakke_nav.getMenu().getItem(0));
                //setFragment(aftaler_nuvaerende_fragment, "nuværende");
                switch (menuItem.getItemId()){
                    case R.id.indbakke_afsluttede:
                        if(!(getFragmentManager().findFragmentByTag("afsluttede") != null && getFragmentManager().findFragmentByTag("afsluttede").isVisible())) {
                            senesteFrag = "afsluttede";

                            fjernFragmenter1(aftaler_forhandlinger_fragment);
                            fjernFragmenter1(aftaler_nuvaerende_fragment);
                            setFragment(aftaler_afsluttede_fragment,"afsluttede");

                        }
                        return true;
                    case R.id.indbakke_forhandlinger:
                        if(!(getFragmentManager().findFragmentByTag("forhandlinger") != null && getFragmentManager().findFragmentByTag("forhandlinger").isVisible())) {
                            senesteFrag = "forhandlinger";
                            //fjernFragmenter1();
                            fjernFragmenter1(aftaler_nuvaerende_fragment);
                            fjernFragmenter1(aftaler_nuvaerende_fragment);
                            setFragment(aftaler_forhandlinger_fragment,"forhandlinger" );
                        }
                        return true;
                    case R.id.indbakke_nuværende:
                        if(!(getFragmentManager().findFragmentByTag("nuværende") != null && getFragmentManager().findFragmentByTag("nuværende").isVisible())) {
                            senesteFrag = "nuværende";
                            //fjernFragmenter1();
                            fjernFragmenter1(aftaler_forhandlinger_fragment);
                            fjernFragmenter1(aftaler_afsluttede_fragment);
                            setFragment(aftaler_nuvaerende_fragment, "nuværende");
                        }
                        return true;
                    default:
                        return false;
                }
            }

        });

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
