package com.example.chris.flexicuv2.Medarbejdere_pakke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;

public class Medarbejdere extends AppCompatActivity implements View.OnClickListener {

    TextView Søren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medarbejdere);

        LinearLayout søren = (LinearLayout) findViewById(R.id.søren_layout);
        søren.setOnClickListener(this);
       /* TextView Søren = (TextView) findViewById(R.id.medarbejder1);
        Søren.setOnClickListener(this);*/
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.søren_layout:
            openMedarbejder();
        }

    }

    private void openMedarbejder() {
        Intent intent = new Intent(this, Medarbejder.class);
        startActivity(intent);
    }
}
