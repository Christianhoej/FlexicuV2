package com.example.chris.flexicuv2.opret_bruger;
/**
 * @Author christian
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chris.flexicuv2.R;

public class Opret_bruger_akt extends AppCompatActivity{

    Opret_bruger_fragment_1 new_user_fragment_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opret_bruger_akt);
        new_user_fragment_1 = new Opret_bruger_fragment_1();

        setFragment(new_user_fragment_1);
    }
    public void setFragment(android.support.v4.app.Fragment fragment) {

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.tilFragmenter_frame, fragment);

        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();

            this.getSupportFragmentManager().popBackStack();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

}
