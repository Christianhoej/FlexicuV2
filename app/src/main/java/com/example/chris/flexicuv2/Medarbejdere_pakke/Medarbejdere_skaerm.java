package com.example.chris.flexicuv2.Medarbejdere_pakke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;

public class Medarbejdere_skaerm extends AppCompatActivity implements View.OnClickListener {

    TextView Søren;
    Button opretMedarbejdereKnap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medarbejdere_skaerm);

        LinearLayout søren = (LinearLayout) findViewById(R.id.søren_layout);
        søren.setOnClickListener(this);

        opretMedarbejdereKnap = (Button) findViewById(R.id.opret_medarbejder_knap);
        opretMedarbejdereKnap.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.opret_medarbejder_knap:
            openMedarbejder();
        }
    }

    private void openMedarbejder() {
        Intent intent = new Intent(this, opret_medarbejder.class);
        startActivity(intent);
    }
}
