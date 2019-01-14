package com.example.chris.flexicuv2.Medarbejdere_pakke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chris.flexicuv2.DBManager;
import com.example.chris.flexicuv2.Indlejninger.MultiSelectionSpinner;
import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.model.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Christian
 * Fragment til den anden del af oprettelse af medarbejder.
 * Inkluderer Email, tlf, arbejdsområde og kommentar
 */
public class opret_medarbejder_fragment_2 extends Fragment implements View.OnClickListener, Opret_Medarbejdere_Fragment2_Presenter.UpdateOpretMedarbejderFrag {


    private EditText medarbejder_email;
    private EditText medarbejder_tlf;
    private MultiSelectionSpinner arbejdsområde_spinner;
    private FrameLayout medarbejdereFrame;
    private FrameLayout opret_medarbejder_1_Frame;
    private opret_medarbejder_fragment_1 opretMedarbejderFragment1;
    private Button opret_medarbejder;
    private Button tilbage2;
    private Opret_Medarbejdere_Fragment2_Presenter presenter;
    private Singleton singleton;
    private DBManager dbManager;

    //public opret_medarbejder_fragment_2() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.opret_medarbejder_fragment_2, container, false);
        presenter = new Opret_Medarbejdere_Fragment2_Presenter(this);

        singleton = Singleton.getInstance();
        dbManager = new DBManager();

        medarbejder_email = (EditText) v.findViewById(R.id.editText_email);
        medarbejder_tlf = (EditText) v.findViewById(R.id.editText_tlf);

        arbejdsområde_spinner=(MultiSelectionSpinner) v.findViewById(R.id.arbejdsområde);

        medarbejdereFrame = (FrameLayout) v.findViewById(R.id.opret_medarbejder_frame_1);
        //Frame Layoutet for opret medarbejder 1
        opret_medarbejder_1_Frame = (FrameLayout) v.findViewById(R.id.opret_medarbejder_frame_1);
        //Fragmentet for opret medarbejder 1
        opretMedarbejderFragment1 = new opret_medarbejder_fragment_1();

        opret_medarbejder = (Button) v.findViewById(R.id.opret_medarbejder);
        opret_medarbejder.setOnClickListener(this);
        tilbage2 = (Button) v.findViewById(R.id.tilbage_medarbejdere2);
        tilbage2.setOnClickListener(this);

        opretSpinner();
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.opret_medarbejder:
                boolean altOK = presenter.korrektUdfyldtInformationFrag2(medarbejder_email.getText().toString(),medarbejder_tlf.getText().toString(),getArbejdsområde());

                if(altOK) {
                    //TODO Lav en ordentlig navigation
                    dbManager.createMedarbejder(singleton.midlertidigMedarbejder);
                    //singleton.getBruger().addMedarbejdere(singleton.midlertidigMedarbejder);
                    singleton.midlertidigMedarbejder=null;

                    Toast.makeText(getContext(), "HURRA! Du oprettede en ny medarbejder", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
                break;
            case R.id.tilbage_medarbejdere2:
                //TODO evt. "pop" backstack
                getActivity().onBackPressed();
                break;
        }
    }
    /**
     * Metoden henter valgte værdi i spinner
     * @return -1 hvis intet er valgt, else return chosen item of fødsels_spinner
     */
    private String getArbejdsområde() {
        String arb;
        if(!arbejdsområde_spinner.getSelectedItem().toString().equals("Vælg arbejdsområde") ) {
            arb = arbejdsområde_spinner.getSelectedItem().toString();
            return arb;
        } else  {
            return null;
        }

    }

    public void setFragment(android.support.v4.app.Fragment fragment) {
        //medarbejdereFrame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.medarbejdere_frame, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }

    /**
     * opretter spinner til valg af arbejdsområde
     */
    public void opretSpinner(){
        List<String> arbejdsområde_liste = new ArrayList<String>();
        arbejdsområde_liste.add("Vælg arbejdsområde");
        arbejdsområde_liste.add("Håndværker");
        arbejdsområde_liste.add("Smed");
        arbejdsområde_liste.add("Lagermand");
        arbejdsområde_liste.add("Elektriker");
        arbejdsområde_spinner.setItems(arbejdsområde_liste);
    }

    @Override
    public void errorEmail(String errorMsg) {
        medarbejder_email.setError(errorMsg);
    }

    @Override
    public void errorTlf(String errorMsg) {
        medarbejder_tlf.setError(errorMsg);
    }

    @Override
    public void errorArbejdsområder(String errorMsg) {
        ((TextView)arbejdsområde_spinner.getSelectedView()).setError(errorMsg);
    }

    @Override
    public void setEmail(String email) {
        medarbejder_email.setText(email);
    }

    @Override
    public void setTlf(String tlf) {
        medarbejder_tlf.setText(tlf);
    }

    @Override
    public void setArbejdsområde(String arbejdsområde) {
        ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)arbejdsområde_spinner.getAdapter();
        arbejdsområde_spinner.setSelection(array_spinner.getPosition(arbejdsområde));
    }


}