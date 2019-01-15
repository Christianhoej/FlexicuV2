package com.example.chris.flexicuv2.opret_bruger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chris.flexicuv2.R;

public class TilFragmenter extends AppCompatActivity{

    New_user_fragment_1 new_user_fragment_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_til_fragmenter_opretBruger);
        new_user_fragment_1 = new New_user_fragment_1();

        setFragment(new_user_fragment_1);
    }
    public void setFragment(android.support.v4.app.Fragment fragment) {
        //loginFrame.setVisibility(View.INVISIBLE);
        //loginFrame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.tilFragmenter_frame, fragment);

        //fragmentTransaction.addToBackStack(null);
        //this.getSupportFragmentManager().popBackStack();
        //fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }
    /*@Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //openloginScreen();
            //getSupportFragmentManager().popBackStackImmediate();
            this.getSupportFragmentManager().popBackStack();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
*/
}
