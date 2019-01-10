
package com.example.chris.flexicuv2.opret_bruger;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.login.LoginScreen_akt;

import com.example.chris.flexicuv2.model.Bruger;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class NewUser_akt extends AppCompatActivity implements View.OnClickListener, NewUser_Presenter.UpdateNewUser {

    private EditText companyCVR, companyName, companyAddress, companyZipCode;
    private TextView companyCity;
    private EditText contactName, contactEmail, contactPhone, contactTitle;
    private EditText username, usernameRepeated;
    private EditText password, passwordRepeated;
    private RadioGroup radioOptions;
    private RadioButton checkPrivate, checkPublic;
    private CheckBox checkFormalities;
    private Button createUserBtn;
    private Button cancelBtn;
    private Bruger virksomhed;
    private DatabaseReference mDatabase;
    private Bruger bruger;

    NewUser_Presenter presenter;

    private HashMap<Integer, String> zipToCity = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_v2);

        presenter = new NewUser_Presenter(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        virksomhed = new Bruger();
        bruger = new Bruger();

        companyCVR = findViewById(R.id.companyCVR);
        companyCVR.addTextChangedListener(CVRtextWatch);
        companyName = findViewById(R.id.companyName);
        companyAddress = findViewById(R.id.companyAddress);
        companyZipCode = findViewById(R.id.companyZipCode);

        companyCity = findViewById(R.id.companyCity);

        contactName = findViewById(R.id.contactName);
        contactPhone = findViewById(R.id.contactPhone);
        contactTitle = findViewById(R.id.contactTitle);

        username = findViewById(R.id.logOnEmail);
        usernameRepeated = findViewById(R.id.logOnEmailRepeat);
        password = findViewById(R.id.logOnPassword);
        passwordRepeated = findViewById(R.id.logOnPasswordRepeat);

        radioOptions = findViewById(R.id.radio_options);
        checkPrivate = findViewById(R.id.checkPrivate);

        checkPublic = findViewById(R.id.checkPublic);
        checkFormalities = findViewById(R.id.checkFormalities);

        createUserBtn = findViewById(R.id.createUserBtn);
        createUserBtn.setOnClickListener(this);

        cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        int selectedId = radioOptions.getCheckedRadioButtonId();

        if(selectedId == R.id.checkPrivate)
            selectedId = 1;
        if(selectedId == R.id.checkPublic)
            selectedId = 2;

        if (v.getId() == R.id.createUserBtn){
            presenter.korrektudfyldtInformation(
                    companyCVR.getText().toString(),
                    companyName.getText().toString(),
                    companyAddress.getText().toString(),
                    companyZipCode.getText().toString(),
                    companyCity.getText().toString(),
                    contactName.getText().toString(),
                    contactPhone.getText().toString(),
                    contactTitle.getText().toString(),
                    username.getText().toString(),
                    usernameRepeated.getText().toString(),
                    password.getText().toString(),
                    passwordRepeated.getText().toString(),
                    selectedId,
                    checkFormalities.isChecked());

        }
        else{

            finish();
        }
    }

    public boolean isEmpty(EditText text){
        return TextUtils.isEmpty(text.getText().toString());
    }

    public void openLoginScreen(){
        Intent intent = new Intent(this, LoginScreen_akt.class);
        startActivity(intent);
    }

    @Override
    public void updateVirksomhedsNavn(String vsh_navn) {
    companyName.setText(vsh_navn);
    }

    @Override
    public void updateAdresse(String adresse) {
        companyAddress.setText(adresse);
    }

    @Override
    public void updatePostNr(String postNr) {
    companyZipCode.setText(postNr);
    }

    @Override
    public void updateBy(String by) {
    companyCity.setText(by);
    }

    @Override
    public void toastTilBrugerOprettet(String displayedMessage) {
        Toast.makeText(this, displayedMessage,
                Toast.LENGTH_LONG).show();    }

    @Override
    public void errorCVR(String errorMsg) {
    companyCVR.setError(errorMsg);
    }

    @Override
    public void errorVirksomhedsnavn(String errorMsg) {
        companyName.setError(errorMsg);
    }

    @Override
    public void errorAdresse(String errorMsg) {
    companyAddress.setError(errorMsg);
    }

    @Override
    public void errorBy(String errorMsg) {
    companyCity.setError(errorMsg);
    }

    @Override
    public void errorPostnr(String errorMsg) {
        companyZipCode.setError(errorMsg);

    }

    @Override
    public void errorNavn(String errorMsg) {
        contactName.setError(errorMsg);
    }

    @Override
    public void errorTlf(String errorMsg) {
        contactPhone.setError(errorMsg);
    }

    @Override
    public void errorTitel(String errorMsg) {
    contactTitle.setError(errorMsg);
    }

    @Override
    public void errorEmailForm(String errorMsg) {
        username.setError(errorMsg);
    }

    @Override
    public void errorEmailMatches(String errorMsg) {
        usernameRepeated.setError(errorMsg);
    }

    @Override
    public void errorPasswordLength(String errorMsg) {
        password.setError(errorMsg);
    }

    @Override
    public void errorPasswordMatches(String errorMsg) {
        passwordRepeated.setError(errorMsg);
    }

    @Override
    public void errorPrivatoplysninger(String errorMsg) {
        checkPublic.setError(errorMsg);
    }

    @Override
    public void errorAcceptTerms(String errorMsg) {
        checkFormalities.setError(errorMsg);
    }

    //Afsnit der "læser på ændring i edittexten for CVR"
    private TextWatcher CVRtextWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length()==8){
            presenter.hentVirksomhedsoplysninger(s.toString());

        }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
