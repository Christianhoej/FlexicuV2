package com.example.chris.flexicuv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Button tilStart = (Button) findViewById(R.id.toStartBtn);
        tilStart.setOnClickListener(this);
    }

    public void openStartScreen(){
        Intent intent = new Intent(this, Startskaerm.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        openStartScreen();
    }
}
