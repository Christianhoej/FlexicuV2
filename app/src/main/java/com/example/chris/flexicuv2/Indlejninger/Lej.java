package com.example.chris.flexicuv2.Indlejninger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.chris.flexicuv2.R;

public class Lej extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout filtrerFrame;
    private Lej_filtrer filtrerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lej);

        filtrerFrame = (FrameLayout) findViewById(R.id.filtrer_frame);

        Button filtrer = (Button) findViewById(R.id.filtrer_knap);
        filtrer.setOnClickListener(this);



        filtrerFragment = new Lej_filtrer();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


    @Override
    public void onClick(View v) {
        setFragment(filtrerFragment);
    }


    public void setFragment(android.support.v4.app.Fragment fragment) {
        filtrerFrame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.filtrer_frame, fragment);
        fragmentTransaction.commit();
    }
}
