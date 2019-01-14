package com.example.chris.flexicuv2.Medarbejdere_pakke;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */public class opret_medarbejder_fragment_1 extends Fragment implements View.OnClickListener, Opret_Medarbejdere_Fragment1_Presenter.UpdateOpretMedarbejderFrag, RadioGroup.OnCheckedChangeListener {

    public opret_medarbejder_fragment_1() {
    }

    private Spinner fødselsår_spinner;
    private EditText medarbejder_navn;
    private FrameLayout medarbejdereFrame;
    private opret_medarbejder_fragment_2 opretMedarbejderFragment2;
    private Button næste_medarbejdere;
    private Button tilbage1;

    private RadioGroup radioGroupKoen;

    private RadioButton radio_mand;
    private RadioButton radio_kvinde;
    opretMedarbejder_presenter opretMedarbejder_presenter;

    private Opret_Medarbejdere_Fragment1_Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new Opret_Medarbejdere_Fragment1_Presenter(this);
        View v = inflater.inflate(R.layout.opret_medarbejder_fragment_1, container, false);

        radioGroupKoen = v.findViewById(R.id.radio_køn);
        radioGroupKoen.setOnCheckedChangeListener(this);

        fødselsår_spinner = v.findViewById(R.id.fødselsår);
        medarbejder_navn =  v.findViewById(R.id.editText_navn);
        //Bemærk denne er anderledes fra sidste side
        medarbejdereFrame =  v.findViewById(R.id.opret_medarbejder_frame_1);
        opretMedarbejderFragment2 = new opret_medarbejder_fragment_2();
        næste_medarbejdere =  v.findViewById(R.id.næste_medarbejdere);
        næste_medarbejdere.setOnClickListener(this);
        tilbage1 =  v.findViewById(R.id.tilbage_medarbejdere);
        tilbage1.setOnClickListener(this);

        radio_mand =  v.findViewById(R.id.radio_mand);
        radio_kvinde =  v.findViewById(R.id.radio_kvinde);


        opretSpinner(v);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.næste_medarbejdere:

                boolean altOK = presenter.korrektUdfyldtInformationFrag1(medarbejder_navn.getText().toString(),getGender(), getBirthYearChosen());

                if(altOK)
                setFragment(opretMedarbejderFragment2);

                break;
            case R.id.tilbage_medarbejdere:
                //TODO ordentlig navigation
                //getFragmentManager().popBackStackImmediate();
                getActivity().recreate();
                //getActivity().onBackPressed();
                break;

        }
    }

    /**
     * Metoden henter valgte værdi i spinner
     * @return -1 hvis intet er valgt, else return chosen item of fødsels_spinner
     */
    private int getBirthYearChosen() {
        int birthyear;
        if(!fødselsår_spinner.getSelectedItem().toString().equals("Vælg fødselsår") ) {
            birthyear = Integer.parseInt(fødselsår_spinner.getSelectedItem().toString());
            return birthyear;
        } else  {
            return -1;
        }

    }

    private String getGender() {
        if(radioGroupKoen.getCheckedRadioButtonId() == R.id.radio_mand) {
            return "mand";
        }
            else if(radioGroupKoen.getCheckedRadioButtonId() == R.id.radio_kvinde){
                return "kvinde";
        }
            else
                return "";
    }

    public void opretSpinner(View v){
        ArrayList<String> år= new ArrayList<>();
        år.add("Vælg fødselsår");
        for (Integer i=2004; i>1940; i--) {
            år.add(i.toString());
        }
        ArrayAdapter<String> adapter_år = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, år);
        fødselsår_spinner.setAdapter(adapter_år);
    }

    public void setFragment(android.support.v4.app.Fragment fragment) {
        medarbejdereFrame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.medarbejdere_frame, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }

    @Override
    public void errorNavn(String errorMsg) {

        medarbejder_navn.setError(errorMsg);

    }

    @Override
    public void errorKøn(String errorMsg) {

        if (!radio_kvinde.isChecked() && !radio_mand.isChecked())
    radio_kvinde.setError(errorMsg);
    }

    @Override
    public void errorFødselsår(String errorMsg) {
        ((TextView)fødselsår_spinner.getSelectedView()).setError(errorMsg);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        radio_kvinde.setError(null);

    }
    //TODO Gemme en "lokal instans" af den medarbejder man er ved at oprette på singleton objekt
    //TODO indsætte værdierne fra medarbejderne i de valgte felter igen.
}

