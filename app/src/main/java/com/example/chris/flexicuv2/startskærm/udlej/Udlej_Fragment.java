package com.example.chris.flexicuv2.startskærm.udlej;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.startskærm.indbakke.aftaler.Aftaler_ledige_fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Udlej_Fragment extends Fragment implements View.OnClickListener{

    private BottomNavigationView indbakke_nav;
    private String senesteFrag;
    private FrameLayout til_udlej_frame;
    private Udlejning_fragment_den_rigtige udlejning_fragment_den_rigtige;
    private Aftaler_ledige_fragment aftaler_ledige_fragment;
    private Udlej_mine_medarbejdere udlej_mine_medarbejdere;
    public Udlej_Fragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        //TODO husk lige at ændre navnet her
        View v = inflater.inflate(R.layout.udlejning_fragment_den_rigtige, container, false);


        indbakke_nav = (BottomNavigationView) v.findViewById(R.id.indbakke_nav);
        til_udlej_frame = (FrameLayout) v.findViewById(R.id.til_udlej_frame);
        udlejning_fragment_den_rigtige = new Udlejning_fragment_den_rigtige();
        aftaler_ledige_fragment = new Aftaler_ledige_fragment();
        udlej_mine_medarbejdere = new Udlej_mine_medarbejdere();




        setNavigation();

        if(senesteFrag=="forhandlinger"){
            setFragment(aftaler_ledige_fragment, "forhandlinger");
        }
        else

        setFragment(udlej_mine_medarbejdere, "ledige");


        return v;
    }

    public void setFragment(android.support.v4.app.Fragment fragment, String tag) {
        til_udlej_frame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.til_udlej_frame, fragment, tag);
        fragmentTransaction.commit();
    }

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

                            //fjernFragmenter1(aftaler_forhandlinger_fragment);
                            setFragment(udlej_mine_medarbejdere,"ledige");

                        }
                        return true;
                    case R.id.indbakke_forhandlinger:
                        if(!(getFragmentManager().findFragmentByTag("forhandlinger") != null && getFragmentManager().findFragmentByTag("forhandlinger").isVisible())) {
                            senesteFrag = "forhandlinger";
                            //fjernFragmenter1();
                            //fjernFragmenter1(ledige_fragment);
                            setFragment(aftaler_ledige_fragment,"forhandlinger" );
                        }
                        return true;
                    default:
                        return false;
                }
            }

        });

    }



    @Override
    public void onClick(View v) {

    }
}
