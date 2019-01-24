
package com.example.chris.flexicuv2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.chris.flexicuv2.model.Singleton;

/**
 *
 * @Author Janus
 *
 */
public class Bekraeftelse_bud_medarbejder_fragment extends Fragment implements View.OnClickListener {

    private TextView navn, periode, løn;
    private Button afslut;
    private Singleton singleton;
    private FrameLayout startskærmFrameTilDiverse;
    public Bekraeftelse_bud_medarbejder_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bekraeftelse_bud_medarbejder_fragment, container, false);
        startskærmFrameTilDiverse = v.findViewById(R.id.startskærm_frame_til_diverse);
        periode = (TextView) v.findViewById(R.id.bekræftelse_anmodning_periode);
        navn = (TextView) v.findViewById(R.id.bekræftelse_anmodning_navn);
        løn = v.findViewById(R.id.bekræftelse_anmodning_løn);

        afslut = (Button) v.findViewById(R.id.bekræftelse_anmodning_afslut_knap);
        afslut.setOnClickListener(this);
        singleton = Singleton.getInstance();

        navn.setText(singleton.midlertidigAftale.getMedarbejder().getNavn());
        periode.setText(singleton.midlertidigAftale.getStartDato().replace(" ", "") + " - " + singleton.midlertidigAftale.getSlutDato().replace(" ", ""));
        løn.setText(singleton.midlertidigAftale.getTimePris() + " DKK");
        return v;
    }

    @Override
    public void onClick(View v) {
        getFragmentManager().popBackStack("fragment", getFragmentManager().POP_BACK_STACK_INCLUSIVE);
    }
}
