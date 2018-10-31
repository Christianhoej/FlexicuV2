package com.example.chris.flexicuv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class NewUserScreen extends AppCompatActivity {

    EditText companyCVR;
    EditText companyName;
    EditText companyAddress;
    EditText companyZipCode;
    EditText companyCity;
    EditText contactName;
    EditText contactAddress;
    EditText contactEmail;
    EditText contactTitle;
    EditText username;
    EditText usernameRepeated;
    EditText password;
    EditText passwordRepeated;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_screen);

        companyCVR = findViewById(R.id.companyCVR);
        companyCVR.setHint("CVR");

        companyName = findViewById(R.id.companyName);

    }
}
