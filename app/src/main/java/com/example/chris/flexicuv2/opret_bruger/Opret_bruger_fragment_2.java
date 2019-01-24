package com.example.chris.flexicuv2.opret_bruger;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.login.LoginScreen_akt;
import com.example.chris.flexicuv2.model.Bruger;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Opret_bruger_fragment_2 extends Fragment implements Opret_bruger_Presenter_Frag2.UpdateNewUser_Frag2, View.OnClickListener{

    private EditText username, usernameRepeated;
    private EditText password, passwordRepeated;
    private RadioGroup radioOptions;
    private RadioButton checkPrivate, checkPublic;
    private CheckBox checkFormalities;
    private Button createUserBtn2;
    private Button cancelBtn2;
    private Bruger virksomhed;
    private DatabaseReference mDatabase;
    private Bruger bruger;
    private ImageButton qmark1;

    Opret_bruger_fragment_1 new_user_fragment_1;

    Opret_bruger_Presenter_Frag2 presenter;
    private HashMap<Integer, String> zipToCity = new HashMap<>();

    public Opret_bruger_fragment_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_user_fragment_2, container, false);


        presenter = new Opret_bruger_Presenter_Frag2(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        virksomhed = new Bruger();
        bruger = new Bruger();

        qmark1 = v.findViewById(R.id.qmark);
        qmark1.setOnClickListener(this);
        username = v.findViewById(R.id.logOnEmail);
        usernameRepeated = v.findViewById(R.id.logOnEmailRepeat);

        password = v.findViewById(R.id.logOnPassword);

        passwordRepeated = v.findViewById(R.id.logOnPasswordRepeat);

        radioOptions = v.findViewById(R.id.radio_options);
        checkPrivate = v.findViewById(R.id.checkPrivate);

        checkPublic = v.findViewById(R.id.checkPublic);
        checkFormalities = v.findViewById(R.id.checkFormalities);

        createUserBtn2 = (Button) v.findViewById(R.id.createUserBtn2);
        createUserBtn2.setOnClickListener(this);
        cancelBtn2 = v.findViewById(R.id.cancelBtn2);
        cancelBtn2.setOnClickListener(this);

        presenter.udfyldFelter();


        return v;
    }


    @Override
    public void onClick(View v) {
/*
        qmark1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLL");
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://portal.flexicu.com/betingelser/"));
                startActivity(i);
            }

        });
*/
        int selectedId = radioOptions.getCheckedRadioButtonId();

        if(selectedId == R.id.checkPrivate)
            selectedId = 1;
        if(selectedId == R.id.checkPublic)
            selectedId = 2;

        if (v.getId() == R.id.createUserBtn2){
            if(presenter.korrektudfyldtInformation(
                    username.getText().toString(),
                    usernameRepeated.getText().toString(),
                    password.getText().toString(),
                    passwordRepeated.getText().toString(),
                    selectedId, checkFormalities.isChecked(),
                    getContext())){
            }

        }
        if(v.getId() == R.id.cancelBtn2){
            getActivity().onBackPressed();
            presenter.setMidlertidigBruger(username.getText().toString(), selectedId);
        }

        if(v.getId()==R.id.qmark){
            Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://portal.flexicu.com/betingelser/"));
            startActivity(i);
        }
    }

    public boolean isEmpty(EditText text){
        return TextUtils.isEmpty(text.getText().toString());
    }

    public void openLoginScreen(){
        Intent intent = new Intent(getActivity(), LoginScreen_akt.class);
        startActivity(intent);
    }

    @Override
    public void newUserSuccess(String displayedMessage, boolean success) {

        if(success){
            Toast.makeText(getContext(), displayedMessage,
                    Toast.LENGTH_LONG).show();
            getActivity().finish();
        }
    }

    @Override
    public void newUserFailed(String displayedMessage) {
        Toast.makeText(getContext(), displayedMessage,
                Toast.LENGTH_LONG).show();
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

    @Override
    public void setEmail(String email) {
        username.setText(email);
    }

    /*@Override
    public void setKodeord(String kodeord) {

    }*/

    @Override
    public void setPrivatoplysninger(int privatoplysninger) {
        if(privatoplysninger==1){
            checkPrivate.setChecked(true);
        }
        else{
            checkPublic.setChecked(true);
        }
    }

}
