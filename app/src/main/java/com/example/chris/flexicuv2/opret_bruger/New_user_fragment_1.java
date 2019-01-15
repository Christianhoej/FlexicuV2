package com.example.chris.flexicuv2.opret_bruger;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
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


public class New_user_fragment_1 extends Fragment implements NewUser_Presenter_Frag1.UpdateNewUser_Frag1, View.OnClickListener {

    public New_user_fragment_1() {
        // Required empty public constructor
    }

    private EditText companyCVR, companyName, companyAddress, companyZipCode;
    private TextView companyCity;
    private EditText contactName, contactPhone, contactTitle;
    private Bruger virksomhed;
    private DatabaseReference mDatabase;
    private Bruger bruger;
    private Button createUserBtn, cancelBtn;
    private FrameLayout loginFrame;
    private New_user_fragment_2 new_user_fragment_2;
    LoginScreen_akt loginScreen_akt;

    NewUser_Presenter_Frag1 presenter;

    private HashMap<Integer, String> zipToCity = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_user_fragment_1, container, false);

        presenter = new NewUser_Presenter_Frag1(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        virksomhed = new Bruger();
        bruger = new Bruger();

        companyCVR = v.findViewById(R.id.companyCVR);
        companyCVR.addTextChangedListener(CVRtextWatch);
        companyName = v.findViewById(R.id.companyName);
        companyAddress = v.findViewById(R.id.companyAddress);
        companyZipCode = v.findViewById(R.id.companyZipCode);

        companyCity = v.findViewById(R.id.companyCity);

        contactName = v.findViewById(R.id.contactName);
        contactName.setText("Gunn");
        contactPhone = v.findViewById(R.id.contactPhone);
        contactPhone.setText("11111111");
        contactTitle = v.findViewById(R.id.contactTitle);
        contactTitle.setText("CEO");

        createUserBtn = v.findViewById(R.id.createUserBtn);
        createUserBtn.setOnClickListener(this);
        cancelBtn = v.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);

        loginFrame = v.findViewById(R.id.login_frame);
        new_user_fragment_2 = new New_user_fragment_2();
        loginScreen_akt = new LoginScreen_akt();

        return v;
    }
    public boolean isEmpty(EditText text) {
        return TextUtils.isEmpty(text.getText().toString());
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

    //Afsnit der "læser på ændring i edittexten for CVR"
    private TextWatcher CVRtextWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 8) {
                presenter.hentVirksomhedsoplysninger(s.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelBtn:
                //getActivity().finish();
                getActivity().onBackPressed();
                break;
            case R.id.createUserBtn:
                if (presenter.korrektudfyldtInformation(
                        companyCVR.getText().toString(),
                        companyName.getText().toString(),
                        companyAddress.getText().toString(),
                        companyZipCode.getText().toString(),
                        companyCity.getText().toString(),
                        contactName.getText().toString(),
                        contactPhone.getText().toString(),
                        contactTitle.getText().toString(),
                        getContext())) {
                    //TODO setFragment
                    setFragment(new_user_fragment_2);
                }
                else
                    //TODO der skal laves en fejlmeddelse
                    Toast.makeText(getContext(), "Indtast venligst alle oplysningerne", Toast.LENGTH_LONG).show();
                break;
        }
    }
    public void setFragment(android.support.v4.app.Fragment fragment) {
        //loginFrame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.tilFragmenter_frame, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }
}
