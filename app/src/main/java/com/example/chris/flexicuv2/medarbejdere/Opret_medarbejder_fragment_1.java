
package com.example.chris.flexicuv2.medarbejdere;



import android.location.Address;
import android.location.Geocoder;
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
import com.example.chris.flexicuv2.hjælpeklasser.Afstandsberegner;
import com.example.chris.flexicuv2.model.Singleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */public class Opret_medarbejder_fragment_1 extends Fragment implements View.OnClickListener, Opret_Medarbejdere_Fragment1_Presenter.UpdateOpretMedarbejderFrag, RadioGroup.OnCheckedChangeListener {

    public Opret_medarbejder_fragment_1() {
    }

    private Spinner fødselsår_spinner;
    private EditText medarbejder_navn;
    private FrameLayout medarbejdereFrame;
    private Opret_medarbejder_fragment_2 opretMedarbejderFragment2;
    private Button næste_medarbejdere;
    private Button tilbage1;
    ArrayAdapter<String> adapter_år;
    private RadioGroup radioGroupKoen;

    private RadioButton radio_mand;
    private RadioButton radio_kvinde;
    OpretMedarbejder_presenter opretMedarbejder_presenter;

    private Opret_Medarbejdere_Fragment1_Presenter presenter;
    private Singleton singleton;

    private EditText mVejnavn;
    private EditText mNummer;
    private EditText mPostNr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new Opret_Medarbejdere_Fragment1_Presenter(this);
        View v = inflater.inflate(R.layout.opret_medarbejder_fragment_1, container, false);

        //singleton.midlertidigMedarbejder = null;
        radioGroupKoen = v.findViewById(R.id.radio_køn);
        radioGroupKoen.setOnCheckedChangeListener(this);

        fødselsår_spinner = v.findViewById(R.id.fødselsår);
        medarbejder_navn =  v.findViewById(R.id.editText_navn);
        //Bemærk denne er anderledes fra sidste side
        medarbejdereFrame =  v.findViewById(R.id.opret_medarbejder_frame_1);
        opretMedarbejderFragment2 = new Opret_medarbejder_fragment_2();
        næste_medarbejdere =  v.findViewById(R.id.næste_medarbejdere);
        næste_medarbejdere.setOnClickListener(this);
        tilbage1 =  v.findViewById(R.id.tilbage_medarbejdere);
        tilbage1.setOnClickListener(this);

        radio_mand =  v.findViewById(R.id.radio_mand);
        radio_kvinde =  v.findViewById(R.id.radio_kvinde);

        mVejnavn = v.findViewById(R.id.adresse_vejnavn);
        mNummer = v.findViewById(R.id.adresse_nummer);
        mPostNr = v.findViewById(R.id.adresse_postnummer);

        opretSpinner(v);

        presenter.udfyldFelter();
        return v;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.næste_medarbejdere:
                boolean altOK = presenter.korrektUdfyldtInformationFrag1(medarbejder_navn.getText().toString(),getGender(), getBirthYearChosen(), mVejnavn.getText().toString(), mNummer.getText().toString(), mPostNr.getText().toString());
                if(altOK){
                    setFragment(opretMedarbejderFragment2);
                    try {
                        String[] latiOgLongi = Afstandsberegner.geolocate(getContext(),mVejnavn.getText().toString(),mNummer.getText().toString(),mPostNr.getText().toString(), null).split(" ");

                        singleton.midlertidigMedarbejder.setLatitude(latiOgLongi[0]);
                        singleton.midlertidigMedarbejder.setLongitude(latiOgLongi[1]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("lat: " + singleton.midlertidigMedarbejder.getLatitude() + "\nLong: " + singleton.midlertidigMedarbejder.getLongitude());
                }



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
        for (Integer i = ((Calendar.getInstance().get(Calendar.YEAR)-18)); i>((Calendar.getInstance().get(Calendar.YEAR)-90)); i--) {
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
    public void errorVejnavn(String errorMsg) {
        mVejnavn.setError(errorMsg);
    }

    @Override
    public void errorNummer(String errorMsg) {
        mNummer.setError(errorMsg);
    }

    @Override
    public void errorPostnr(String errorMsg) {
        mPostNr.setError(errorMsg);
    }

    @Override
    public void setNavn(String navn) {
        medarbejder_navn.setText(navn);
    }

    @Override
    public void setKøn(String køn) {
        if(køn.equals("kvinde")){
            radio_kvinde.setChecked(true);
            radio_mand.setChecked(false);
        }
        else if(køn.equals("mand")){
            radio_mand.setChecked(true);
            radio_kvinde.setChecked(false);
        }
        else {
            radio_kvinde.setChecked(false);
            radio_mand.setChecked(false);
        }
    }

    @Override
    public void setFødselsår(String fødselsår) {
        ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)fødselsår_spinner.getAdapter();
        fødselsår_spinner.setSelection(array_spinner.getPosition(fødselsår));
    }

    @Override
    public void setVejnavn(String vejnavn) {
        mVejnavn.setText(vejnavn);
    }

    @Override
    public void setNummer(String nummer) {
        mNummer.setText(nummer);
    }

    @Override
    public void setPostnr(String postnr) {
        mPostNr.setText(postnr);
    }

    //TODO Hvad skal der lige gøres med disse to
    @Override
    public void setLatitude(String latitude) {

    }

    @Override
    public void setLongitude(String longitude) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        radio_kvinde.setError(null);

    }
    //TODO Gemme en "lokal instans" af den medarbejder man er ved at oprette på singleton objekt
    //TODO indsætte værdierne fra medarbejderne i de valgte felter igen.


}

