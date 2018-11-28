package com.example.chris.flexicuv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chris.flexicuv2.model.Bruger;
import com.example.chris.flexicuv2.model.Virksomhed;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class NewUser_akt extends AppCompatActivity implements View.OnClickListener{
    private EditText companyCVR, companyName, companyAddress, companyZipCode;
    private TextView companyCity;
    private EditText contactName, contactEmail, contactPhone, contactTitle;
    private EditText username, usernameRepeated;
    private EditText password, passwordRepeated;
    private CheckBox checkPrivate, checkPublic;
    private CheckBox checkFormalities;
    private Button createUserBtn;
    private Button cancelBtn;
    private Virksomhed virksomhed;
    private DatabaseReference mDatabase;
    private Bruger bruger;

    private HashMap<Integer, String> zipToCity = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_v2);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        virksomhed = new Virksomhed();
        bruger = new Bruger();

        companyCVR = findViewById(R.id.companyCVR);
        companyCVR.setHint("CVR");
        companyName = findViewById(R.id.companyName);
        companyName.setHint("Virksomhedsnavn");
        companyAddress = findViewById(R.id.companyAddress);
        companyAddress.setHint("Adresse");
        companyZipCode = findViewById(R.id.companyZipCode);
        companyZipCode.setHint("Postnr");

        companyCity = findViewById(R.id.companyCity);
        companyCity.setHint("By");

        contactName = findViewById(R.id.contactName);
        contactName.setHint("Fulde navn");
        contactEmail = findViewById(R.id.contactEmail);
        contactEmail.setHint("E-mail");
        contactPhone = findViewById(R.id.contactPhone);
        contactPhone.setHint("Tlf");
        contactTitle = findViewById(R.id.contactTitle);
        contactTitle.setHint("Tittel");

        username = findViewById(R.id.logOnEmail);
        username.setHint("E-mail");
        usernameRepeated = findViewById(R.id.logOnEmailRepeat);
        usernameRepeated.setHint("Gentag e-mail");
        password = findViewById(R.id.logOnPassword);
        password.setHint("Kodeord");
        passwordRepeated = findViewById(R.id.logOnPasswordRepeat);
        passwordRepeated.setHint("Gentag kodeord");

        checkPrivate = findViewById(R.id.checkPrivate);
        checkPrivate.setText("Privat");
        checkPublic = findViewById(R.id.checkPublic);
        checkPublic.setText("Offentlig");
        checkFormalities = findViewById(R.id.checkFormalities);
        //checkFormalities.setText("JA, jeg har læst, forstået og accepterer betingelserne");

        createUserBtn = findViewById(R.id.createUserBtn);
        createUserBtn.setOnClickListener(this);
        createUserBtn.setText("Opret bruger");

        cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);
        cancelBtn.setText("Annuller");


    }


    @Override
    public void onClick(View v) {
        if(v.getId() == v.getId()) {
            virksomhed.setVirksomhedsnavn(companyName.getText().toString());
            virksomhed.setAdresse(companyAddress.getText().toString());
            virksomhed.setPostnr(companyZipCode.getText().toString());
            virksomhed.setVirksomhedCVR(companyCVR.getText().toString());



            bruger.setAdmin(true);
            bruger.setNavn(contactName.getText().toString());
            bruger.setEmail(contactEmail.getText().toString());
            bruger.setTitel(contactTitle.getText().toString());
            bruger.setTlfnr(contactPhone.getText().toString());

            virksomhed.addBruger(bruger);


            mDatabase.child("virksomhed").child(virksomhed.getVirksomhedCVR()).setValue(virksomhed);


            DatabaseReference dbBruger = FirebaseDatabase.getInstance().getReference();

            int brugerindex = virksomhed.getBrugere().indexOf(bruger);

            String key = dbBruger.child("bruger").push().getKey();
            dbBruger.child("bruger").child(key).setValue(bruger);
            dbBruger.child("bruger").child(key).child("virksomhed").child(virksomhed.getVirksomhedCVR()).setValue(true);

            bruger.setBrugerKey("Key = " + dbBruger.getKey());
            System.out.println(bruger.getBrugerKey());
            openLoginScreen();
        }
    }

    public boolean isEmpty(EditText text){
        return TextUtils.isEmpty(text.getText().toString());
    }

    public void openLoginScreen(){
        Intent intent = new Intent(this, LoginScreen_akt.class);
        startActivity(intent);
    }
}
